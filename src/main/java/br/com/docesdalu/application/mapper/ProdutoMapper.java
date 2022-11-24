package br.com.docesdalu.application.mapper;

import br.com.docesdalu.application.dto.input.ProdutoEdit;
import br.com.docesdalu.application.dto.input.ProdutoInput;
import br.com.docesdalu.core.produto.Produto;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public Produto produtoMapperEdit(ProdutoEdit produtoEdit, Produto produtoAtual){
        return Produto.builder()
                .nome(Optional.ofNullable(produtoEdit.getNome()).orElse(produtoAtual.getNome()))
                .categoria(Optional.ofNullable(produtoEdit.getCategoria()).orElse(produtoAtual.getCategoria()))
                .preco(Optional.ofNullable(produtoEdit.getPreco()).orElse(produtoAtual.getPreco()))
                .quantidade(Optional.ofNullable(produtoEdit.getQuantidade()).orElse(produtoAtual.getQuantidade()))
                .pathImagem(Optional.ofNullable("teste").orElse(produtoAtual.getPathImagem()))
                .build();
    }

    public Produto produtoOutput(Produto produto){
        return Produto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .preco(produto.getPreco())
                .quantidade(produto.getQuantidade())
                .pathImagem("teste")
                .build();
    }
}
