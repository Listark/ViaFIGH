package br.com.ebt.figh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ebt.figh.model.Projeto;
import br.com.ebt.figh.persistence.ProjetoDAO;

@Service
public class ProjetoService {
	private ProjetoDAO dao;
	
	@Transactional
	public void salvar(Projeto projeto) {
		dao.salvar(projeto);
	}
	
	@Transactional
	public List<Projeto> getProjetos() {
		return dao.getProjetos();
	}
	
	@Transactional
	public void excluir(Projeto projeto) {
		dao.excluir(projeto);
	}
	
	@Transactional
	public List<Projeto> pesquisar(String numeroProjeto) {
		return dao.pesquisar(numeroProjeto);
	}

	public ProjetoDAO getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(ProjetoDAO dao) {
		this.dao = dao;
	}
	
	
}
