package saraya.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import saraya.requests.AuthenticationRequest;
import saraya.services.user.AuthenticationService;
import saraya.services.user.JWTService;
import saraya.services.user.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    public final AuthenticationService authenticationService;
    public final UserService userService;
    public final JWTService jwtService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserService userService, JWTService jwtService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest request) throws BadCredentialsException {
        Authentication authentication = authenticationService.authenticate(request);
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authentication);
        }
        throw new BadCredentialsException("Bad credentials");
    }
}
