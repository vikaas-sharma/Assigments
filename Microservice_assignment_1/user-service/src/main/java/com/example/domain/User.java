package com.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Email @Column(unique = true)
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

  private String role = "ROLE_STUDENT";

  public Long getId() { return id; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getRole() { return role; }
  public void setRole(String role) { this.role = role; }
}
