package org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTest {
	
	public Usuario usuario;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validador = factory.getValidator(); 
	
	@BeforeEach
	public void start() {
		usuario = new Usuario("Denise Mignoli", "denise@gmail.com", "134652"); //Antes de rodar o teste, ele vai criar isso
	}

	@Test
	void testValidaAtributosNaoRetornaErro() { //o nome do teste tem que ser exatamente daquilo que estamos testando
		Set<ConstraintViolation<Usuario>> validacao = validador.validate(usuario); // é o validador
		assertTrue(validacao.isEmpty()); // não apareceu nenhum erro, então o set é vazio, passou.
	}
	
	@Test
	void testValidaAtributosRetornaErro() {
		Usuario usuarioErro = new Usuario();
		usuarioErro.setNome("Igor Boaz");
		Set<ConstraintViolation<Usuario>> validacao = validador.validate(usuarioErro);
		assertFalse(validacao.isEmpty()); // aqui eu quero que retorne um erro então é false
	}

}
