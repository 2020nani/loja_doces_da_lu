package br.com.docesdalu.application.dto.input;

import br.com.docesdalu.application.config.security.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
public class UsuarioInput {

    private String nome;
    @CPF
    @NotNull
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)")
    private String cpf;
    private String telefone;
    private String senha;
    private List<Roles> roles;

}
