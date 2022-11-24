package br.com.docesdalu.application.mapper;

import br.com.docesdalu.application.dto.input.UsuarioInput;
import br.com.docesdalu.core.usuario.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioMapper {

    public Usuario usuarioMapperInput(UsuarioInput usuarioInput, BCryptPasswordEncoder passwordEncoder){

        return Usuario.builder()
                .nome(Optional.ofNullable(usuarioInput.getNome()).orElse(usuarioInput.getNome()))
                .telefone(Optional.ofNullable(usuarioInput.getTelefone()).orElse(usuarioInput.getTelefone()))
                .senha(passwordEncoder.encode(usuarioInput.getSenha()))
                .roles(usuarioInput.getRoles())
                .build();
    }
}