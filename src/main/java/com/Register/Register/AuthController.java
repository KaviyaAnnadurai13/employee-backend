package com.Register.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("User signed-in successfully!");
        } catch (BadCredentialsException e) {
            if (userRepository.existsByUsername(loginDto.getUsernameOrEmail()) || userRepository.existsByEmail(loginDto.getUsernameOrEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
            }
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        // Check if username exists in DB
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // Check if email exists in DB
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already taken!");
        }

        // Create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole(signUpDto.getRole());

        // Retrieve role from repository
        String roleName = signUpDto.getRole() != null ? signUpDto.getRole() : "ROLE_USER";
        Optional<Role> optionalRole = roleRepository.findByName(roleName);
        if (!optionalRole.isPresent()) {
            return ResponseEntity.badRequest().body("Role not found");
        }

        Role role = optionalRole.get();
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
