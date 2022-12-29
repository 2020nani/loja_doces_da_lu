package br.com.docesdalu.application.dto.output;

import br.com.docesdalu.application.config.security.Roles;
import br.com.docesdalu.core.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginResponse {
    private Usuario user;
    private String token;
}
