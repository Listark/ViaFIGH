package br.com.ebt.figh.web.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.model.Perfil;
import br.com.ebt.figh.model.Usuario;
import br.com.ebt.figh.services.FornecedorService;
import br.com.ebt.figh.services.UsuarioService;
import br.com.ebt.figh.web.util.FacesUtil;

@Controller
@ViewScoped
@ManagedBean(name = "usuarioController")
public class UsuarioController {
	private static final String DEFAULT_PASSWORD = "123456";
	private static final String SAVE_SUCESS = "Usuário cadastrado com sucesso.";
	private static final String DELETE_SUCESS = "Usuário exlcuído com sucesso.";
	private static final String EDIT_SUCESS = "Usuário editado com sucesso.";
	private static final String EDIT_CANCEL = "Acão Cancelada !";
	private static final String URL_EDIT_USER = "/paginas/admin/cadUsuario/editarUsuario.jsf";
	private static final String URL_LIST_USUARIO = "/paginas/admin/cadUsuario/crudUsuarios.jsf";
	private Usuario usuario;
	private UsuarioService usuarioService;
	private FornecedorService fornecedorService;
	private Perfil perfil;
	private Long idPerfil;
	private boolean hideComboForn;
	private Long idFornecedor;
	private Fornecedor fornecedor;
	private List<Perfil> perfis;
	private List<Usuario> usuarios;
	private Usuario usuarioSelected;
	
	public static final String index = "/paginas/inicio.jsf";

	@PostConstruct
	public void getAllUsers() {
		usuarios = usuarioService.buscarTodosUsuarios();
		perfis = usuarioService.getPerfis();

		hideComboForn = true;
	}

	public void editar() {
		if(idPerfil == 1)
			idPerfil = null;
 		FacesUtil.getNavigationHandler().handleNavigation(FacesUtil.getFacesContext(), null, URL_EDIT_USER + "?faces-redirect=true");
	}

	public void cancelarEdicao() {
		limparBean();
		getAllUsers();
		FacesUtil.getNavigationHandler().handleNavigation(FacesUtil.getFacesContext(), null, URL_LIST_USUARIO + "?faces-redirect=true");
		FacesUtil.exibirMensagemSucesso(EDIT_CANCEL);
	}

	public void excluirUsuario(ActionEvent event) {
		usuarioService.excluirUsuario(getUsuarioSelected());
		usuarios.remove(usuarioSelected);
		FacesUtil.exibirMensagemSucesso(DELETE_SUCESS);
	}

	public void salvarUsuario(ActionEvent event) {
		
		if (idPerfil != null) {
			perfil = usuarioService.buscarPerfil(idPerfil);
			List<Perfil> perfis = new ArrayList<Perfil>();
			perfis.add(perfil);
			usuario.setPerfis(perfis);

			if(idPerfil == 2) {
				fornecedor = fornecedorService.getById(idFornecedor);
				usuario.setFornecedor(fornecedor);
			}
			
			usuario.setSenha(DEFAULT_PASSWORD);
			usuarioService.salvarUsuario(usuario);
			usuarios.add(usuario);
			
			limparBean();
			FacesUtil.exibirMensagemSucesso(SAVE_SUCESS);
		}
		
	}

	public void salvarEdicao() throws IOException {

		if(usuarioSelected.getPerfis().get(0).getId() != null) {
			perfil = usuarioService.buscarPerfil(usuarioSelected.getPerfis().get(0).getId());
			List<Perfil> perfis = new ArrayList<Perfil>();
			perfis.add(perfil);
			
			if(usuarioSelected.getPerfis().get(0).getId() == 1)
				this.fornecedor = null;
			else if(usuarioSelected.getPerfis().get(0).getId() == 2 && idFornecedor == 0) {
				FacesUtil.exibirMensagemErro("Selecione o fornecedor !");
				getAllUsers();
				return;
			} else
				fornecedor = fornecedorService.getById(idFornecedor);
			
			usuarioSelected.setFornecedor(fornecedor);
			usuarioSelected.setPerfis(perfis);
			usuarioService.salvarUsuario(usuarioSelected);
			usuarios.set(usuarios.indexOf(usuarioSelected), usuarioSelected);  
			limparBean();
			getAllUsers();
			FacesUtil.getNavigationHandler().handleNavigation(FacesUtil.getFacesContext(), null, URL_LIST_USUARIO + "?faces-redirect=true");
		}
		
	}
	
	public boolean validarUsuarioExistente() {
		for (Usuario u : usuarios) {
			if (u.getUsername().equals(usuario.getUsername()))
				return false;
		}
		return true;
	}
	
	public void showHideComboForn() {
		if(this.idPerfil != null) {
			if(this.idPerfil == 2)
				hideComboForn = false;
			else
				hideComboForn = true;
		} else {
			hideComboForn = true;
		}
	}
	
	public void showHideComboFornEdit() {
		if(usuarioSelected != null) {
			if(usuarioSelected.getPerfis().get(0).getId() == 2)
				hideComboForn = false;
			else
				hideComboForn = true;
		}
	}
	
	public void limparBean() {
		this.usuario = null;
		this.usuarioSelected = null;
		this.fornecedor = null;
		this.perfil = null;
		this.idFornecedor = null;
		this.idPerfil = null;
		hideComboForn = true;
	}

	public void buscar() {
		List<Usuario> usuariosPesquisa;
		usuariosPesquisa = usuarioService.buscar(usuario.getNome(), usuario.getLogin(), usuario.getEmail(), idPerfil, idFornecedor);
		usuarios = usuariosPesquisa;
		
		FacesUtil.exibirMensagemSucesso(usuariosPesquisa.size() + " usuários encontrados");
		
		usuario = null;
		this.perfil = null;
	}
	
	public void limparPesquisa() {
		getAllUsers();
	}
	
	public void limparBeanPesquisa() {
		idFornecedor = null;
		idPerfil = null;
		usuario = null;
		hideComboForn = true;
	}
	
	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		if(this.perfil == null)
			perfil = new Perfil();
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	@Autowired
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public FornecedorService getFornecedorService() {
		return fornecedorService;
	}

	@Autowired
	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSelected() {
		if(this.usuarioSelected == null)
			this.usuarioSelected = new Usuario();
		return usuarioSelected;
	}

	public void setUsuarioSelected(Usuario usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public boolean isHideComboForn() {
		return hideComboForn;
	}

	public void setHideComboForn(boolean hideComboForn) {
		this.hideComboForn = hideComboForn;
	}

	public Fornecedor getFornecedor() {
		if(fornecedor == null)
			this.fornecedor = new Fornecedor();
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
}
