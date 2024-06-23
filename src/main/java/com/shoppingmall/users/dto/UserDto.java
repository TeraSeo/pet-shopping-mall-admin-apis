package com.shoppingmall.users.dto;

import com.shoppingmall.users.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean isVerified;

}
