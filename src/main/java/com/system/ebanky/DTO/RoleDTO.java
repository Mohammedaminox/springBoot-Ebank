package com.system.ebanky.DTO;

import com.system.ebanky.Entity.Enums.Role;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Role role;
}
