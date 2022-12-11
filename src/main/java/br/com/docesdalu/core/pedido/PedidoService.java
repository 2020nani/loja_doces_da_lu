package br.com.docesdalu.core.pedido;

import java.util.List;

public interface PedidoService {
    String salvarPedido(List<Pedido> pedido);
    String deletarPedido(String pedidoId);
    Iterable<Pedido> buscarPedidos();
    Pedido buscaPedidoPorId(String pedidoId);
}
