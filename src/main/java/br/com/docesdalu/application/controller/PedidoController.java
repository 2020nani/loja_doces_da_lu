package br.com.docesdalu.application.controller;

import br.com.docesdalu.application.dto.input.PedidoInput;
import br.com.docesdalu.application.mapper.PedidoMapper;
import br.com.docesdalu.core.pedido.Pedido;
import br.com.docesdalu.core.pedido.PedidoServiceImpl;
import br.com.docesdalu.core.usuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PedidoController {

    private PedidoMapper pedidoMapper;
    private PedidoServiceImpl pedidoService;

    public PedidoController(PedidoMapper pedidoMapper, PedidoServiceImpl pedidoService) {
        this.pedidoMapper = pedidoMapper;
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedidos")
    public String salvarPedidos(@Valid @RequestBody List<PedidoInput> pedidoInput, @AuthenticationPrincipal Usuario usuario){
        List<Pedido> pedidos = pedidoInput.stream().map(p -> {
            return pedidoMapper.pedidoInputMapper(p,usuario);
        }).collect(Collectors.toList());

        pedidoService.salvarPedido(pedidos);
        return "ok";
    }

    @GetMapping("/pedidos")
    public Iterable<Pedido> buscarPedidos(){
        return pedidoService.buscarPedidos();
    }

    @GetMapping("/pedidos/{id}")
    public Pedido buscarPedidoPorId(@PathVariable String id){
        return pedidoService.buscaPedidoPorId(id);
    }
}
