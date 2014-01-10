package br.com.ebt.figh.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ebt.figh.model.Cidade;
import br.com.ebt.figh.model.Estado;
import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.services.FornecedorService;
import br.com.ebt.figh.web.util.FacesUtil;

@Controller
@ViewScoped
@ManagedBean(name="fornecedorController")
public class FornecedorController {
	private static final String DELETE_SUCCESS = "Fornecedor excluído com sucesso!";
	private static final String INSERT_SUCCESS = "Fornecedor cadastrado com sucesso!";
	private static final String EDIT_SUCCESS = "Usuário alterado com sucesso!";
	private static final String SEARCH_SUCCESS = " fornecedores foram encontrados";
	private List<Fornecedor> fornecedores;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private FornecedorService servico;
	private Fornecedor fornecedor;
	private boolean edicao;
	private Integer estadoId;
	private Integer cidadeId;
	private Cidade cidade;
	private Estado estado;
	
	
	@PostConstruct
	public void init() {
		fornecedores = servico.getAllFornecedores();
		estados = servico.getAllEstados();
		cidades = new ArrayList<Cidade>();
	}
	
	
	public void salvar() {
		cidade = servico.getCidadeById(cidadeId);
		estado = servico.getEstadoById(estadoId);
		
		fornecedor.getEndereco().getLogradouro().setCidade(cidade);
		fornecedor.getEndereco().getLogradouro().getCidade().setEstado(estado);
		
		servico.salvar(fornecedor);
		
		if(edicao == false) {
			fornecedores.add(fornecedor);
			FacesUtil.exibirMensagemSucesso(INSERT_SUCCESS);
			FacesUtil.executarScript("PF('wForn').hide()");
			FacesUtil.executarScript("PF('wNovoCadastro').show()");
		} else { 
			fornecedores.set(fornecedores.indexOf(fornecedor), fornecedor);
			FacesUtil.exibirMensagemSucesso(EDIT_SUCCESS);
			FacesUtil.executarScript("PF('wForn').hide()");
		}
		
		fornecedor = null;
		cidadeId = null;
		estadoId = null;
		cidades = null;

	}
	
	public void cadastrar() {
		edicao = false;
		fornecedor = null;
		estadoId = null;
		cidadeId = null;
		cidades = null;
		FacesUtil.executarScript("PF('wForn').show()");
	}
	
	public void editar(Fornecedor fornecedor) {
		this.edicao = true;
		this.fornecedor = fornecedor;
		
		if(fornecedor != null && fornecedor.getId() != null) {
			cidadeId = fornecedor.getEndereco().getLogradouro().getCidade().getId();
			estadoId = fornecedor.getEndereco().getLogradouro().getCidade().getEstado().getId();
			
			cidades = servico.getAllCidadeByEstado(estadoId);
		}
		
		FacesUtil.executarScript("PF('wForn').show()");
		
	}
	
	public void excluir() {
		servico.excluir(fornecedor);
		fornecedores.remove(fornecedores.indexOf(fornecedor));
		
		FacesUtil.executarScript("PF('wExclusao').hide()");
		FacesUtil.exibirMensagemSucesso(DELETE_SUCCESS);
	}
	
	public void pesquisar(String nome) {
		fornecedores = servico.buscar(nome);
		fornecedor = null;		
		FacesUtil.exibirMensagemSucesso(fornecedores.size() + SEARCH_SUCCESS);
		FacesUtil.executarScript("PF('wPesquisa').hide()");
	}

	
	public void limparPesquisa() {
		fornecedores = servico.getAllFornecedores();
	}
	
	public void carregarComboCidades(AjaxBehaviorEvent event) {
		cidades = servico.getAllCidadeByEstado(estadoId);
	}
	
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	
	public FornecedorService getServico() {
		return servico;
	}

	
	@Autowired
	public void setServico(FornecedorService servico) {
		this.servico = servico;
	}

	
	public Fornecedor getFornecedor() {
		if(fornecedor == null)
			fornecedor = new Fornecedor();
		
		return fornecedor;
	}

	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	
	public boolean isEdicao() {
		return edicao;
	}

	
	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
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


	public Cidade getCidade() {
		if(cidade == null)
			cidade = new Cidade();
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public Estado getEstado() {
		if(estado == null)
			estado = new Estado();
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
}
