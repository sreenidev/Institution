//package com.multimodule.application.controller;
//
//import com.multimodule.domain.model.CreateUser;
//import com.multimodule.domain.repository.CreateUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/createUser")
//public class CreateUserController {
//    @Autowired
//    public PasswordEncoder passwordEncoder;
//    @Autowired
//    public CreateUserRepository createUserRepository;
//
//    @PostMapping("/user")
//    public CreateUser newUser(@RequestBody CreateUser createUser){
//         createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));
//         return createUserRepository.save(createUser);
//    }
//}
