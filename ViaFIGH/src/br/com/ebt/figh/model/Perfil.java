package br.com.ebt.figh.model;

import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_perfil")
public class Perfil implements java.io.Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_perfil")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "DESCR")
	private String descr;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "tb_usuario_perfil", joinColumns = @JoinColumn(name = "id_perfil"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@Transient
	public String getAuthority() {
		return this.descricao;
	}

	@Transient
	public int compareTo(Object o) {
		return this.compareTo(o);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Perfil)) return false;
		if(this.id == null) return false;
		
		Perfil perfil = (Perfil) obj;
		
		if(this.id.equals(perfil.getId())) 
			return true;
		return false;	
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public String toString() {
		return getId() + "-" + getDescricao() + "-" + getUsuarios();
	}

}