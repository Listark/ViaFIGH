package br.com.ebt.figh.persistence;

import java.util.List;

import br.com.ebt.figh.model.Projeto;

public interface ProjetoDAO {
	
	public void salvar(Projeto projeto);
	public List<Projeto> getProjetos();
	public void excluir(Projeto projeto);
	public List<Projeto> pesquisar(String numeroProjeto);
	
}
