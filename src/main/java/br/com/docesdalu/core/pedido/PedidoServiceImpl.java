package br.com.docesdalu.core.pedido;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private RabbitTemplate template;
    @Value("${encomenda.exchange}")
    public String EXCHANGE ;
    @Value("${encomenda.key}")
    public String ROUTING_KEY ;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public String salvarPedido(List<Pedido> pedido) {

        try {
            template.convertAndSend(EXCHANGE, ROUTING_KEY, pedido);
            pedidoRepository.saveAll(pedido);
        }
        catch (Exception e){
            return "Erro ao processar pedidos ";
        }
        return "Pedido realizado, status: ";
    }

    @Override
    public String deletarPedido(String pedidoId) {
        pedidoRepository.deleteById(pedidoId);
        return "ok";
    }

    @Override
    public Iterable<Pedido> buscarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido buscaPedidoPorId(String pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNotFoundException("Erro ao buscar pedido com id: " + pedidoId));
    }
}
