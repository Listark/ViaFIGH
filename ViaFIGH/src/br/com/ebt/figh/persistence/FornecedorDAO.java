package br.com.ebt.figh.persistence;

import java.util.List;

import br.com.ebt.figh.model.Fornecedor;

public interface FornecedorDAO {
	public void salvar(Fornecedor fornecedor);
	public void excluir(Fornecedor fornecedor);
	public List<Fornecedor> getAllFornecedores();
	public List<Fornecedor> buscar(String fornecedor, String cnpj, String cep, String uf, String cidade, String contato, String email);
	public List<Fornecedor> buscar(String nome);
	public Fornecedor getById(Long id);
}
