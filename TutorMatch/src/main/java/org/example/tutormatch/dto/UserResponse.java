package org.example.tutormatch.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.tutormatch.entity.Role;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;

    public UserResponse(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
