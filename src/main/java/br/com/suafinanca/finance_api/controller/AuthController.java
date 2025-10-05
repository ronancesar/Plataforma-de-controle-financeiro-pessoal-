package br.com.suafinanca.finance_api.controller;

import br.com.suafinanca.finance_api.dto.LoginDto;
import br.com.suafinanca.finance_api.dto.RegistroDto;
import br.com.suafinanca.finance_api.dto.TokenDto;
import br.com.suafinanca.finance_api.model.Usuario;
import br.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.suafinanca.finance_api.security.JwtTokenService;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Token não e mesmo

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar (@RequestBody @Valid RegistroDto registroDto){
        if(usuarioRepository.findByEmail(registroDto.email()). isPresent()){
            return ResponseEntity.badRequest().body("Erro: E-mail já esta em uso!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(registroDto.nome());
        novoUsuario.setEmail(registroDto.email());
        novoUsuario.setSenha(passwordEncoder.encode(registroDto.senha()));

       usuarioRepository.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado com sucesso");
    }

    @Autowired
    private JwtTokenService tokenService; // essa parte e pra adicionar o serviço do token

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto){
        var usernamePassword =  new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());

        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String userEmail = auth.getName();

        var usuario = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado após autenticação"));

        String token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(new TokenDto(token));
    }


}
