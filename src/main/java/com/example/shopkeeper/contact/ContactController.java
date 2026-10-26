package com.example.shopkeeper.contact;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/contact")
public class ContactController {
 private final ContactRepository repo;
 public ContactController(ContactRepository repo){this.repo=repo;}
 public record Request(@NotBlank String name,@Email @NotBlank String email,@NotBlank String message){}
 @PostMapping @ResponseStatus(HttpStatus.CREATED)
 public ContactMessage create(@Valid @RequestBody Request r){return repo.save(new ContactMessage(r.name(),r.email(),r.message()));}
 @GetMapping public List<ContactMessage> all(){return repo.findAll();}
}
