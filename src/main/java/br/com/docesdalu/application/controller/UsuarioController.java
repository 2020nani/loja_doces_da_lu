package br.com.docesdalu.application.controller;

import br.com.docesdalu.application.config.security.RoleAcess;
import br.com.docesdalu.application.config.security.Roles;
import br.com.docesdalu.application.config.security.RolesRepository;
import br.com.docesdalu.application.dto.input.UsuarioEditForm;
import br.com.docesdalu.application.dto.input.UsuarioInput;
import br.com.docesdalu.application.mapper.UsuarioMapper;
import br.com.docesdalu.core.usuario.Usuario;
import br.com.docesdalu.core.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("teste")
    public String testeSecurity(){
        return "ok";
    }

    @PostMapping("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvarUsuario(@Validated @RequestBody UsuarioInput usuarioInput){
        Usuario usuario = usuarioMapper.usuarioMapperInput(usuarioInput, passwordEncoder);
        return usuarioService.salvarUsuario(usuario);
    }

    @PostMapping("usuario_roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Roles salvarRolesUsuario(@RequestParam RoleAcess roles){
        return rolesRepository.save(Roles.builder()
                .roleAcess(roles)
                .build());
    }

    @PutMapping("usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Usuario> editarUsuario(@RequestBody @Valid UsuarioEditForm usuarioEditForm,
                                                 @PathVariable("usuarioId") Long usuarioId){

        Usuario usuario = usuarioMapper.usuarioMapperEdit(usuarioEditForm,usuarioId,passwordEncoder);
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

    @DeleteMapping("usuario/encomenda/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletaUsuarioSiteEncomenda(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.deletaUsuario(id));
    };

    @DeleteMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletaUsuarioAdmin(@PathVariable Long id){
        return usuarioService.deletaUsuario(id);
    }

}
