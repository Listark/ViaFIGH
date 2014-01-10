package br.com.ebt.figh.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.xml.bind.ValidationException;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.model.Projeto;
import br.com.ebt.figh.model.Usuario;
import br.com.ebt.figh.services.ProjetoService;
import br.com.ebt.figh.services.UsuarioService;
import br.com.ebt.figh.web.util.FacesUtil;

@Component
@RequestScoped
@ManagedBean(name = "projetoController")
public class ProjetoController {
	private static final String SAVE_SUCESS_MESSAGE = "Projeto salvo com sucesso.";
	private static final String EDIT_SUCESS = "Projeto editado com sucesso.";
	private static final String EDIT_CANCEL = "Ação cancelada !";
	private static final String DELETE_SUCESS = "Projeto excluido com sucesso.";
	private static final String URL_EDIT_USER = "/paginas/admin/cadProjeto/editarProjeto.jsf";
	private static final String URL_LIST_PROJETO = "/paginas/admin/cadProjeto/crudProjeto.jsf";
	private Projeto projeto;
	private Projeto projetoSelected;
	private List<Projeto> projetos;
	private ProjetoService projetoService;
	private UsuarioService usuarioService;
	private Fornecedor fornecedor;
	private List<Usuario> usuariosSelectedsForn;
	private List<String> usuariosSelectedsCliente;
	private List<Usuario> usuariosForn;
	private List<Usuario> usuariosCliente;
	private List<Usuario> usuariosFornEdit;
	private List<Usuario> usuariosClienteEdit;
	private Long idFornecedor;
	private Boolean edicao;
	private Boolean novoCadastro;
	
	@PostConstruct
	public void init() {
		getAllProjetos();
	}
	
	public void gerarErro() throws Exception {
		throw new Exception("Erro");
	}
	
	public void salvar() throws ValidationException {
		int validaAssociacao;
		if(edicao == false)
			validaAssociacao = validarAssociacao(1);
		else
			validaAssociacao = validarAssociacao(2);
		
		if(validaAssociacao == 1) {
			FacesUtil.exibirMensagemErro("É necessário associar os usuários Parceiro ao projeto");
			return;
		} else if(validaAssociacao == 2) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesUtil.exibirMensagemErro("É necessário associar os usuários Claro TV ao projeto");
			return;
		} else if(validaAssociacao == 3) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesUtil.exibirMensagemErro("É necessário associar os parceiros e usuários Claro TV ao projeto");
			return;
		}
		
		List<Usuario> usuariosProjeto;
		
		if(edicao == true) {
			usuariosProjeto = new ArrayList<Usuario>();
			usuariosProjeto.addAll(usuariosFornEdit);
			usuariosClienteEdit = converterCliente(usuariosClienteEdit);
			usuariosProjeto.addAll(usuariosClienteEdit);
			projetoSelected.setUsuarios(usuariosProjeto);
			this.projetos.set(projetos.indexOf(projetoSelected), projetoSelected);
			projetoService.salvar(projetoSelected);			
		} else {
			usuariosProjeto = buscarUsuarios(usuariosSelectedsCliente);
			usuariosProjeto.addAll(usuariosSelectedsForn);			
			projeto.setUsuarios(usuariosProjeto);
			this.projetos.add(projeto);
			projetoService.salvar(projeto);
		}
		
		limparBean();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if(novoCadastro)
			requestContext.execute("PF('confirmarInicioNovoCadastro').show()");
			
			
	}
	
	public void novoProjeto() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update("FormCadProjeto");
		requestContext.execute("PF('cadProjeto').show()");
	}
	
	public List<Usuario> converterCliente(List<?> usuarios) {
		List<Usuario> usuariosConvertidos = new ArrayList<Usuario>();
		
		for(Object usuario : usuarios) {
			String[] dados = usuario.toString().split("-");
			usuariosConvertidos.add(usuarioService.getUsuarioById(new Long(dados[0])));
		}
		
		return usuariosConvertidos;
	}
	
	public void atualizarListaUsuariosFornecedor() {
		this.usuariosForn = usuarioService.getUsuariosTipoFornecedor();
	}
	
	public void atualizarListaUsuariosCliente() {
		this.usuariosCliente = usuarioService.getUsuariosTipoCliente();
	}
	
	public void novoCadastro() {
		limparBean();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('cadProjeto').show();");
	}
	
	public List<Usuario> buscarUsuarios(List<String> idsUsuarios) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		if(idsUsuarios.size() > 0) {
			Usuario u;
			
			for(String ids : idsUsuarios) {
				Long id = Long.parseLong(ids);
				u = usuarioService.getUsuarioById(id);
				usuarios.add(u);
			}
			
			return usuarios;
		}
		return null;
		
	}
	
	public void separarUsuarios() {
		if(projetoSelected.getUsuarios().size() > 0) {
			usuariosClienteEdit = new ArrayList<Usuario>();
			usuariosFornEdit = new ArrayList<Usuario>();
			
			for(Usuario usuario : projetoSelected.getUsuarios()) {
				if(usuario.getPerfis().get(0).getId() == 1)
					usuariosClienteEdit.add(usuario);
				else
					usuariosFornEdit.add(usuario);
			}
			
		}
	}
	
	public void cancelarEdicao() {
		limparBean();
		getAllProjetos();
		FacesUtil.getNavigationHandler().handleNavigation(FacesUtil.getFacesContext(), null, URL_LIST_PROJETO + "?faces-redirect=true");
		FacesUtil.exibirMensagemSucesso(EDIT_CANCEL);
	}
	
	public void getAllProjetos() {
		this.projetos = projetoService.getProjetos();
	}
	
	public void excluir() {
		projetos.remove(getProjetoSelected());
		projetoService.excluir(getProjetoSelected());
		FacesUtil.exibirMensagemSucesso(DELETE_SUCESS);
	}
	
	public void pesquisar() {
		List<Projeto> projetosPesquisa;
		projetosPesquisa = projetoService.pesquisar(getProjeto().getNumeroProjeto());
		projetos = projetosPesquisa;
		projeto = null;
		
		FacesUtil.exibirMensagemSucesso(projetosPesquisa.size() + " projetos encontrados");
	}
	
	public void limparPesquisa() {
		getAllProjetos();
	}
	
	public void limparBean() {
		projeto = null;
		projetoSelected = null;
		usuariosSelectedsCliente = null;
		usuariosSelectedsForn = null;
		edicao = false;
	}
	
	public void limparAssociacaoForn() {
		this.usuariosSelectedsForn = null;
		this.idFornecedor = null;
	}
	
	public void limparAssociacaoCliente() {
		this.usuariosSelectedsCliente = null;
	}
	
	public void atualizarListaUsuarios(AjaxBehaviorEvent event) {
		this.usuariosForn = usuarioService.getUsuariosByFornecedorId(idFornecedor);
	}
	
	public int validarAssociacao(int operacao) {
		
		if(operacao == 1) { // Valida as associações de Fornecedores e Usuários Claro TV ao projeto (CADASTRO)
			if((usuariosSelectedsForn == null || usuariosSelectedsForn.size() <= 0) && (usuariosSelectedsCliente == null || usuariosSelectedsCliente.size() <= 0))
				return 3;
			
			if(usuariosSelectedsForn == null || usuariosSelectedsForn.size() <= 0)
				return 1;
			
			if(usuariosSelectedsCliente == null || usuariosSelectedsCliente.size() <= 0)
				return 2;			 
		
		} else if(operacao == 2) { // Valida as associações de Fornecedores e Usuários Claro TV ao projeto(ALTERACAO)
			
			if((usuariosFornEdit == null || usuariosFornEdit.size() <= 0) && (usuariosClienteEdit == null || usuariosClienteEdit.size() <=0))
				return 3;
			if(usuariosFornEdit == null || usuariosFornEdit.size() <= 0)
				return 1;
			
			if(usuariosClienteEdit == null || usuariosClienteEdit.size() <= 0)
				return 2;
		}
		return 0;
		
	}
	
	public Projeto getProjeto() {
		if(this.projeto == null)
			this.projeto = new Projeto();
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Projeto getProjetoSelected() {
		return projetoSelected;
	}

	public void setProjetoSelected(Projeto projetoSelected) {
		this.projetoSelected = projetoSelected;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public ProjetoService getProjetoService() {
		return projetoService;
	}
	
	@Autowired
	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	
	@Autowired
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Usuario> getUsuariosSelectedsForn() {
		if(this.usuariosSelectedsForn == null)
			this.usuariosSelectedsForn = new ArrayList<Usuario>();
		return usuariosSelectedsForn;
	}

	public void setUsuariosSelectedsForn(List<Usuario> usuariosSelectedsForn) {
		this.usuariosSelectedsForn = usuariosSelectedsForn;
	}

	public List<Usuario> getUsuariosForn() {
		return usuariosForn;
	}

	public void setUsuariosForn(List<Usuario> usuariosForn) {
		this.usuariosForn = usuariosForn;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public List<String> getUsuariosSelectedsCliente() {
		if(usuariosSelectedsCliente == null)
			usuariosSelectedsCliente = new ArrayList<String>();
		return usuariosSelectedsCliente;
	}

	public void setUsuariosSelectedsCliente(List<String> usuariosSelectedsCliente) {
		this.usuariosSelectedsCliente = usuariosSelectedsCliente;
	}

	public List<Usuario> getUsuariosCliente() {
		return usuariosCliente;
	}

	public void setUsuariosCliente(List<Usuario> usuariosCliente) {
		this.usuariosCliente = usuariosCliente;
	}

	public Boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(Boolean edicao) {
		this.edicao = edicao;
	}

	public List<Usuario> getUsuariosFornEdit() {
		if(usuariosFornEdit == null)
			usuariosFornEdit = new ArrayList<Usuario>();
		return usuariosFornEdit;
	}

	public void setUsuariosFornEdit(List<Usuario> usuariosFornEdit) {
		this.usuariosFornEdit = usuariosFornEdit;
	}

	public List<Usuario> getUsuariosClienteEdit() {
		if(usuariosClienteEdit == null)
			usuariosClienteEdit = new ArrayList<Usuario>();
		return usuariosClienteEdit;
	}

	public void setUsuariosClienteEdit(List<Usuario> usuariosClienteEdit) {
		this.usuariosClienteEdit = usuariosClienteEdit;
	}

	public Boolean getNovoCadastro() {
		return novoCadastro;
	}

	public void setNovoCadastro(Boolean novoCadastro) {
		this.novoCadastro = novoCadastro;
	}

	

}
