package br.com.ebt.figh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ebt.figh.model.Cidade;
import br.com.ebt.figh.model.Endereco;
import br.com.ebt.figh.model.Estado;
import br.com.ebt.figh.model.Fornecedor;
import br.com.ebt.figh.persistence.FornecedorDAO;
import br.com.ebt.figh.persistence.LocalizacaoDAO;

@Service
public class FornecedorService {

	private FornecedorDAO fornecedorDAO;
	private LocalizacaoDAO localizacaoDAO;
	
	@Transactional
	public List<Fornecedor> getAllFornecedores(){
		return fornecedorDAO.getAllFornecedores();
	}

	@Transactional
	public void salvar(Fornecedor fornecedor) {
		fornecedorDAO.salvar(fornecedor);
	}

	@Transactional
	public void excluir(Fornecedor fornecedor) {
		fornecedorDAO.excluir(fornecedor);
	}
	
	@Transactional
	public List<Estado> getAllEstados() {
		return localizacaoDAO.getAllEstados();
	}

	@Transactional
	public List<Cidade> getAllCidadeByEstado(Integer idEstado) {
		return localizacaoDAO.getAllCidadesByEstado(idEstado);
	}
	
	@Transactional
	public Cidade getCidadeById(Integer idCidade) {
		return localizacaoDAO.getCidadeById(idCidade);
	}
	
	@Transactional
	public Estado getEstadoByUF(String uf) {
		return localizacaoDAO.getEstadoByUF(uf);
	}
	
	@Transactional
	public void salvarEndereco(Endereco endereco) {
		localizacaoDAO.salvarEndereco(endereco);
	}
	
	@Transactional
	public List<Fornecedor> buscar(String fornecedor, String cnpj, String cep, String uf, String cidade, String contato, String email) {
		return fornecedorDAO.buscar(fornecedor, cnpj, cep, uf, cidade, contato, email);
	}
	
	@Transactional
	public List<Fornecedor> buscar(String nome) {
		return fornecedorDAO.buscar(nome);
	}
	
	@Transactional
	public Fornecedor getById(Long id) {
		return fornecedorDAO.getById(id);
	}
	
	@Transactional
	public Estado getEstadoById(Integer id) {
		return localizacaoDAO.getEstadoById(id);
	}
	
	@Autowired
	public void setFornecedorDAO(FornecedorDAO fornecedorDAO) {
		this.fornecedorDAO = fornecedorDAO;
	}
	
	public FornecedorDAO getFornecedorDAO() {
		return fornecedorDAO;
	}

	@Autowired
	public void setLocalizacaoDAO(LocalizacaoDAO localizacaoDAO) {
		this.localizacaoDAO = localizacaoDAO;
	}
	
	public LocalizacaoDAO getLocalizacaoDAO() {
		return localizacaoDAO;
	}

}
