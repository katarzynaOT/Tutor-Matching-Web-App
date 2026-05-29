package org.example.tutormatch.mapper;

import org.example.tutormatch.dto.UserResponse;
import org.example.tutormatch.entity.*;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        if (user == null) return null;
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
