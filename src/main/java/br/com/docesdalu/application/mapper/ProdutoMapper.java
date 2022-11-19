package br.com.docesdalu.application.mapper;

import br.com.docesdalu.application.dto.input.ProdutoInput;
import br.com.docesdalu.core.produto.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto produtoMapperInput(ProdutoInput produtoInput){
        return Produto.builder()
                .nome(produtoInput.getNome())
                .categoria(produtoInput.getCategoria())
                .preco(produtoInput.getPreco())
                .quantidade(produtoInput.getQuantidade())
                .pathImagem("teste")
                .build();
    }
}
