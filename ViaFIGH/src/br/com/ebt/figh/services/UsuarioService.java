package br.com.ebt.figh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.model.Perfil;
import br.com.ebt.figh.model.Usuario;
import br.com.ebt.figh.persistence.UsuarioDAO;

@Service
public class UsuarioService {

	UsuarioDAO usuarioDAO;
	
	@Transactional
	public void excluirUsuario(Usuario usuario){
		usuarioDAO.excluirUsuario(usuario);
	}
	
	@Transactional
	public void salvarUsuario(Usuario usuario) {
		usuarioDAO.salvarUsuario(usuario);
	}

	@Transactional
	public List<Usuario> buscarTodosUsuarios() {
		return usuarioDAO.buscarTodosUsuarios();
	}
	
	@Transactional
	public List<Usuario> buscar(String nome, String login, String email, Long idPerfil, Long idFornecedor) {
		return usuarioDAO.buscar(nome, login, email, idPerfil, idFornecedor);
	}
	
	@Transactional
	public Perfil buscarPerfil(Long idPerfil) {
		return usuarioDAO.buscarPerfil(idPerfil);
	}
	
	@Transactional
	public List<Perfil> getPerfis() {
		return usuarioDAO.getPerfis();
	}
	
	@Transactional
	public List<Usuario> getUsuariosByFornecedor(Fornecedor fornecedor) {
		return usuarioDAO.getUsuariosByFornecedor(fornecedor);
	}
	
	@Transactional
	public List<Usuario> getUsuariosByFornecedorId(Long idFornecedor) {
		return usuarioDAO.getUsuariosByFornecedorId(idFornecedor);
	}
	
	@Transactional
	public Usuario getUsuarioById(Long id) {
		return usuarioDAO.getUsuarioById(id);
	}
	
	@Transactional
	public List<Usuario> getUsuariosTipoCliente() {
		return usuarioDAO.getUsuariosTipoCliente();
	}
	
	@Transactional
	public List<Usuario> getUsuariosTipoFornecedor() {
		return usuarioDAO.getUsuariosTipoFornecedor();
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	@Autowired
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
