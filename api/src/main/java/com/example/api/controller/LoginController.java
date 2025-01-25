package com.example.api.controller;

//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.LoginDTO;
import com.example.api.dto.TokenDTO;
import com.example.api.model.Usuario;
import com.example.api.service.TokenService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
	private final AuthenticationManager authenticationManager;

	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

    @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario)auth.getPrincipal());
        String role = auth.getAuthorities().iterator().next().getAuthority();
        return ResponseEntity.ok(new TokenDTO(token, role, auth.getName()));
            
	}
}
