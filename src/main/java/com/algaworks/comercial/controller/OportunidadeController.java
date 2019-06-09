package com.algaworks.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {
	
	@Autowired
	private OportunidadeRepository opRepository;
	
	@GetMapping
	public List<Oportunidade> listar() {
		return opRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = opRepository.findById(id);
		
		if(oportunidade.isEmpty()) {
			return ResponseEntity.notFound().build();
		} 
		
		return ResponseEntity.ok(oportunidade.get());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade op) {
		Optional<Oportunidade> opExiste = opRepository.
				findByDescricaoAndNomeProspecto(op.getDescricao(), op.getNomeProspecto());
		
		if(opExiste.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Já existe uma oportunidade para este prospecto com a mesma descricao");
		}
		
		return opRepository.save(op);
	}
	
}
