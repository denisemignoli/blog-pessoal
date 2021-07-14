package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // basicamente equivale a criação de uma tabela
@Table(name = "tb_postagem")
public class Postagem {

	@Id // Define que o atributo abaixo é uma ID, chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Define o auto_increment com a estratégia "GenerationType.IDENTITY"
	private long id;

	@NotNull(message = "O título não pode ser nulo") // Define que não pode ser nulo
	@Size(min = 2, max = 100) // Define tamanho do título com mínimo e máximo
	private String titulo;

	@NotNull(message = "O texto não pode ser nulo")
	@Size(min = 10, max = 600)
	private String texto;

	@Temporal(TemporalType.TIMESTAMP) // Pega a data da postagem
	private Date date = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	@JsonIgnoreProperties("postagem") // ignorar a propriedade postagem do objeto tema
	private Tema tema;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
