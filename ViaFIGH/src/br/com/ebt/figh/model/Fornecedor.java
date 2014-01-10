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
@Table(name = "TB_FORNECEDOR")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String CNPJ;
	private String contato;
	private String telefone;
	private String email;
	private Endereco endereco;
	private List<Usuario> usuarios;

	@SequenceGenerator(name="fornecedorGenerator",
					   sequenceName="SEQ_FORNECEDOR",
					   allocationSize=20)
	@Id	@GeneratedValue(strategy = GenerationType.AUTO,
						generator="fornecedorGenerator")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "FORN_NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	@Column(name = "FORN_CNPJ")
	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	@Column(name = "FORN_CONTATO")
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato.toUpperCase();
	}

	@Column(name = "FORN_TELEFONE")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(name = "FORN_EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toUpperCase();
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public Endereco getEndereco() {
		if(endereco == null)
			this.endereco = new Endereco();
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@OneToMany(mappedBy="fornecedor")
	@JoinColumn(name="FORNECEDOR_ID")
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", CNPJ=" + CNPJ
				+ ", contato=" + contato + ", telefone=" + telefone
				+ ", email=" + email + ", endereco=" + endereco + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Fornecedor)) return false;
		if(this.id == null) return false;
		
		Fornecedor fornecedor = (Fornecedor) obj;
		
		if(this.id.equals(fornecedor.getId())) 
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}
