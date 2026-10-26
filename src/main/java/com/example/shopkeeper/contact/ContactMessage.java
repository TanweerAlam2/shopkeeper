package com.example.shopkeeper.contact;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class ContactMessage {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String name; private String email;
 @Column(length=2000) private String message;
 private LocalDateTime createdAt=LocalDateTime.now();
 public ContactMessage() {}
 public ContactMessage(String n,String e,String m){name=n;email=e;message=m;}
 public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
 public String getMessage(){return message;} public LocalDateTime getCreatedAt(){return createdAt;}
}
