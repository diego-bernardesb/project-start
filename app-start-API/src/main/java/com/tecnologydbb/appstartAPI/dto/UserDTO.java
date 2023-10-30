package com.tecnologydbb.appstartAPI.dto;

import com.tecnologydbb.appstartAPI.entity.ENUN.RoleEnun;
import com.tecnologydbb.appstartAPI.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    private String name;
    private String cpf;
    private LocalDate dateOfBirth;
    private String email;
    private RoleEnun role;

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.dateOfBirth = entity.getDateOfBirth();
        this.email = entity.getEmail();
        this.role = entity.getRole();
    }
}
