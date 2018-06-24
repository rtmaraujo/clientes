package br.com.exemplo.cliente.json.model;

/**
 * 
 * @author Marcos Araujo
 *
 */
public class IpInformacaoDTO{
	
	private String countryName;
	private Double latitude;
	private Double longitude;

	public IpInformacaoDTO(String countryName, Double latitude, Double longitude) {
		this.countryName = countryName;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
