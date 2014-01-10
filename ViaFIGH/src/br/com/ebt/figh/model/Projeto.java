package br.com.ebt.figh.model;

import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TB_PROJETO")
public class Projeto {
	private Long id;
	private String numeroProjeto;
	private String descricao;
	private Date dataInicio;
	private String responsavel;
	private List<Usuario> usuarios;

	@SequenceGenerator(name="projetoGenerator",
			sequenceName="SEQ_PROJETO",
			initialValue=0, allocationSize=10)
	@Id @GeneratedValue(generator="projetoGenerator",
			strategy=GenerationType.AUTO)
	
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="DESCRICAO")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	@Column(name="DATA_INICIO")
	@Temporal(TemporalType.DATE)
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Column(name="RESPONSAVEL_EBT")
	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel.toUpperCase();
	}
	
	@Column(name="NUMERO_PROJETO")
	public String getNumeroProjeto() {
		return numeroProjeto;
	}

	public void setNumeroProjeto(String numeroProjeto) {
		this.numeroProjeto = numeroProjeto.toUpperCase();
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value=FetchMode.SUBSELECT)
	@JoinTable(name = "TB_PROJETO_USUARIO", joinColumns = @JoinColumn(name="ID_PROJETO"), inverseJoinColumns = @JoinColumn(name="ID_USUARIO"))
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Projeto)) return false;
		if(this.id == null) return false;
		
		Projeto projeto = (Projeto) obj;
		
		if(this.id.equals(projeto.getId())) 
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	@Override
	public String toString() {
		return getId() + "-" + getDescricao() + "-" + getNumeroProjeto() + "-" + getResponsavel() + "-" +
				getDataInicio().toString() + "-" + getUsuarios();
	}


}
