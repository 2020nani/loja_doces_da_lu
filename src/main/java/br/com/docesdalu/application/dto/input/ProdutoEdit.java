package br.com.docesdalu.application.dto.input;

import br.com.docesdalu.core.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.File;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEdit {
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Positive
    private BigDecimal preco;
    @Positive
    private Integer quantidade;
    private File imagem;
}
