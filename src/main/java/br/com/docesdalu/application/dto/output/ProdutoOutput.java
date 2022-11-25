package br.com.docesdalu.application.dto.output;

import br.com.docesdalu.core.enums.Categoria;
import br.com.docesdalu.core.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoOutput {
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer quantidade;
    private String pathImagem;

    public ProdutoOutput(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
        this.pathImagem = produto.getPathImagem();
    }
}
