package br.com.ebt.figh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ebt.figh.model.Cidade;

@FacesConverter(value = "ConverterCidade")
public class ConverterCidade implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			String[] dados = value.split("-");
			Integer id = Integer.parseInt(dados[0]);
			String nome = dados[1];
			
			Cidade cidade = new Cidade();
			cidade.setId(id);
			cidade.setNome(nome);
			
			return cidade;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Cidade) {
			Cidade municipio = (Cidade) value;
			return municipio.toString();
		}
		return "";
	}
	
}