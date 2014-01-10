package br.com.ebt.figh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ebt.figh.model.ModeloTesteDependente;
import br.com.ebt.figh.persistence.ModeloDependenteDAO;

@Service
public class ModeloDependenteService {
	private ModeloDependenteDAO modeloDependenteDAO;

	@Transactional
	public void salvar(ModeloTesteDependente modeloDependente) {
		modeloDependenteDAO.salvar(modeloDependente);
	}
	
	public ModeloDependenteDAO getModeloDependenteDAO() {
		return modeloDependenteDAO;
	}

	@Autowired
	public void setModeloDependenteDAO(ModeloDependenteDAO modeloDependenteDAO) {
		this.modeloDependenteDAO = modeloDependenteDAO;
	}
	
	
}
