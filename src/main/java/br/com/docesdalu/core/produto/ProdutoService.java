package br.com.docesdalu.core.produto;

import java.util.List;

public interface ProdutoService {
    public Produto salvarProduto(Produto produto);
    public Produto atualizarProduto(Produto produto);
    public List<Produto> buscarProdutos();
    public void deletarProduto(Long idProduto);
}
