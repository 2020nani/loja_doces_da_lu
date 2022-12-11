package br.com.docesdalu.core.pedido;

import br.com.docesdalu.core.produto.Produto;
import br.com.docesdalu.core.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "pedido_index")
public class Pedido {
    @Id
    private String id;

    @Field(type = FieldType.Object, name = "usuario")
    private Usuario usuario;

    @Field(type = FieldType.Object, name = "produto")
    private Produto produto;

    @Field(type = FieldType.Text, name = "email")
    private String email;

    @Field(type = FieldType.Integer, name = "quantidade_pedido")
    private Integer quantidadePedido;

    @Field(type = FieldType.Double, name = "valor_total")
    private Double valorTotal;

    @Field(type = FieldType.Text, name = "status_pedido")
    private String statusPedido;

    @Field(type = FieldType.Date, name = "data_pedido")
    private Date dataPedido = new Date();
}
