package br.com.ebt.figh.web.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ebt.figh.model.Cidade;
import br.com.ebt.figh.model.Estado;
import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.services.FornecedorService;
import br.com.ebt.figh.web.util.FacesUtil;

@Component
@ViewScoped
@ManagedBean(name = "fornecedorController_old")
public class FornecedorController_old implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_SUCESS_MESSAGE = "Fornecedor salvo com sucesso.";
	private static final String SAVE_ERROR_MESSAGE = "Ocorreu um erro ao salvar o fornecedor !\nCódigo do Erro: ";
	private static final String EDIT_SUCESS = "Fornecedor editado com sucesso.";
	private static final String EDIT_CANCEL = "Ação cancelada !";
	private static final String DELETE_SUCESS = "Fornecedor excluido com sucesso.";

	private FornecedorService fornecedorService;
	private Fornecedor fornecedor;
	private Fornecedor fornecedorSelected;
	private Cidade cidade;
	private Cidade cidadeTeste;
	private Estado estado;
	private Integer estadoId;
	private Integer cidadeId;
	private List<Fornecedor> fornecedores;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private Boolean edicao;
	private Boolean novoCadastro;

	@PostConstruct
	public void init() {
		estados = fornecedorService.getAllEstados();
		fornecedores = fornecedorService.getAllFornecedores();
		cidades = new ArrayList<Cidade>();
	}

	public void buscar() {

		if (estado == null && cidade == null) {
			estado = new Estado();
			estado.setSigla("");

			cidade = new Cidade();
			cidade.setNome("");
			cidade.setEstado(estado);
		} else if (cidade == null) {
			cidade = new Cidade();
			cidade.setNome("");
			cidade.setEstado(estado);
		}
		cidade.setEstado(estado);
		fornecedor.getEndereco().getLogradouro().setCidade(cidade);

		List<Fornecedor> fornecedoresPesquisa;
		fornecedoresPesquisa = fornecedorService.buscar(fornecedor.getNome(),
				fornecedor.getCNPJ(), fornecedor.getEndereco().getLogradouro()
						.getCep(), fornecedor.getEndereco().getLogradouro()
						.getCidade().getEstado().getSigla(), fornecedor
						.getEndereco().getLogradouro().getCidade().getNome(),
				fornecedor.getContato(), fornecedor.getEmail());
		this.fornecedores = fornecedoresPesquisa;
		this.fornecedor = null;
		this.cidade = null;
		this.estado = null;

		FacesUtil.exibirMensagemSucesso(fornecedoresPesquisa.size()
				+ " fornecedores encontrados");
	}

	public void setarCidadeEdicao() {
		Integer idEstado = getFornecedorSelected().getEndereco()
				.getLogradouro().getCidade().getEstado().getId();
		cidades = fornecedorService.getAllCidadeByEstado(idEstado);
		cidade = getFornecedorSelected().getEndereco().getLogradouro()
				.getCidade();
		estado = cidade.getEstado();
	}

	public void limparPesquisa() {
		fornecedores = fornecedorService.getAllFornecedores();
	}
	
	public void limparDados() {
		fornecedor = null;
		estado = null;
		cidade = null;
		
		FacesUtil.executarScript("PF('cadFornecedor').show()");
	}
	
	public void limparTela() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.reset(":FormCadastrarFornecedor");
	}

	public void editarComboCidades(AjaxBehaviorEvent event) {

		/*if (estado != null && estado.getId() != null) {
			cidades = fornecedorService.getAllCidadeByEstado(estado.getId());
		}*/
		
		if(estadoId != null && !estadoId.equals(new Integer(0)))
			cidades = fornecedorService.getAllCidadeByEstado(estadoId);

	}
	
	public void editar(Fornecedor fornecedor) {
		this.fornecedorSelected = fornecedor;
		cidades = fornecedorService.getAllCidadeByEstado(fornecedor.getEndereco().getLogradouro().getCidade().getEstado().getId());
		FacesUtil.executarScript("PF('cadFornecedor').show()");
	}

	public void cancelEditarFornecedor(RowEditEvent event) {
		FacesUtil.exibirMensagemSucesso(EDIT_CANCEL);
	}

	public void excluirFornecedor(ActionEvent event) {
		fornecedorService.excluir(getFornecedorSelected());
		fornecedores.remove(getFornecedorSelected());
		FacesUtil.exibirMensagemSucesso(DELETE_SUCESS);
	}

	public void salvarFornecedor() {

		if (edicao == false) {
			cidade = fornecedorService.getCidadeById(cidadeId);
			fornecedor.getEndereco().getLogradouro().setCidade(cidade);
			fornecedorService.salvar(fornecedor);
			fornecedores.add(fornecedor);
		} else {
			cidade = fornecedorService.getCidadeById(cidadeId);
			fornecedorSelected.getEndereco().getLogradouro().setCidade(cidade);
			fornecedorService.salvar(fornecedorSelected);
			fornecedores.set(fornecedores.indexOf(fornecedorSelected),
					fornecedorSelected);
		}

		fornecedor = null;
		fornecedorSelected = null;
		estado = null;
		cidade = null;

		if (edicao == false) {
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.execute("PF('cadFornecedor').hide()");
			requestContext.execute("PF('confirmarInicioNovoCadastro').show()");
			FacesUtil.exibirMensagemSucesso(SAVE_SUCESS_MESSAGE);
		} else {
			FacesUtil.exibirMensagemSucesso(EDIT_SUCESS);
		}
	} 

	@Autowired
	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	public FornecedorService getFornecedorService() {
		return fornecedorService;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Fornecedor getFornecedor() {
		if (fornecedor == null)
			fornecedor = new Fornecedor();
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedorSelected() {
		return fornecedorSelected;
	}

	public void setFornecedorSelected(Fornecedor fornecedorSelected) {
		this.fornecedorSelected = fornecedorSelected;
	}

	public Estado getEstado() {
		if (this.estado == null)
			this.estado = new Estado();
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		if (this.cidade == null)
			this.cidade = new Cidade();
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(Boolean edicao) {
		this.edicao = edicao;
	}

	public Boolean getNovoCadastro() {
		return novoCadastro;
	}

	public void setNovoCadastro(Boolean novoCadastro) {
		this.novoCadastro = novoCadastro;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public Cidade getCidadeTeste() {
		return cidadeTeste;
	}

	public void setCidadeTeste(Cidade cidadeTeste) {
		this.cidadeTeste = cidadeTeste;
	}

}
