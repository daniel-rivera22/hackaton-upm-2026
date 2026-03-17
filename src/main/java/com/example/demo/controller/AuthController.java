package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserRegitrationDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public String registrar(UserRegitrationDTO dto){
        try {
            userService.registrarCiudadano(dto);
            return "redirect:/dashboard.html?success=true";
        }catch (Exception e){
            return "redirect:/index.html?error=" + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(LoginDTO dto){
        Optional<User> userOpt = userService.buscarPorNombre(dto.nombre());
        if (userOpt.isPresent()){
            User user = userOpt.get();
            if (passwordEncoder.matches(dto.password(), user.getPassword())){
                return "redirect:/dashboard.html?success=true";
            }
        }
        return "redirect:/index.html?error=invalid_credentials";
    }

}
