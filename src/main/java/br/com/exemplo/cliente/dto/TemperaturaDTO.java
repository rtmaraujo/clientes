package br.com.exemplo.cliente.dto;

/**
 * 
 * @author Marcos Araujo
 *
 */
public class TemperaturaDTO {

	private Double min;
	private Double max;
	
	public TemperaturaDTO(Double min, Double max) {
		this.min = min;
		this.max = max;
	}
	
	public TemperaturaDTO() {
	}
	
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
}
