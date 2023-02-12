package br.com.docesdalu.application.dto.input;

import br.com.docesdalu.core.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.File;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoInput {
    @NotNull
    private String nome;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @NotNull
    @Positive
    private BigDecimal preco;
    @NotNull
    @Positive
    private Integer quantidade;

    private String fileBase64;
}
