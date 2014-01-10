package br.com.ebt.figh.persistence;

import java.util.List;

import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.model.Perfil;
import br.com.ebt.figh.model.Usuario;

public interface UsuarioDAO {
	
	public void excluirUsuario(Usuario usuario);
	public Perfil buscarPerfil(Long idPerfil);
	public void salvarUsuario(Usuario usuario);
	public Usuario getUsuarioById(Long id);
	public List<Usuario>buscarTodosUsuarios();
	public List<Usuario>buscar(String nome, String login, String email, Long idPerfil, Long idFornecedor);
	public List<Perfil> getPerfis();
	public List<Usuario> getUsuariosByFornecedor(Fornecedor fornecedor);
	public List<Usuario> getUsuariosByFornecedorId(Long idFornecedor);
	public List<Usuario> getUsuariosTipoCliente();
	public List<Usuario> getUsuariosTipoFornecedor();
	
}
