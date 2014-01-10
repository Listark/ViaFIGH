package br.com.ebt.figh.web.control;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ebt.figh.model.ModeloTesteDependente;
import br.com.ebt.figh.services.ModeloDependenteService;

@Controller
@ViewScoped
@ManagedBean(name="modeloDependenteController")
public class ModeloDependenteController {
	private ModeloTesteDependente modeloDependente;
	private ModeloDependenteService modeloDependenteService;

	public ModeloTesteDependente getModeloDependente() {
		if(modeloDependente == null) 
			return new ModeloTesteDependente();
		else 
			return modeloDependente;
	}

	public void setModeloDependente(ModeloTesteDependente modeloDependente) {
		this.modeloDependente = modeloDependente;
	}

	public ModeloDependenteService getModeloDependenteService() {
		return modeloDependenteService;
	}
	
	@Autowired
	public void setModeloDependenteService(
			ModeloDependenteService modeloDependenteService) {
		this.modeloDependenteService = modeloDependenteService;
	}

}
