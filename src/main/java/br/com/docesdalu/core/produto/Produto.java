package br.com.docesdalu.core.produto;

import br.com.docesdalu.core.enums.Categoria;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer quantidade;
    private String pathImagem;
}
