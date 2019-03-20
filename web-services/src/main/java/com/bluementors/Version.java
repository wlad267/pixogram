package com.bluementors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Version {

    @GetMapping("/version")
    public ResponseEntity verison() {
        //return ResponseEntity.ok(this.getClass().getPackage().getImplementationVersion());
        return ResponseEntity.ok("1.1");
    }
}
