package br.com.ebt.figh.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.model.Perfil;
import br.com.ebt.figh.model.Usuario;

@Repository
public class UsuarioDAOImpl extends CustomHibernateDaoSupport implements UsuarioDAO{
	
	public Perfil buscarPerfil(Long idPerfil) {
		Perfil perfil = hibernateTemplate.get(Perfil.class, idPerfil);
		return perfil;
	}

	public void excluirUsuario(Usuario usuario){
		hibernateTemplate.delete(usuario);
	}
	
	public void salvarUsuario(Usuario usuario){
		hibernateTemplate.saveOrUpdate(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodosUsuarios() {
		return hibernateTemplate.find("from Usuario");
	}
	
	@SuppressWarnings("unchecked")
	public List<Perfil> getPerfis() {
		return hibernateTemplate.find("from Perfil");
	}
		
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosByFornecedor(Fornecedor fornecedor) {
		Object param[] = new Object[]  {fornecedor};
		String sql = "from Usuario a where a.fornecedor=?";
		
		return hibernateTemplate.find(sql, param);
	}
	
	public Usuario getUsuarioById(Long id) {
		return hibernateTemplate.get(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosByFornecedorId(Long idFornecedor) {
		Object[] param = new Object[] {idFornecedor};
		return hibernateTemplate.find("select usuario from Usuario usuario join " +
									  "usuario.fornecedor forn " +
									  "where forn.id=?", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosTipoCliente() {
		return hibernateTemplate.find("select usuario from Usuario usuario join usuario.perfis perfil " +
				"where perfil.id=1");
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosTipoFornecedor() {
		return hibernateTemplate.find("select usuario from Usuario usuario join usuario.perfis perfil " +
				"where perfil.id=2");
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscar(String nome, String login, String email, Long idPerfil, Long idFornecedor) {
		nome  = "%" + nome + "%";
		login = "%" + login + "%";
		email = "%" + email + "%";
		
		Object[] parametros;
		//se o perfil for zero é pq não houve seleção de perfil na tela de pesquisa e deverá buscar considerando todos os perfis
		if(idPerfil == 0) {
			
			if(idFornecedor == 0) {
				parametros = new Object[] {nome, login, email};
				return hibernateTemplate.find("select usuario from Usuario usuario join usuario.perfis perfil " +
						"where usuario.nome like ? and " +
						"usuario.login like ? and " +
						"usuario.email like ?", parametros);
			} else {
				parametros = new Object[] {nome, login, email, idFornecedor};
				return hibernateTemplate.find("select usuario from Usuario usuario join usuario.perfis perfil " +
						"join usuario.fornecedor fornecedor " +
						"where usuario.nome like ? and " +
						"usuario.login like ? and " +
						"usuario.email like ? and " + 
						"fornecedor.id=?", parametros);
			}
			
		} else if(idPerfil == 1){
			parametros = new Object[] {nome, login, email};
			return hibernateTemplate.find("select usuario from Usuario usuario join usuario.perfis perfil " +
					"where usuario.nome like ? and " +
					"usuario.login like ? and " +
					"usuario.email like ? and " +
					"perfil.id=1", parametros);			
		} else {
			
			if(idFornecedor == 0) {
				parametros = new Object[] {nome, login, email};
				return hibernateTemplate.find("select usuario from Usuario usuario join usuario.perfis perfil " +
						"where usuario.nome like ? and " +
						"usuario.login like ? and " +
						"usuario.email like ? and " +
						"perfil.id=2", parametros);	
			} else {
				parametros = new Object[] {nome, login, email, idFornecedor};
				return hibernateTemplate.find("select usuario from Usuario usuario join usuario.perfis perfil " +
						"join usuario.fornecedor fornecedor " +
						"where usuario.nome like ? and " +
						"usuario.login like ? and " +
						"usuario.email like ? and " +
						"perfil.id=2 and " +
						"fornecedor.id like ?", parametros);				
			}
		
		}

	}
	
}
