package br.com.ebt.figh.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ESTADO")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String sigla;
	private List<Cidade> cidades;

	@SequenceGenerator(name="estadoGenerator",
					   sequenceName="SEQ_ESTADO",
					   allocationSize=10)
	@GeneratedValue(strategy = GenerationType.AUTO,
					generator="estadoGenerator")
	@Id	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "ESTADO_NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ESTADO_SIGLA")
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@OneToMany(mappedBy = "estado")
	@JoinColumn(name="ESTADO_ID")
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public String toString() {
		return sigla + "-" + nome;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof Estado)) return false;
		if(this.id == null) return false;
		
		Estado estado = (Estado) other;
		
		if(this.id.equals(estado.getId()))
			return true;
		return false;
	}
	
	public int hashCode() {
		return this.id.hashCode();
	}
}
