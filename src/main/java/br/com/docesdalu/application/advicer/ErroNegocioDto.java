package br.com.docesdalu.application.advicer;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroNegocioDto {
    private String mensagem;
    private HttpStatus statusErro;
    private Date dataErro;
    private String causaErro;
}
