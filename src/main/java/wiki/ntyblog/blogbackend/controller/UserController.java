package wiki.ntyblog.blogbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wiki.ntyblog.blogbackend.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@RestController
@RequestMapping("/backend/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody HashMap req) {
        String username = (String) req.get("username");
        String password = (String) req.get("password");
        String regisCode = (String) req.get("regisCode");

        if (!regisCode.equals("NTYBLOGABC")) {
            return ResponseEntity.badRequest().body("Invalid Registration Code");
        }

        try {
            userService.createUser(username, password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("Successfully Registered");
    }

}
