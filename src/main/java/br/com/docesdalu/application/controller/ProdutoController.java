package br.com.docesdalu.application.controller;

import br.com.docesdalu.application.dto.input.ProdutoEdit;
import br.com.docesdalu.application.dto.input.ProdutoInput;
import br.com.docesdalu.application.dto.output.ProdutoOutput;
import br.com.docesdalu.application.mapper.ProdutoMapper;
import br.com.docesdalu.core.produto.Produto;
import br.com.docesdalu.core.produto.ProdutoServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProdutoController {

    private ProdutoServiceImpl produtoService;
    private ProdutoMapper produtoMapper;

    public ProdutoController(ProdutoServiceImpl produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @PostMapping("/produtos")
    @Transactional
    private Produto salvarProduto(@Valid @RequestBody ProdutoInput produtoInput){
        return produtoService.salvarProduto(produtoMapper.produtoMapperInput(produtoInput));
    }

    @GetMapping("produtos")
    public Page<ProdutoOutput> listaProdutos(
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size,
            @PageableDefault(sort = "nome", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){
        return produtoService.buscarProdutos(paginacao)
                .map(ProdutoOutput::new);
    }

    @GetMapping("produtos/{id}")
    public Produto buscaProduto(@PathVariable Long id){
        return produtoMapper.produtoOutput(produtoService.buscaProdutoPorId(id));
    }

    @PutMapping("produtos/{id}")
    public Produto atualizarProduto(@PathVariable("id") Long id, @RequestBody ProdutoEdit produtoEdit){
        Produto produtoAtual = produtoService.buscaProdutoPorId(id);
        return produtoService.atualizarProduto(produtoMapper.produtoMapperEdit(produtoEdit,produtoAtual));
    }

    @DeleteMapping("produtos/{id}")
    public String deletarProduto(@PathVariable("id") Long id) {
        return produtoService.deletarProduto(id);
    }
}
