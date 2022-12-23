package br.com.docesdalu.core.pedido;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends ElasticsearchRepository<Pedido, String> {
}
