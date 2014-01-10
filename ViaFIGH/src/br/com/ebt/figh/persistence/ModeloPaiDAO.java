package br.com.ebt.figh.persistence;

import java.util.List;

import br.com.ebt.figh.model.ModeloTestePai;

public interface ModeloPaiDAO {
	
	public void salvar(ModeloTestePai modeloPai);
	public ModeloTestePai getModeloPaiById(int id);
	public List<ModeloTestePai> getTodos();
}
