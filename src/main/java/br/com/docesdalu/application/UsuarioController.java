package br.com.docesdalu.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("teste")
    public String testeSecurity(){
        return "ok";
    }
}
