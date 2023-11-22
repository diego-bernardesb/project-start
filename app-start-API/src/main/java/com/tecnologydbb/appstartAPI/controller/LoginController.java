package com.tecnologydbb.appstartAPI.controller;

import com.tecnologydbb.appstartAPI.dto.UserLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    //tela principal onde sera realizado o login do usuario
    @GetMapping("/")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok().body("Tela de Login!");
    }

    //valida e autentica o usuario atravez de token jwt
    @PostMapping("/login")
    public ResponseEntity logar(@RequestBody UserLoginDTO dto){
        if(dto.getLogin() != null)

        return ResponseEntity.ok().build();
    }

}
