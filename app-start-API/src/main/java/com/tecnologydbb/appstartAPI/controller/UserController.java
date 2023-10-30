package com.tecnologydbb.appstartAPI.controller;

import com.tecnologydbb.appstartAPI.dto.UserDTO;
import com.tecnologydbb.appstartAPI.dto.UserInsertDTO;
import com.tecnologydbb.appstartAPI.service.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    //dependencias injetadas com lombok atravez da anotation de AllArgConstructor
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = userService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id) {
        UserDTO userById = userService.findById(id);
        return ResponseEntity.ok(userById);
    }

    @PostMapping("/insertUser")
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserInsertDTO dto){
        try {
            //insere o objeto no base de dados
            UserDTO addUser = userService.insertUser(dto);
            return ResponseEntity.ok(addUser);
        }
        catch (ServiceException e){
            //caso alguma validacao não autorize a criação do novo usuario retorna um erro 422
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/drop/{id}")
    public void dropUser(@PathVariable Long id){
        userService.dropUser(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserInsertDTO dto, @PathVariable long id){
        //executa logica para update de usuario
        UserDTO update = userService.updateUser(dto, id);

        return ResponseEntity.ok(update);
    }
}
