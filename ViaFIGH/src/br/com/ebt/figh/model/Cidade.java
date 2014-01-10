package br.com.ebt.figh.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
* Comentário Teste
*/

@Entity
@Table(name = "TB_CIDADE")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Estado estado;
	private List<Logradouro> logradouros;

	@SequenceGenerator(name="cidadeGenerator",
					   sequenceName="SEQ_CIDADE",
					   allocationSize=20)
	@Id	@GeneratedValue(strategy = GenerationType.AUTO,
						generator="cidadeGenerator")
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CID_NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	@ManyToOne()
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@OneToMany(mappedBy="cidade")
	@JoinColumn(name="CIDADE_ID")
	public List<Logradouro> getLogradouros() {
		return logradouros;
	}

	public void setLogradouros(List<Logradouro> logradouros) {
		this.logradouros = logradouros;
	}
	
	public String toString() {
		return id + "-" + nome;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof Cidade)) return false;
		if(this.id == null) return false;
		
		Cidade cidade = (Cidade) other;
		
		if(this.id.equals(cidade.getId()))
			return true;
		return false;
	}
	
	public int hashCode() {
		return this.id.hashCode();
	}
	
}
