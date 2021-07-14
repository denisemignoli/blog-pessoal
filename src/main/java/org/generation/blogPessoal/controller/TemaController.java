package org.generation.blogPessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")  // aceita requisições de qualquer lugar 
@RequestMapping("/tema") // caminho aplicado para todos os métodos aqui dentro
public class TemaController {

	@Autowired // incorpora a classe PostagemRepository
	private TemaRepository temaRepository; // "objeto" da interface repository
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){ // ResponseEntity > devolve uma resposta ao usuário, que nesse caso é de uma lista
		return ResponseEntity.ok(temaRepository.findAll()); // o "objeto" chama através de um método já definido no JPArepository
	}
	
	@GetMapping("/{id}") // já temos um GetMapping, então especificamos o URI aqui para poder diferenciar
	public ResponseEntity<Tema> getById(@PathVariable long id){ // estamos buscando por id, o caminho é variável de acordo com o id que o usuário colocar
		return temaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) //a expressão lambda vai mapear um objeto de resposta e mostrar o ok, caso tenha dado tudo certo
				.orElse(ResponseEntity.notFound().build()); // Ex: 404, caso não for encontrado
	}
	
	@GetMapping("/descricao/{descricao}") // aqui especificamos mais um caminho pq o Java não vai saber diferenciar somente as chaves
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){ // é lista pq trabalhamos com a possibilidade de trazer mais coisas
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Tema> criarTema(@Valid @RequestBody Tema tema){ // não é lista pq postamos uma coisa de cada vez | RequestBody > pede o corpo da requisição do tipo Tema chamado tema
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema)); //pego o corpo da requisção e chaam o método de salvar para salvar o tema que criamos em cima
	}
	
	@PutMapping // aqui precisamos necessariamente informar o id no Postman
	public ResponseEntity<Tema> alterarTema(@RequestBody Tema tema){
		return ResponseEntity.ok(temaRepository.save(tema));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) { // void pq não precisa devolver nada
		temaRepository.deleteById(id);
	}
	
}
