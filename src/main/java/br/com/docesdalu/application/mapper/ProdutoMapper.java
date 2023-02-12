package br.com.docesdalu.application.mapper;

import br.com.docesdalu.application.dto.input.ProdutoEdit;
import br.com.docesdalu.application.dto.input.ProdutoInput;
import br.com.docesdalu.core.produto.Produto;
import br.com.docesdalu.infrastructure.config.sftp.SftpConfig;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static br.com.docesdalu.application.utils.ConverteBase64Multipartfile.base64ToMultipartFile;
import static br.com.docesdalu.infrastructure.config.sftp.SftpConfig.lerArquivo;

@Component
public class ProdutoMapper {

    public Produto produtoMapperInput(ProdutoInput produtoInput) throws JSchException, SftpException {
        MultipartFile file = base64ToMultipartFile(produtoInput.getFileBase64());

        return Produto.builder()
                .nome(produtoInput.getNome())
                .categoria(produtoInput.getCategoria())
                .preco(produtoInput.getPreco())
                .quantidade(produtoInput.getQuantidade())
                .pathImagem(SftpConfig.salvarImagemSftp(lerArquivo(file), file.getOriginalFilename()))
                .build();
    }

    public Produto produtoMapperEdit(ProdutoEdit produtoEdit, Produto produtoAtual){
        return Produto.builder()
                .nome(Optional.ofNullable(produtoEdit.getNome()).orElse(produtoAtual.getNome()))
                .categoria(Optional.ofNullable(produtoEdit.getCategoria()).orElse(produtoAtual.getCategoria()))
                .preco(Optional.ofNullable(produtoEdit.getPreco()).orElse(produtoAtual.getPreco()))
                .quantidade(Optional.ofNullable(produtoEdit.getQuantidade()).orElse(produtoAtual.getQuantidade()))
                .pathImagem(Optional.ofNullable("teste").orElse(produtoAtual.getPathImagem()))
                .build();
    }

    public Produto produtoOutput(Produto produto){
        return Produto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .preco(produto.getPreco())
                .quantidade(produto.getQuantidade())
                .pathImagem("teste")
                .build();
    }
}
