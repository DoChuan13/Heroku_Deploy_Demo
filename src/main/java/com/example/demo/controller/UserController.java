package com.example.demo.controller;

import com.example.demo.dto.request.ChangeAvatar;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.User;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.jwt.JwtTokenFilter;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtTokenFilter tokenFilter;
    @Autowired
    private IUserService userService;


    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatar avatar) {
        String token = tokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(token);
        User user = userService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found"));
        if (avatar.getAvatar() == null || avatar.getAvatar().trim().equals("")) {
            return new ResponseEntity<>("No", HttpStatus.OK);
        } else {
            user.setAvatar(avatar.getAvatar());
            userService.save(user);
            return new ResponseEntity<>(new ResponMessage("Yes"), HttpStatus.OK);
        }
    }
}
