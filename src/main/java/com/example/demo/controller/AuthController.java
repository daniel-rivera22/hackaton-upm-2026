package com.example.demo.controller;

import com.example.demo.dto.UserRegitrationDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    public String registrar(UserRegitrationDto dto){
        try {
            userService.registrarCiudadano(dto);
            return "redirect:/index.html?success=true";
        }catch (Exception e){
            return "redirect:/index.html?error=" + e.getMessage();
        }
    }
}
