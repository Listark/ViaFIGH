package br.com.ebt.figh.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MODELO_DEPENDENTE")
public class ModeloTesteDependente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;

	@ManyToOne(cascade=CascadeType.ALL)
	private ModeloTestePai modeloPai;
	
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

	public ModeloTestePai getModeloPai() {
		return modeloPai;
	}

	public void setModeloPai(ModeloTestePai modeloPai) {
		this.modeloPai = modeloPai;
	}

	public String toString() {
		return id + "-" + nome; 
	}
	
}
