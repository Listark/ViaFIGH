package br.com.ebt.figh.persistence;



import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.web.util.FacesUtil;

@Repository
public class FornecedorDAOImpl extends CustomHibernateDaoSupport implements FornecedorDAO {

	public void salvar(Fornecedor fornecedor) {
		try {
			hibernateTemplate.saveOrUpdate(fornecedor);
		} catch (HibernateException e) {
			FacesUtil.exibirMensagemErro(e.toString());
		}

	}
	
	public void excluir(Fornecedor fornecedor) {
		try {
			hibernateTemplate.delete(fornecedor);
		} catch(HibernateException e) {
			FacesUtil.exibirMensagemErro(e.toString());
		}
	}
	
	public Fornecedor getById(Long id) {
		return hibernateTemplate.get(Fornecedor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> getAllFornecedores() {
		return hibernateTemplate.find("from Fornecedor");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> buscar(String fornecedor, String cnpj, String cep, String uf, String cidade, String contato, String email) {
		fornecedor = "%" + fornecedor + "%";
		cnpj = "%" + cnpj + "%";
		cep = "%" + cep + "%";
		contato = "%" + contato + "%";
		email = "%" + email + "%";
		
		Object[] parametros;
		String query;
		
		if(uf.equals("") && cidade.equals("")) {
			parametros = new Object[] {fornecedor, cnpj, cep, contato, email};
			query = "select f from Fornecedor f where f.nome like ? and f.CNPJ like ? and " +
					"f.endereco.logradouro.cep like ? and f.contato like ? and f.email like ?";
		} else if(cidade.equals("")) {
			parametros = new Object[] {fornecedor, cnpj, uf, cep, contato, email};
			query = "select f from Fornecedor f where f.nome like ? and f.CNPJ like ? and " +
					"f.endereco.logradouro.cidade.estado.sigla like ? and f.endereco.logradouro.cep like ? and " +
					"f.contato like ? and f.email like ?";
		} else {
			parametros = new Object[] {fornecedor, cnpj, uf, cidade, cep, contato, email};
			query = "select f from Fornecedor f where f.nome like ? and f.CNPJ like ? and " +
					"f.endereco.logradouro.cidade.estado.sigla like ? and f.endereco.logradouro.cidade.nome like ? and " +
					"f.endereco.logradouro.cep like ? and " +
					"f.contato like ? and f.email like ?";
		}
		
		return hibernateTemplate.find(query, parametros);

	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> buscar(String nome) {
		nome = "%" + nome + "%";
		Object[] parametros = new Object[] {nome};
		String query = "select fornecedor from Fornecedor fornecedor where fornecedor.nome like ?";
		
		return hibernateTemplate.find(query, parametros);
		
		
	}
}
