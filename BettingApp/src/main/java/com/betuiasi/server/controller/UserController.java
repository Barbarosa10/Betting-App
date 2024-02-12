package com.betuiasi.server.controller;

import com.betuiasi.server.dto.Identity;
import com.betuiasi.server.dto.PasswordResetRequest;
import com.betuiasi.server.dto.UserDTO;
import com.betuiasi.server.service.AuthenticationServiceImpl;
import com.betuiasi.server.service.UserService;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/authenticate", consumes = "application/x-protobuf", produces = "application/json")
    public ResponseEntity<?> authenticate(@RequestBody byte[] data) throws InvalidProtocolBufferException, InterruptedException {
        System.out.println(data);
        System.out.println("Printing data");
        for (byte datum : data) {
            System.out.print(datum + " ");
        }
        System.out.println("Done printing data");
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<String> tokenReference = new AtomicReference<>();

        io.grpc.stub.StreamObserver<Identity.AuthenticationResponse> responseObserver = new io.grpc.stub.StreamObserver<>() {
            @Override
            public void onNext(Identity.AuthenticationResponse authenticationResponse) {
                tokenReference.set(authenticationResponse.getToken());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Authentication error: " + throwable.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        };

        Identity.AuthenticationRequest authRequest = Identity.AuthenticationRequest.parseFrom(data);
        authenticationService.authenticate(authRequest, responseObserver);

        latch.await();
        String token = tokenReference.get();
        if (token != null && !token.equals("invalid-token")) {
            Map<String, String> responseBody = new HashMap<>();

            Optional<UserDTO> user = userService.findByUsername(authRequest.getUsername());
            user.ifPresent(userDTO -> responseBody.put("funds", userDTO.getFunds().toString()));

            responseBody.put("token", token);

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token received");
        }
    }

    @GetMapping(value ="/funds", params = {"username"})
    public ResponseEntity<?> getFunds(@RequestParam String username) {
        System.out.println(username);
        Optional<UserDTO> user = userService.findByUsername(username);
        if(user.isPresent()){
            System.out.println(user.get().getFunds());
            return new ResponseEntity<>(String.format("{\"funds\": \"%s\"}", user.get().getFunds()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user) throws URISyntaxException {
        System.out.println("user "+user.getUsername() + " "+user.getPassword() + " "+user.getCnp() + " "+user.getRole());
        user.setFunds(0.0);
        UserDTO savedUser = userService.save(user);
        return ResponseEntity.ok("");

    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordResetRequest user) throws URISyntaxException {
        Optional<UserDTO> foundUser= userService.findByUsernameAndPassword(user.getUsername(), user.getOldPassword());
        if(foundUser.isPresent()){
            foundUser.get().setPassword(user.getNewPassword());
            userService.save(foundUser.get());
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
