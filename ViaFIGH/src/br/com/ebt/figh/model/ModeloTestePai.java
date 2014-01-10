package br.com.ebt.figh.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MODELO_PAI")
public class ModeloTestePai {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nome;
	
	@OneToMany(mappedBy="modeloPai", cascade=CascadeType.ALL)
	@JoinColumn(name="MODELOPAI_ID")
	private List<ModeloTesteDependente> dependentes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ModeloTesteDependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<ModeloTesteDependente> dependentes) {
		this.dependentes = dependentes;
	}

	public String toString() {
		return this.id + "-" + this.nome;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof ModeloTestePai)) return false;
		
		ModeloTestePai pai = (ModeloTestePai) other;

		if(id == null) return false;
		
		if(id == pai.getId())
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}
