package com.tpe.service.user;

import com.tpe.payload.request.user.LoginRequest;
import com.tpe.payload.response.authantication.AuthResponse;
import com.tpe.security.jwt.JwtTokenProvider;
import com.tpe.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationService
{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<AuthResponse> authenticateUser(LoginRequest loginRequest)
    {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        //Validate user from authantication manager
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        //validated user sent to the context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = "Bearer " + jwtTokenProvider.generateToken(authentication);

        //reaching loged in user details

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //required fields are setting
        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        //reaching first role of the user
        Optional<String> firstRole = roles.stream().findFirst();

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();
        authResponse.email(userDetails.getEmail());
        authResponse.token(token.substring(7));
        firstRole.ifPresent(authResponse::role);

        return ResponseEntity.ok(authResponse.build());

    }
}
