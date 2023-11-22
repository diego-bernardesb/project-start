package com.tecnologydbb.appstartAPI.dto;

import com.tecnologydbb.appstartAPI.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String login;
    private String password;

    public UserLoginDTO(User entity) {
        //o login e definido pela matricula do funcionario que sera definido pelo id
        this.login = String.valueOf(entity.getId()); //recebe um long transformando para uma string
        this.password = entity.getPassword();
    }
}
