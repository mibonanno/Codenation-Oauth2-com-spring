package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping("/user")
    public List<User> findAll(@RequestParam(required = false) Optional<String> accelerationName,
                              @RequestParam(required = false) Optional<Long> companyId) {

        return accelerationName.map(service::findByAccelerationName)
                .orElseGet(() -> companyId.map(service::findByCompanyId).orElse(new ArrayList<>()));
    }


}