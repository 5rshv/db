package skypro.hogwarts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("production")
public class InfoController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort() {
        return "port: " + port;
    }
}
