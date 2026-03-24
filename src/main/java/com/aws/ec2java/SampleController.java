package com.aws.ec2java;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/aws")
public class SampleController {
    
    @GetMapping
    public ResponseEntity<String> getMethodName() {
        return ResponseEntity.ok("Hello Aws World, this is a get response");
    }   

    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestBody String entity) {
        return ResponseEntity.ok("Hello, this is a post response");
    }
    
}
