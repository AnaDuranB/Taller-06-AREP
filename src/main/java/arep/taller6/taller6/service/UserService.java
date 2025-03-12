package arep.taller6.taller6.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final Map<String, String> users = new HashMap<>();

    public UserService() {
        // usuario de prueba (admin:admin123)
        users.put("admin", passwordEncoder.encode("admin123"));
    }

    public boolean authenticate(String username, String password) {
        String hashedPassword = users.get(username);
        return hashedPassword != null && passwordEncoder.matches(password, hashedPassword);
    }

    public void registerUser(String username, String password) {
        users.put(username, passwordEncoder.encode(password));
    }
}