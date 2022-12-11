package br.com.docesdalu.core.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {
    public Produto salvarProduto(Produto produto);
    public Produto atualizarProduto(Produto produto);
    public Page<Produto> buscarProdutos(Pageable paginacao);
    public Produto buscaProdutoPorId(Long idProduto);
    public String deletarProduto(Long idProduto);
}
