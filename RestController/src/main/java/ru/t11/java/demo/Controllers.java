package ru.t11.java.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.java.demo.LogAround;

@RestController
@RequestMapping("/api/v1")
public class Controllers {

    @LogAround
    @PostMapping("/post")
    public ResponseEntity<String> postClient (@RequestBody(required = false) String body){
        return ResponseEntity.ok("Request processed");
    }

    @LogAround
    @GetMapping("/get")
    public String getClient() {
        return "это Клиент";
    }
}
