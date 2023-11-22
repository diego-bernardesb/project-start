package com.tecnologydbb.appstartAPI.dto;

import com.tecnologydbb.appstartAPI.entity.ENUN.RoleEnun;
import com.tecnologydbb.appstartAPI.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDTO {

    private String name;
    private String cpf;
    private String email;
    private String password;
    private RoleEnun role;

    public UserInsertDTO(User entity) {
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.role = entity.getRole();
    }
}
