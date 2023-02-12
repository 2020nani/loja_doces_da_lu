package br.com.docesdalu.infrastructure.config.sftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class SftpConfig {

    /* criar canal para acessar servidor sftp */
    public static ChannelSftp setupJsch() {

        Session jschSession = null;

        Channel sftp = null;

        // 10 seconds session timeout
        try {
            JSch jsch = new JSch();
            JSch.setConfig("StrictHostKeyChecking", "no");
            //jsch.setKnownHosts("/home/remote_user/.ssh/known_hosts");
            jschSession = jsch.getSession("remote_user", "localhost", 58898);

            // authenticate using private key
            // jsch.addIdentity(<destino da chave ssh>);

            // authenticate using password
            jschSession.setPassword("password1234");
            jschSession.connect(100000);

            sftp = jschSession.openChannel("sftp");
        }catch (JSchException e) {
            log.error(e.getMessage());
        }

        return  (ChannelSftp) sftp;

    }

    public static InputStream lerArquivo(MultipartFile file) {

        InputStream arquivo = null;
        try {
            arquivo = file.getInputStream();
        }catch (IOException e){
            log.error("Erro ao ler arquivo");
        }
        return arquivo;
    }

    /* armazenar imagem servidor SFTP */
    public static String salvarImagemSftp(InputStream storedImage, String originalFilename) throws SftpException, JSchException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        try {
            log.info("Imagem cadastrada com sucesso");
            String urlAcessoImagem = channelSftp.getHome() + "/" + originalFilename;
            channelSftp.put(storedImage, urlAcessoImagem );
            return originalFilename;
        }catch (SftpException e) {
            log.error("Erro ao armazenar mensagem no sftp");
        }
        channelSftp.exit();

        return "Erro ao armazenar imagem Sftp";
    }

    /* buscar imagem servidor sftp e transferir para pasta local */
    public static String buscarImagemSftp(String originalFilename) {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        String pathDestino = "C:\\Users\\Dell\\Desktop\\lojali\\novoProjeto\\src\\assets\\images\\";
        try {
            channelSftp.connect(50000);
            channelSftp.get(channelSftp.getHome() + "/" + originalFilename, pathDestino + originalFilename);
            log.info("Done");

            return originalFilename;
        }catch (SftpException | JSchException ex){
            log.error(ex.getMessage());
        }

        channelSftp.exit();

        log.info("Done");

        return "";
    }

    /* deletar imagem servidor sftp */
    public static void deletarImagemSftp(String nomeImagem){
        String pathDestino = "C:\\Users\\Dell\\Desktop\\lojali\\novoProjeto\\src\\assets\\images\\";
        Session jschSession = null;

        Channel sftp = null;
        // delete file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        try {
            File file = new File(pathDestino + nomeImagem);
            file.delete();
            channelSftp.connect(50000);

            channelSftp.rm(channelSftp.getHome() + "/" + nomeImagem);

            channelSftp.exit();

            log.info("Done");
        }catch (JSchException | SftpException ex){
            log.error(ex.getMessage());
        }

    }
}
