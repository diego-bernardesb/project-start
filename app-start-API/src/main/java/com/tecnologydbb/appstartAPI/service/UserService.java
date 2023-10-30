package com.tecnologydbb.appstartAPI.service;

import com.tecnologydbb.appstartAPI.dto.UserDTO;
import com.tecnologydbb.appstartAPI.dto.UserInsertDTO;
import com.tecnologydbb.appstartAPI.entity.User;
import com.tecnologydbb.appstartAPI.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    //injecao de dependencia via lombok com anotation AllArgsConstructor
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public List<UserDTO> findAll(){
        //instancia lista de usuarios
        List<User> list = userRepository.findAll();
        //converte a lista de usuarios atraves da lambda para uma lista de usuariosDTO
        return list.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public UserDTO findById(long id){
        //instancia um usuarios e busca por id
        User userById = userRepository.findById(id).get();
        //converte o usuarios em um usuariosDTO
        return new UserDTO(userById);
    }

    public UserDTO insertUser(UserInsertDTO dto){
        //verifica se o email e cpf ja esta cadastrado
        User userEmail = userRepository.findByEmail(dto.getEmail());
        User userCpf = userRepository.findByCpf(dto.getCpf());
        //estoura uma exception caso o email ou cpf ja esteja cadastrado
        if (userEmail != null) throw new RuntimeException("E-mail já cadastrado.");
        else if (userCpf != null) throw new RuntimeException("CPF já cadastrado.");

        //instancia novo usuario para salvar no banco
        User newUser = new User();
        //seta todos os valores da requisicao post no novo usuario
        newUser.setName(dto.getName());
        newUser.setCpf(dto.getCpf());
        newUser.setDateOfBirth(dto.getDateOfBirth());
        newUser.setEmail(dto.getEmail());
        //cryptografia da senha com BCrypt
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setRole(dto.getRole());
        //salva o usuario no banco
        newUser = userRepository.save(newUser);
        return new UserDTO(newUser);
    }

    public void dropUser(long id){
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(UserInsertDTO dto, long id){
        User user = userRepository.findById(id).get();
        //verifica se existe usuario.
        if(user == null) throw new RuntimeException("Usuário não encontrado.");

        //adiciona os novos valores para um usuario
        user.setName(dto.getName().isEmpty() ? user.getName() :dto.getName());
        user.setCpf(dto.getCpf().isEmpty() ? user.getCpf() : dto.getCpf());
        user.setDateOfBirth(dto.getDateOfBirth().isEqual(user.getDateOfBirth()) ?
                user.getDateOfBirth() : dto.getDateOfBirth());
        user.setEmail(dto.getEmail().isEmpty() ? user.getEmail() : dto.getEmail());
        user.setPassword(dto.getPassword().isEmpty() ? user.getPassword() : passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole().equals(user.getRole()) ? user.getRole() : dto.getRole());

        user = userRepository.save(user);
        return new UserDTO(user);
    }
}
