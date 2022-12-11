package br.com.docesdalu.application.dto.input;

import br.com.docesdalu.core.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoInput {

    @NotNull
    private Long idProduto;
    @NotNull
    @Email
    private String email;
    @Positive
    private Integer quantidadePedido;
}
