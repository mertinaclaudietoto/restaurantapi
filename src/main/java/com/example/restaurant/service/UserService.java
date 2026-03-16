package com.example.restaurant.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.UserDTO;
import com.example.restaurant.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.example.restaurant.model.Users;
import com.example.restaurant.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

   public List<UserResponseDTO> getAllUsers(Pageable pageable) {

    Page<Users> page = userRepository.findAll(pageable);

    return page.getContent().stream().map(user -> {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setPseudo(user.getPseudo());
        dto.setEmail(user.getEmail());
        dto.setIdrole(user.getIdrole());
        return dto;
    }).collect(Collectors.toList());
}
    // creation simple utilisateur
    public Users saveUser(UserDTO userDTO) {
        Users users = new Users();
        users.setPseudo(userDTO.getPseudo());
        users.setEmail(userDTO.getEmail());
        users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        users.setIdrole(63L);
        return userRepository.save(users);
    }

    public UserResponseDTO getUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setPseudo(user.getPseudo());
                    dto.setEmail(user.getEmail());
                    dto.setIdrole(user.getIdrole());
                    return dto;
                })
                .orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}