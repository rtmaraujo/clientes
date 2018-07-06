package br.com.exemplo.cliente.dto;

import java.util.Comparator;

/**
 * 
 * @author Marcos Araujo
 *
 */
public class LocalizacaoDTO implements Comparable<LocalizacaoDTO>{

	private Integer distance;
	private String local;
	private Integer woeid;
	
	public LocalizacaoDTO(Integer distance, String local, Integer woeid) {
		this.distance = distance;
		this.local = local;
		this.woeid = woeid;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getWoeid() {
		return woeid;
	}

	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}

	@Override
    public int compareTo(LocalizacaoDTO o) {
        return Comparators.DISTANCIA.compare(this, o);
    }
	
	public static class Comparators {

        public static Comparator<LocalizacaoDTO> DISTANCIA = new Comparator<LocalizacaoDTO>() {
            @Override
            public int compare(LocalizacaoDTO o1, LocalizacaoDTO o2) {
                return o1.distance.compareTo(o2.distance);
            }
        };
    }
}
