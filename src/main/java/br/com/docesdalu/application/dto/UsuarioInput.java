package br.com.docesdalu.application.dto;

import br.com.docesdalu.application.config.security.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsuarioInput {

    private String nome;
    private String telefone;
    private String senha;
    private List<Roles> roles;

}
