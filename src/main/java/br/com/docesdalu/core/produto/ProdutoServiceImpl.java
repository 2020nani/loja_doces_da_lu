package br.com.docesdalu.core.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{

    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> buscarProdutos() {
        return null;
    }

    @Override
    public void deletarProduto(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }
}
