package br.com.ebt.figh.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ebt.figh.model.Cidade;
import br.com.ebt.figh.model.Endereco;
import br.com.ebt.figh.model.Estado;

@Repository
public class LocalizacaoDAOImpl extends CustomHibernateDaoSupport implements LocalizacaoDAO {

	public LocalizacaoDAOImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> getAllEstados() {
		List<Estado> estados = hibernateTemplate.find("from Estado");
		return estados;
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> getAllCidadesByEstado(Integer idEstado) {
		List<Cidade> cidadesList = hibernateTemplate.find(
				"from Cidade c where c.estado.id=?", idEstado);
		return cidadesList;
	}

	public Cidade getCidadeById(Integer idCidade) {
		Cidade cidade = hibernateTemplate.get(Cidade.class, idCidade);
		return cidade;
	}
	
	@SuppressWarnings("unchecked")
	public Estado getEstadoByUF(String uf) {
		List<Estado> estados = hibernateTemplate.find("from Estado e where e.sigla=?", uf);
		return estados.get(0);
	}
	
	public void salvarEndereco(Endereco endereco) {
		hibernateTemplate.saveOrUpdate(endereco);
	}
	
	@SuppressWarnings("unchecked")
	public Estado getEstadoById(Integer id) {
		List<Estado> estados = hibernateTemplate.find("from Estado estado where estado.id=?", id);
		return estados.get(0);
	}
}
