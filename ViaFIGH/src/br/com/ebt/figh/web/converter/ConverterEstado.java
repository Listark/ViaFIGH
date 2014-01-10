package br.com.ebt.figh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ebt.figh.model.Estado;

@FacesConverter(value="ConverterEstado")
public class ConverterEstado implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			String[] dados = value.split("-");
			Integer id = Integer.parseInt(dados[0]);
			String nome = dados[1];
			String sigla = dados[2];
			
			Estado estado = new Estado();
			estado.setId(id);
			estado.setNome(nome);
			estado.setSigla(sigla);
			
			return estado;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Estado) {
			Estado estado = (Estado) value;
			return estado.getId() + "-" + estado.getNome() + "-" + estado.getSigla();
		}
		return "";
	}
	
}
