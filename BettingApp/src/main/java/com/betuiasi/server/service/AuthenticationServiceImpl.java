package com.betuiasi.server.service;

import com.betuiasi.server.dto.Identity;
import com.betuiasi.server.dto.UserDTO;
import io.grpc.Status;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl extends AuthenticationServiceGrpc.AuthenticationServiceImplBase {
    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void authenticate(
            Identity.AuthenticationRequest request,
            io.grpc.stub.StreamObserver<Identity.AuthenticationResponse> responseObserver) {

        Optional<UserDTO> data = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (data.isPresent()) {
            String jws = Jwts.builder()
                    .claim("id", data.get().getUid())
                    .claim("username", data.get().getUsername())
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plusSeconds(3600 * 2)))
                    .signWith(
                            SignatureAlgorithm.HS256, Base64.getDecoder().decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                    )
                    .compact();

            Claims claims = validateToken(jws);

            if (claims != null) {
                Identity.AuthenticationResponse result = Identity.AuthenticationResponse.newBuilder().setToken(jws).build();
                responseObserver.onNext(result);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.UNAUTHENTICATED.withDescription("Token validation failed").asRuntimeException());
            }

        } else {
            responseObserver.onError(
                    Status.UNAUTHENTICATED
                            .withDescription("Authentication failed")
                            .augmentDescription("Invalid user credentials")
                            .asRuntimeException()
            );
        }
    }

    private Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            return null;
        }
    }

    @Override
    public void validate(
            Identity.ValidateRequest request,
            io.grpc.stub.StreamObserver<Identity.ValidateResponse> responseObserver) {
        String tokenToValidate = request.getValid();
        Claims claims = validateToken(tokenToValidate);

        if (claims != null) {
            Identity.ValidateResponse result = Identity.ValidateResponse.newBuilder().setValid("Token is valid").build();
            responseObserver.onNext(result);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.UNAUTHENTICATED.withDescription("Invalid token").asRuntimeException());
        }
    }
}
