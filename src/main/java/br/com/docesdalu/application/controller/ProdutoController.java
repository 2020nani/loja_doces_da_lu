package br.com.docesdalu.application.controller;

import br.com.docesdalu.application.dto.input.ProdutoInput;
import br.com.docesdalu.application.dto.output.ProdutoOutput;
import br.com.docesdalu.application.mapper.ProdutoMapper;
import br.com.docesdalu.core.produto.Produto;
import br.com.docesdalu.core.produto.ProdutoRepository;
import br.com.docesdalu.core.produto.ProdutoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProdutoController {

    private ProdutoServiceImpl produtoService;
    private ProdutoRepository produtoRepository;
    private ProdutoMapper produtoMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("produto")
    private Produto salvarProduto(@Valid @RequestBody ProdutoInput produtoInput){
        return produtoService.salvarProduto(produtoMapper.produtoMapperInput(produtoInput));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("produtos")
    public Page<ProdutoOutput> listaProdutos(
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size,
            @PageableDefault(sort = "nome", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao){
        return produtoRepository.findAll(paginacao)
                .map(produto -> {});
    }
}
