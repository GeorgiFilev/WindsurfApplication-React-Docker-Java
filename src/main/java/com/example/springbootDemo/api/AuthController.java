package com.example.springbootDemo.api;


import com.example.springbootDemo.dao.PersonJpaRepository;
import com.example.springbootDemo.dao.PersonRepository;
import com.example.springbootDemo.dao.RoleJpaRepository;
import com.example.springbootDemo.model.ERole;
import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Role;
import com.example.springbootDemo.payload.request.LoginRequest;
import com.example.springbootDemo.payload.request.SignupRequest;
import com.example.springbootDemo.payload.response.JwtResponse;
import com.example.springbootDemo.payload.response.MessageResponse;
import com.example.springbootDemo.security.jwt.JwtUtils;
import com.example.springbootDemo.service.AuthService;
import com.example.springbootDemo.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
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

    @Autowired
    private AuthService authService;


    @PostMapping("/signin")
//        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles));
//    }
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        if(authService.getJwtResponse(loginRequest) != null){
            return ResponseEntity.ok( authService.getJwtResponse(loginRequest));
        }
        else{
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }

    }

    @PostMapping("/signup")
    public  ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        int response = authService.savePerson(signupRequest);

        if (response== 1){
            return new ResponseEntity<String>("A user with that username exist",HttpStatus.BAD_REQUEST);
        }
        else if (response== 2){
            return new ResponseEntity<String>("A user with that email exist",HttpStatus.BAD_REQUEST);
        }
        else if(response ==0){
            return new ResponseEntity<String>("user created",HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<String>("unexpected error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//        if (personJpaRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (personJpaRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        Person user = new Person(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleJpaRespository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleJpaRespository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleJpaRespository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleJpaRespository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        personJpaRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
}
