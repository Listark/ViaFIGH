package br.com.ebt.figh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ebt.figh.model.ModeloTestePai;

@FacesConverter(value="converterModeloPai")
public class ConverterModeloPai implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.equals("")) {
			String[] valores = value.split("-");
			
			int id = Integer.parseInt(valores[0]);
			String nome = valores[1];
			
			ModeloTestePai pai = new ModeloTestePai();
			pai.setId(id);
			pai.setNome(nome);
			
			return pai;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof ModeloTestePai) {
			ModeloTestePai pai = (ModeloTestePai) value;
			return pai.toString();
		} else {
			return null;
		}
	}
	
}
