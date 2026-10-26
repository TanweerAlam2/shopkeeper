package com.example.shopkeeper.auth;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService service;
    public AuthController(UserService service){ this.service = service; }
    public record RegisterRequest(@NotBlank @Size(max=80) String fullName, @NotBlank @Email String email, @NotBlank @Size(min=6,max=120) String password) {}
    public record LoginRequest(@NotBlank @Email String email, @NotBlank String password) {}
    public record Profile(Long id, String fullName, String email) {}
    public record AuthResponse(boolean success, String message, Profile user) {}

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request, HttpSession session) {
        User user = service.register(request.fullName(), request.email(), request.password());
        session.setAttribute("userId", user.getId());
        return ResponseEntity.ok(new AuthResponse(true, "Account created successfully.", profile(user)));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        return service.authenticate(request.email(), request.password()).map(user -> {
            session.setAttribute("userId", user.getId());
            return ResponseEntity.ok(new AuthResponse(true, "Signed in successfully.", profile(user)));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse(false, "Invalid email or password.", null)));
    }
    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me(HttpSession session) {
        Object id = session.getAttribute("userId");
        if (id == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(false,"Not signed in.",null));
        return service.find((Long)id).map(u -> ResponseEntity.ok(new AuthResponse(true,"Profile loaded.",profile(u))))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(false,"Session expired.",null)));
    }
    @PostMapping("/logout") public AuthResponse logout(HttpSession session){ session.invalidate(); return new AuthResponse(true,"Signed out.",null); }
    private Profile profile(User u){ return new Profile(u.getId(),u.getFullName(),u.getEmail()); }
}
