package br.com.ebt.figh.persistence;

import java.util.List;

import br.com.ebt.figh.model.Cidade;
import br.com.ebt.figh.model.Endereco;
import br.com.ebt.figh.model.Estado;

public interface LocalizacaoDAO {
	public List<Estado> getAllEstados();
	public List<Cidade> getAllCidadesByEstado(Integer idEstado);
	public Cidade getCidadeById(Integer idCidade);
	public Estado getEstadoByUF(String uf);
	public void salvarEndereco(Endereco endereco);
	public Estado getEstadoById(Integer id);
}
