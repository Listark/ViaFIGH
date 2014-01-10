package br.com.ebt.figh.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements java.io.Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="usuarioGenerator",
					   sequenceName="SEQ_USUARIO")	
	@Id	@GeneratedValue(strategy=GenerationType.AUTO,
						generator="usuarioGenerator")
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "login")
	private String login;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne()
	private Fornecedor fornecedor;

	@ManyToMany()
	@JoinTable(name="TB_PROJETO_USUARIO", joinColumns = @JoinColumn(name="ID_USUARIO"), inverseJoinColumns = @JoinColumn(name="ID_PROJETO"))
	private List<Projeto> projetos;
	
	private boolean ativo = true;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "tb_usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private List<Perfil> perfis = new ArrayList<Perfil>();

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> lista = new ArrayList<GrantedAuthority>();
		for (Perfil perfil : getPerfis()) {
			lista.add(new GrantedAuthorityImpl(perfil.getAuthority()));
		}
		return lista;
	}

	@Transient
	public String getPassword() {
		return this.senha;
	}

	@Transient
	public String getUsername() {
		return this.login;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return this.ativo;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return this.ativo;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return this.ativo;
	}

	@Transient
	public boolean isEnabled() {
		return this.ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.toUpperCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toUpperCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Usuario)) return false;
		if(this.id == null) return false;
		
		Usuario usuario= (Usuario) obj;
		
		if(this.id.equals(usuario.getId())) 
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	@Override
	public String toString() {
		return getId() + "-" + getDescricao() + "-" + getEmail() + "-" 
				+ getLogin() + "-" + getNome() + "-" + getPassword() + "-"
				+ getUsername() + "-" + getSenha() + "-" + ativo;
	}


}