package br.com.ebt.figh.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String numero;
	private Logradouro logradouro;
	private List<Fornecedor> fornecedores;
	
	public Endereco() {
		super();
	}

	public Endereco(Logradouro logradouro) {
		super();
		this.logradouro = logradouro;
	}

	@SequenceGenerator(name="enderecoGenerator",
					   sequenceName="SEQ_ENDERECO",
					   allocationSize=10)
	@Id	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO,
					generator="enderecoGenerator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "END_NUMERO")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public Logradouro getLogradouro() {
		if(this.logradouro == null)
			this.logradouro = new Logradouro();
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	@OneToMany(mappedBy="endereco")
	@JoinColumn(name="ENDERECO_ID")
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Endereco)) return false;
		if(this.id == null) return false;
		
		Endereco endereco = (Endereco) obj;
		
		if(this.id.equals(endereco.getId())) 
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
}
