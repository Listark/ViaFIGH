package br.com.ebt.figh.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	private static final String INSERT_SUCCESS = "Usuário cadastrado com sucesso!";
	private static final String DELETE_SUCCESS = "Usuário excluído com sucesso!";
	private static final String EDIT_SUCCESS   = "Usuário alterado com sucesso";
	private static final String SEARCH_SUCCESS = " usuarios foram encontrados";
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Perfil> perfis;
	private Perfil perfil;
	private UsuarioService servico;
	private FornecedorService servicoForn;
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
		hideComboForn = true;
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
			avaliarPerfil(idPerfil);
			
			if(usuario.getFornecedor() != null)
				idFornecedor = usuario.getFornecedor().getId();
				
		}
		
		FacesUtil.executarScript("PF('wUsuario').show()");
		
	}
	
	public void salvar() {
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis.add(servico.buscarPerfil(idPerfil));
		usuario.setPerfis(perfis);
		
		if(idPerfil == 2) {
			Fornecedor f = servicoForn.getById(idFornecedor);
			usuario.setFornecedor(f);
		}
		
		servico.salvarUsuario(usuario);
		
		if(edicao) {
			usuarios.set(usuarios.indexOf(usuario), usuario);
			FacesUtil.exibirMensagemSucesso(EDIT_SUCCESS);
		} else {
			usuarios.add(usuario);
			FacesUtil.exibirMensagemSucesso(INSERT_SUCCESS);
			FacesUtil.executarScript("PF('wNovoCadastro').show()");
		}
		
		usuario = null;
		idFornecedor = null;
		idPerfil = null;
	}
	
	public void excluir() {
		servico.excluirUsuario(usuario);
		usuarios.remove(usuarios.indexOf(usuario));
		
		FacesUtil.executarScript("PF('wExclusao').hide()");
		FacesUtil.exibirMensagemSucesso(DELETE_SUCCESS);
	}
	
	public void pesquisar(String nome) {
		usuarios = servico.buscar(nome);
		usuario = null;
		FacesUtil.exibirMensagemSucesso(usuarios.size() + SEARCH_SUCCESS);
		FacesUtil.executarScript("PF('wPesquisa').hide()");
	}
	
	public void limparPesquisa() {
		usuarios = servico.buscarTodosUsuarios();
	}
	
	private void avaliarPerfil(Long id) {
		if(id == null)
			hideComboForn = true;
		
		if(id == 1) {
			hideComboForn = true;
			idFornecedor = null;
		} else
			hideComboForn = false;
	}
	
	public void showHideComboForn() {
		avaliarPerfil(idPerfil);
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
	
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Perfil getPerfil() {
		if(perfil == null)
			perfil = new Perfil();
		return perfil;
	}
	
	@Autowired
	public void setServicoForn(FornecedorService servicoForn) {
		this.servicoForn = servicoForn;
	}
	
	public FornecedorService getServicoForn() {
		return servicoForn;
	}
	
}
