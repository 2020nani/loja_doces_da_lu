package br.com.docesdalu.core.pedido;

public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(String message) {
        super(message);
    }

}
