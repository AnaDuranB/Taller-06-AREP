package arep.taller6.taller6.controller;

import arep.taller6.taller6.service.UserService;
import arep.taller6.taller6.service.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://taller06arepfront.duckdns.org")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (userService.authenticate(username, password)) {
            String token = jwtUtil.generateToken(username); // Generar token
            return ResponseEntity.ok(Map.of("token", token)); // Devolver token
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        userService.registerUser(username, password);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con éxito"));
    }
}