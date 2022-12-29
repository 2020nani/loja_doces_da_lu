package br.com.docesdalu.application.mapper;

import br.com.docesdalu.application.dto.input.UsuarioEditForm;
import br.com.docesdalu.application.dto.input.UsuarioInput;
import br.com.docesdalu.application.dto.output.LoginResponse;
import br.com.docesdalu.core.usuario.Usuario;
import br.com.docesdalu.core.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Component
public class UsuarioMapper {

    private final UsuarioRepository usuarioRepository;

    public UsuarioMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario usuarioMapperInput(UsuarioInput usuarioInput, BCryptPasswordEncoder passwordEncoder){

        return Usuario.builder()
                .nome(Optional.ofNullable(usuarioInput.getNome()).orElse(usuarioInput.getNome()))
                .telefone(Optional.ofNullable(usuarioInput.getTelefone()).orElse(usuarioInput.getTelefone()))
                .cpf(Optional.ofNullable(usuarioInput.getCpf()).orElse(usuarioInput.getCpf()))
                .senha(passwordEncoder.encode(usuarioInput.getSenha()))
                .roles(usuarioInput.getRoles())
                .build();
    }

    public LoginResponse usuarioMapperLoginResponse(Usuario usuario, String token) {
        return LoginResponse.builder()
                .user(usuario)
                .token(token)
                .build();
    }

    public Usuario usuarioMapperEdit(UsuarioEditForm usuarioEditForm, Long usuarioId, BCryptPasswordEncoder passwordEncoder) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsernameNotFoundException("Nao ha usuario cadastrado id: " + usuarioId));
        if(!passwordEncoder.matches(usuarioEditForm.getOldPassword(),usuario.getSenha())){
            throw new IllegalArgumentException("Senha difere da senha atual");
        }

        return Usuario.builder()
                .id(usuarioId)
                .nome(Optional.ofNullable(usuarioEditForm.getNome()).orElse(usuario.getNome()))
                .telefone(usuario.getTelefone())
                .senha(Optional.ofNullable(passwordEncoder.encode(usuarioEditForm.getSenha())).orElse(usuario.getSenha()))
                .cpf(usuario.getCpf())
                .roles(usuario.getRoles())
                .build();
    }
}