package br.com.ebt.figh.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ebt.figh.model.ModeloTestePai;

@Repository
public class ModeloPaiDAOImpl extends CustomHibernateDaoSupport implements ModeloPaiDAO {

	public void salvar(ModeloTestePai modeloPai) {
		hibernateTemplate.saveOrUpdate(modeloPai);
	}
	
	public ModeloTestePai getModeloPaiById(int id) {
		return hibernateTemplate.get(ModeloTestePai.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ModeloTestePai> getTodos() {
		return hibernateTemplate.find("from ModeloTestePai");
	}
	
}
