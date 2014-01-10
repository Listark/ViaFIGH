package br.com.ebt.figh.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import br.com.ebt.figh.model.Projeto;
import br.com.ebt.figh.web.util.FacesUtil;

@Repository
public class ProjetoDAOImpl extends CustomHibernateDaoSupport implements ProjetoDAO {
	
	@Override
	public void salvar(Projeto projeto) {
		try {
			hibernateTemplate.saveOrUpdate(projeto);
		} catch(HibernateException ex) {
			FacesUtil.exibirMensagemErro(ex.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> getProjetos() {
		List<Projeto> projetos = hibernateTemplate.find("from Projeto");
		return projetos;
	}
	
	@Override
	public void excluir(Projeto projeto) {
		hibernateTemplate.delete(projeto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> pesquisar(String numeroProjeto) {
		numeroProjeto = "%" + numeroProjeto + "%";
		Object[] param = new Object[] {numeroProjeto};
		
		List<Projeto> projetosPesquisa = hibernateTemplate.find("select projeto from Projeto projeto " +
				"where projeto.numeroProjeto like ?", param);
		
		return projetosPesquisa;
		
	}
	
}
