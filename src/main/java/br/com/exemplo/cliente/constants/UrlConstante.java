package br.com.exemplo.cliente.constants;

/**
 * 
 * @author Marcos Araujo
 *
 */

public enum UrlConstante {

	URLIP("https://ipvigilante.com/json/"), URLLOCALIZACAO(
			"https://www.metaweather.com/api/location/search/?lattlong="), URLTEMPERATURA(
					"https://www.metaweather.com/api/location/");

	private String valor;

	UrlConstante(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
