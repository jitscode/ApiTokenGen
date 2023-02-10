package com.example.ApiTokenGen.ApiTokenGen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/generatetoken")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return loginService.getToken(loginRequest);
    }
    @GetMapping("/getuserdetails")
    public ResponseEntity<UserDetails> getUserDetails(){
        return loginService.getUserDetails();
    }

    @PostMapping("/getuser")
    public ResponseEntity<UserDetails> getUser(@RequestBody LoginRequest loginRequest){
        return loginService.getUserDetailsByUsernameAndPassword(loginRequest);
    }
}
