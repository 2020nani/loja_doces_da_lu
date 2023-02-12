package br.com.docesdalu.application.dto.output;

import br.com.docesdalu.core.enums.Categoria;
import br.com.docesdalu.core.produto.Produto;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;

import static br.com.docesdalu.infrastructure.config.sftp.SftpConfig.buscarImagemSftp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoOutput {
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer quantidade;
    private String imagem;

    public ProdutoOutput(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
        this.imagem = buscarImagemSftp(produto.getPathImagem());
    }
}
