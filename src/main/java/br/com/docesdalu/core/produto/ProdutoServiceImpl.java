package br.com.docesdalu.core.produto;

import br.com.docesdalu.infrastructure.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static br.com.docesdalu.infrastructure.config.sftp.SftpConfig.deletarImagemSftp;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    private ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Page<Produto> buscarProdutos(Pageable paginacao) {
        return produtoRepository.findAll(paginacao);
    }

    @Override
    public Produto buscaProdutoPorId(Long idProduto) {
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new NoSuchElementException("erro"));
    }

    @Override
    public String deletarProduto(Long idProduto) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new NoSuchElementException("Produto nao encontrado"));
        deletarImagemSftp(produto.getPathImagem());
        produtoRepository.deleteById(idProduto);
        return "Deletado com sucesso";
    }
}
