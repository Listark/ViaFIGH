package br.com.ebt.figh.persistence;

import org.springframework.stereotype.Repository;

import br.com.ebt.figh.model.ModeloTesteDependente;

@Repository
public class ModeloDependenteDAOImpl extends CustomHibernateDaoSupport implements ModeloDependenteDAO {
	
	public void salvar(ModeloTesteDependente modeloTesteDependente) {
		hibernateTemplate.saveOrUpdate(modeloTesteDependente);
	}

}
