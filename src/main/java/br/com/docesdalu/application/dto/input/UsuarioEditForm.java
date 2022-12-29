package br.com.docesdalu.application.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEditForm {
    private String nome;
    private String confirmPassword;
    private String oldPassword;
    private String senha;
}
