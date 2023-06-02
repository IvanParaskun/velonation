package org.velonation.core.controller.api.v1.admin.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin/auth")
public class AuthorizationController {
    @GetMapping
    public ResponseEntity<String> auth() {
        return ResponseEntity.status(200).body("Authorized");
    }
}
