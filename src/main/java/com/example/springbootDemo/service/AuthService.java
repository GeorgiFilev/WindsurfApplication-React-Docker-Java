package com.example.springbootDemo.service;

import com.example.springbootDemo.dao.PersonJpaRepository;
import com.example.springbootDemo.dao.RoleJpaRepository;
import com.example.springbootDemo.model.ERole;
import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Role;
import com.example.springbootDemo.payload.request.LoginRequest;
import com.example.springbootDemo.payload.request.SignupRequest;
import com.example.springbootDemo.payload.response.JwtResponse;
import com.example.springbootDemo.payload.response.MessageResponse;
import com.example.springbootDemo.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PersonJpaRepository personJpaRepository;

    @Autowired
    RoleJpaRepository roleJpaRespository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse getJwtResponse(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int savePerson(SignupRequest signUpRequest){
        if (personJpaRepository.existsByUsername(signUpRequest.getUsername())) {
            return 1;
        }

        if (personJpaRepository.existsByEmail(signUpRequest.getEmail())) {
            return 2;
        }

        // Create new user's account
        Person user = new Person(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleJpaRespository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleJpaRespository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleJpaRespository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleJpaRespository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        personJpaRepository.save(user);
        return 0;
    }
}
