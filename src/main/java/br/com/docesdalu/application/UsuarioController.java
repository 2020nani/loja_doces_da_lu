package br.com.docesdalu.application;

import br.com.docesdalu.application.config.security.RoleAcess;
import br.com.docesdalu.application.config.security.Roles;
import br.com.docesdalu.application.config.security.RolesRepository;
import br.com.docesdalu.application.dto.UsuarioInput;
import br.com.docesdalu.application.mapper.UsuarioMapper;
import br.com.docesdalu.core.usuario.Usuario;
import br.com.docesdalu.core.usuario.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private UsuarioMapper usuarioMapper;
    private UsuarioService usuarioService;
    private BCryptPasswordEncoder passwordEncoder;
    private RolesRepository rolesRepository;

    public UsuarioController(UsuarioMapper usuarioMapper, UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("teste")
    public String testeSecurity(){
        return "ok";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("usuario")
    public Usuario salvarUsuario(@Validated @RequestBody UsuarioInput usuarioInput){
        Usuario usuario = usuarioMapper.usuarioMapperInput(usuarioInput, passwordEncoder);
        System.out.println(usuario);
        return usuarioService.salvarUsuario(usuario);
    }

    @PostMapping("usuario_roles")
    public Roles salvarRolesUsuario(@RequestParam RoleAcess roles){
        return rolesRepository.save(Roles.builder()
                .roleAcess(roles)
                .build());
    }

}
