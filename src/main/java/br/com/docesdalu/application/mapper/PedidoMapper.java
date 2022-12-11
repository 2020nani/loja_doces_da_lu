package br.com.docesdalu.application.mapper;

import br.com.docesdalu.application.dto.input.PedidoInput;
import br.com.docesdalu.core.enums.StatusPedido;
import br.com.docesdalu.core.pedido.Pedido;
import br.com.docesdalu.core.produto.Produto;
import br.com.docesdalu.core.produto.ProdutoServiceImpl;
import br.com.docesdalu.core.usuario.Usuario;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class PedidoMapper {

    private ProdutoServiceImpl produtoService;

    public PedidoMapper(ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    public Pedido pedidoInputMapper(PedidoInput pedidoInput, Usuario usuario){
        Produto produto = produtoService.buscaProdutoPorId(pedidoInput.getIdProduto());
        BigDecimal quantidade = new BigDecimal(pedidoInput.getQuantidadePedido());
        return Pedido.builder()
                .id(UUID.randomUUID().toString())
                .usuario(usuario)
                .email(pedidoInput.getEmail())
                .produto(produto)
                .quantidadePedido(pedidoInput.getQuantidadePedido())
                .statusPedido(StatusPedido.AGUARDANDO_ATENDIMENTO.toString())
                .valorTotal(quantidade.doubleValue() * produto.getPreco().doubleValue())
                .build();
    }
}
