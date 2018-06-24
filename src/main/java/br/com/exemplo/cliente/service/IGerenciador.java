package br.com.exemplo.cliente.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import br.com.exemplo.cliente.json.model.IpInformacaoDTO;
import br.com.exemplo.cliente.json.model.LocalizacaoDTO;
import br.com.exemplo.cliente.json.model.TemperaturaDTO;
import br.com.exemplo.cliente.model.Cliente;
import br.com.exemplo.cliente.model.Historico;

/**
 * 
 * @author Marcos Araujo
 *
 */
public interface IGerenciador {
	
	final static String URL_IP = "https://ipvigilante.com/json/";
	final static String URL_LOCALIZACAO = "https://www.metaweather.com/api/location/search/?lattlong=";
	final static String URL_TEMPERATURA = "https://www.metaweather.com/api/location/";
	
	default Historico prepararHistorico(HttpServletRequest request, Cliente clienteNovo) {
		Historico historico = null;
		String ip = buscarIp(request);
		
		try {
			IpInformacaoDTO ipInformacao = prepararIpInformacao(ip);
			List<LocalizacaoDTO> lista = prepararLocalizacao(ipInformacao.getLatitude(), ipInformacao.getLongitude());
			if (!lista.isEmpty()) {
				Collections.sort(lista, LocalizacaoDTO.Comparators.DISTANCIA);

				TemperaturaDTO temp = prepararTemperatura(lista.get(0).getWoeid());
				historico = new Historico();
				historico.setTempMaxima(temp.getMax());
				historico.setTempMinima(temp.getMin());
				historico.setLocal(lista.get(0).getLocal());
				historico.setCliente(clienteNovo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return historico;
	}
	
	default String buscarIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null) {
			ipAddress = request.getHeader("X_FORWARDED_FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
		}
		/* TODO: Fiz varios testes para retornar o endereco de IP valido, porem retorna 0:0:0:0:0:0:0:1,
		 * vi que a implementacao acima funciona, mas alguns casos o proxy bloqueia, entao coloquei um ipfixo para
		 * poder verificar a implementacao.
		 * return ipAddress;
		 *  
		 */
		return "201.6.2.122";
	}

	default IpInformacaoDTO prepararIpInformacao(String ip) throws JSONException {
		
		IpInformacaoDTO ipInformacao = null;
		JSONObject obj = new JSONObject(new RestTemplate().getForObject(URL_IP + ip, String.class));
		if (obj.getString("status").equals("success")) {
			JSONObject obj2 = obj.getJSONObject("data");
			return new IpInformacaoDTO(obj2.getString("country_name"), obj2.getDouble("latitude"),
					obj2.getDouble("longitude"));
		}
		return ipInformacao;
	}

	default List<LocalizacaoDTO> prepararLocalizacao(double latitude, double longitude) throws JSONException {

		List<LocalizacaoDTO> lista = new ArrayList<LocalizacaoDTO>();
		JSONArray array = new JSONArray(new RestTemplate().getForObject(URL_LOCALIZACAO + latitude + "," + longitude, String.class));
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			lista.add(new LocalizacaoDTO(obj.getInt("distance"), obj.getString("location_type"), obj.getInt("woeid")));
		}
		return lista;
	}

	default TemperaturaDTO prepararTemperatura(Integer woeid) throws JSONException {

		JSONObject objArray = new JSONObject(new RestTemplate().getForObject(URL_TEMPERATURA + woeid, String.class));
		JSONArray array = objArray.getJSONArray("consolidated_weather");
		if (array.length() > 0) {
			JSONObject obj = array.getJSONObject(0);
			return new TemperaturaDTO(obj.getDouble("min_temp"), obj.getDouble("max_temp"));
		}
		return null;
	}

}
