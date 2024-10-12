package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.UserDTO;
import com.upc.talkia_proyect.dtos.queries.ShowSuscriptionDetailsDTO;
import com.upc.talkia_proyect.entities.User;
import com.upc.talkia_proyect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> insertUser(@RequestBody UserDTO userDTO){
        try {
            User user = modelMapper.map(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userService.insertUser(user);
            userDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/users_exist/{userName}/{password}")
    public ResponseEntity<?> existsUser(@PathVariable String userName, @PathVariable String password){
        try {
            Boolean exists = userService.existsUser(userName, password);
            return new ResponseEntity<>(exists, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        try {
            User user = modelMapper.map(userDTO, User.class);
            user = userService.updateUser(user);
            userDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> listUsers(){
        try {
            List<User>users = userService.listUsers();
            List<UserDTO> userDTOS = modelMapper.map(users, List.class);
            return new ResponseEntity<>(userDTOS, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users_register_date/{startDate}/{endDate}")
    public ResponseEntity<?> listUsersByRegisterDate(@PathVariable LocalDate startDate,@PathVariable  LocalDate endDate){
        try {
            List<User>users = userService.listUsersByRegisterDate(startDate, endDate);
            List<UserDTO> userDTOS = modelMapper.map(users, List.class);
            return new ResponseEntity<>(userDTOS, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users_status/{status}")
    public ResponseEntity<?> listUsersByStatus(@PathVariable String status){
        try {
            List<User>users =  userService.listUsersByStatus(status);
            List<UserDTO> userDTOS = modelMapper.map(users, List.class);
            return new ResponseEntity<>(userDTOS, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user_by_username/{username}")
    public ResponseEntity<?> getUserByUserNameContains(@PathVariable String username){
        try {
            List<User>users =  userService.getUserByUserNameContains(username);
            List<UserDTO> userDTOS = modelMapper.map(users, List.class);
            return new ResponseEntity<>(userDTOS, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user_by_id/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId){
        try {
            User user = userService.getUserById(userId);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/current_suscription_by_user_id/{userId}")
    public ResponseEntity<?> getCurrentSuscription(@PathVariable int userId){
        try {
            ShowSuscriptionDetailsDTO suscriptionDTO = userService.getCurrentSuscription(userId);
            return new ResponseEntity<>(suscriptionDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
