package dev.leaf_carvalho.gerenciador_financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.leaf_carvalho.gerenciador_financeiro.dto.LoginRequestDTO;
import dev.leaf_carvalho.gerenciador_financeiro.dto.RegisterRequestDTO;
import dev.leaf_carvalho.gerenciador_financeiro.dto.ResponseDTO;
import dev.leaf_carvalho.gerenciador_financeiro.exception.UnauthorizedException;
import dev.leaf_carvalho.gerenciador_financeiro.infra.security.TokenService;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UsuariosRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        Usuarios user = repository.findByEmail(body.email())
                .orElseThrow(() -> new UnauthorizedException("Usuário não encontrado"));

        if (!passwordEncoder.matches(body.senha(), user.getSenha())) {
            throw new UnauthorizedException("Email ou senha incorretos ou não autorizado.");
        }

        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        if (repository.findByEmail(body.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Usuarios newUser = new Usuarios();
        newUser.setSenha(passwordEncoder.encode(body.senha()));
        newUser.setEmail(body.email());
        newUser.setUsername(body.username());
        newUser.setRole(body.role());
        repository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), token));
    }
}
