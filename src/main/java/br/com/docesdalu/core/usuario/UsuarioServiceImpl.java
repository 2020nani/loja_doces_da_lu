package br.com.docesdalu.core.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario) ;
    }
}
