package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // é o cara que liga o banco de dados com o controlador
public interface PostagemRepository extends JpaRepository<Postagem, Long> { // <NomeDaClasseModel, TipoChavePrimaria>

	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo); // sempre usar um atributo da tabela
																				// postagem
	// método personalizado
}
