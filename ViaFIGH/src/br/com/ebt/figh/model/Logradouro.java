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

@Entity
@Table(name = "TB_LOGRADOURO")
public class Logradouro implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String cep;
	private Cidade cidade;
	private List<Endereco> enderecos;
	
	@SequenceGenerator(name="logradouroGenerator",
			   		   sequenceName="SEQ_LOGRADOURO",
			   		   allocationSize=20)
	@Id	@GeneratedValue(strategy = GenerationType.AUTO,
						generator="logradouroGenerator")
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "LOGRAD_NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	@Column(name = "LOGRAD_CEP")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@ManyToOne()
	public Cidade getCidade() {
		return this.cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@OneToMany(mappedBy="logradouro")
	@JoinColumn(name="LOGRADOURO_ID")
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	

}
