package arep.taller6.taller6.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "https://taller06arepfront.duckdns.org")
public class HelloController {
    @GetMapping("/hello")
    public Map<String, String> home() {
        return Map.of("mensaje", "Hola desde el backend :D");
    }
}
