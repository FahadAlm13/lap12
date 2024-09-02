package spring.boot.lap12security.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.lap12security.Model.User;
import spring.boot.lap12security.Service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user){
    authService.register(user);
    return ResponseEntity.status(200).body("User registered successfully");
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user,@PathVariable Integer id){
        authService.updateUser(user,id);
        return ResponseEntity.status(200).body("User update successfully");
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        authService.deleteUser(id);
        return ResponseEntity.status(200).body("User delete it successfully");
    }
}
