package br.com.ebt.figh.web.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ebt.figh.model.Perfil;
import br.com.ebt.figh.model.Usuario;
import br.com.ebt.figh.services.UsuarioService;
import br.com.ebt.figh.web.util.FacesUtil;

@Controller
@ViewScoped
@ManagedBean(name = "usuarioController")
public class UsuarioController {
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Perfil> perfis;
	private UsuarioService servico;
	private Long idPerfil;
	private Long idFornecedor;
	private boolean edicao;
	private boolean hideComboForn;
	
	@PostConstruct
	public void init() {
		usuarios = servico.buscarTodosUsuarios();
		perfis = servico.getPerfis();
	}
	
	public void cadastrar() {
		edicao = false;
		usuario = null;
		idPerfil = null;
		idFornecedor = null;
		
		FacesUtil.executarScript("PF('wUsuario').show()");
	}
	
	public void editar(Usuario usuario) {
		this.edicao = true;
		this.usuario = usuario;
		
		if(usuario == null || usuario.getId() != null) {
			idPerfil = usuario.getPerfis().get(0).getId();
			
			if(usuario.getFornecedor() != null)
				idFornecedor = usuario.getFornecedor().getId();
		}
		
		FacesUtil.executarScript("PF('wUsuario').show()");
		
	}
	
	public Usuario getUsuario() {
		if(usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public UsuarioService getServico() {
		return servico;
	}
	
	@Autowired
	public void setServico(UsuarioService servico) {
		this.servico = servico;
	}
	
	public boolean isEdicao() {
		return edicao;
	}
	
	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	
	public Long getIdPerfil() {
		return idPerfil;
	}
	
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	public List<Perfil> getPerfis() {
		return perfis;
	}
	
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
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
	
}
