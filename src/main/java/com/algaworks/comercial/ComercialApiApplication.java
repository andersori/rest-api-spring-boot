package com.algaworks.comercial;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;

@SpringBootApplication
public class ComercialApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ComercialApiApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(OportunidadeRepository opRepository) throws Exception {
        return (String[] args) -> {
        	Oportunidade op1 = new Oportunidade();
    		op1.setDescricao("test1");
    		op1.setNomeProspecto("test1.org");
    		op1.setValor(new BigDecimal(490000));
    		
    		Oportunidade op2 = new Oportunidade();
    		op2.setDescricao("test2");
    		op2.setNomeProspecto("test2.org");
    		op2.setValor(new BigDecimal(490000));
    		
    		opRepository.save(op1);
    		opRepository.save(op2);
        };
    }
}
