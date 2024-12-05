package com.system.ebanky.DTO;

import com.system.ebanky.Entity.Enums.Role;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private int age;
    private int creditScore;
    private Role role;
    private Double monthly_income;
}
