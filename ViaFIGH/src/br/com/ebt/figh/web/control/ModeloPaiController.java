package br.com.ebt.figh.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ebt.figh.model.ModeloTesteDependente;
import br.com.ebt.figh.model.ModeloTestePai;
import br.com.ebt.figh.services.ModeloPaiService;

@Controller
@ViewScoped
@ManagedBean(name = "modeloPaiController")
public class ModeloPaiController {
	private ModeloTestePai modeloPai;
	private ModeloTesteDependente modeloDependente;
	private ModeloPaiService modeloPaiService;
	private List<ModeloTesteDependente> dependentes = new ArrayList<ModeloTesteDependente>();
	private List<ModeloTestePai> pais;
	
	@PostConstruct
	public void init() {
		this.pais = modeloPaiService.getTodos();
	}
	
	public void salvar(ActionEvent event) {
		modeloDependente.setModeloPai(modeloPai);
		dependentes.add(modeloDependente);
		modeloPai.setDependentes(dependentes);
		modeloPaiService.salvar(modeloPai);
	}
	
	public ModeloTestePai getModeloPai() {
		if(this.modeloPai == null)
			this.modeloPai = new ModeloTestePai();
		return modeloPai;
	}

	public void setModeloPai(ModeloTestePai modeloPai) {
		this.modeloPai = modeloPai;
	}

	public ModeloPaiService getModeloPaiService() {
		return modeloPaiService;
	}
	
	public ModeloTesteDependente getModeloDependente() {
		if(this.modeloDependente == null)
			this.modeloDependente = new ModeloTesteDependente();
		return modeloDependente;
	}

	public void setModeloDependente(ModeloTesteDependente modeloDependente) {
		this.modeloDependente = modeloDependente;
	}
	
	public List<ModeloTesteDependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<ModeloTesteDependente> dependentes) {
		this.dependentes = dependentes;
	}

	@Autowired
	public void setModeloPaiService(ModeloPaiService modeloPaiService) {
		this.modeloPaiService = modeloPaiService;
	}

	public List<ModeloTestePai> getPais() {
		return pais;
	}

	public void setPais(List<ModeloTestePai> pais) {
		this.pais = pais;
	}

}
