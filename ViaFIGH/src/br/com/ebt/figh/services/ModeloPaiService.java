package br.com.ebt.figh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ebt.figh.model.ModeloTestePai;
import br.com.ebt.figh.persistence.ModeloPaiDAO;

@Service
public class ModeloPaiService {
	private ModeloPaiDAO modeloPaiDAO;

	@Transactional
	public void salvar(ModeloTestePai modeloPai) {
		modeloPaiDAO.salvar(modeloPai);
	}
	
	@Transactional
	public ModeloTestePai getModeloPaiById(int id) {
		return modeloPaiDAO.getModeloPaiById(id);
	}
	
	@Transactional
	public List<ModeloTestePai> getTodos() {
		return modeloPaiDAO.getTodos();
	}
	
	public ModeloPaiDAO getModeloPaiDAO() {
		return modeloPaiDAO;
	}

	@Autowired
	public void setModeloPaiDAO(ModeloPaiDAO modeloPaiDAO) {
		this.modeloPaiDAO = modeloPaiDAO;
	}
	
	
	
}
 