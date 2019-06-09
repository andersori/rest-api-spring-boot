package com.algaworks.comercial.test.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OportunidadeRepositoryTest {
	
	@Autowired
	private OportunidadeRepository opRepository;
	
	@Test
	public void whenCalledSave_thenCorrectNumberOfUsers() {
		Oportunidade op = new Oportunidade();
		op.setDescricao("test");
		op.setNomeProspecto("test.org");
		op.setValor(new BigDecimal(490000));
		
		opRepository.save(op);
		
		List<Oportunidade> oportunidades = (List<Oportunidade>) opRepository.findAll();
		
		assertEquals(oportunidades.size(), 1);
	}
}
