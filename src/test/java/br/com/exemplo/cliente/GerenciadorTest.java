package br.com.exemplo.cliente;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.exemplo.cliente.json.model.IpInformacaoDTO;
import br.com.exemplo.cliente.json.model.LocalizacaoDTO;
import br.com.exemplo.cliente.json.model.TemperaturaDTO;
import br.com.exemplo.cliente.service.GerenciadorImpl;
/**
 * 
 * @author Marcos Araujo
 *
 */
public class GerenciadorTest {
	
	@Mock
	private GerenciadorImpl gerenciador;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIp() throws JSONException {
		IpInformacaoDTO temp = new IpInformacaoDTO("United States", 33.2, 33.8);
		
		when(gerenciador.prepararIpInformacao("8.8.8.8")).thenReturn(temp);
		IpInformacaoDTO tempMock = gerenciador.prepararIpInformacao("8.8.8.8"); 
		Assert.assertNotNull(tempMock);
		Assert.assertEquals(temp.getCountryName(), tempMock.getCountryName());
		Assert.assertEquals(temp.getLatitude(), tempMock.getLatitude(), 0.002);
		Assert.assertEquals(temp.getLongitude(), tempMock.getLongitude(), 0.002);
        Mockito.verify(gerenciador, Mockito.times(1)).prepararIpInformacao("8.8.8.8");
	}

	@Test
	public void testLocalizacao() throws JSONException {
		List<LocalizacaoDTO> lista = new ArrayList<LocalizacaoDTO>();
		
		when(gerenciador.prepararLocalizacao(37.38600, -122.08380)).thenReturn(lista);
		Assert.assertNotNull(gerenciador.prepararLocalizacao(37.38600, -122.08380));
		Mockito.verify(gerenciador, Mockito.times(1)).prepararLocalizacao(37.38600, -122.08380);
	}

	@Test
	public void testTemperatura() throws JSONException {
		TemperaturaDTO temp = new TemperaturaDTO(33.0, 20.6);
		
		when(gerenciador.prepararTemperatura(44418)).thenReturn(temp);
		TemperaturaDTO tempMock = gerenciador.prepararTemperatura(44418);
		Assert.assertNotNull(tempMock);
		Assert.assertEquals(temp.getMax(), tempMock.getMax(), 0.002);
		Assert.assertEquals(temp.getMin(), tempMock.getMin(), 0.002);
		Mockito.verify(gerenciador, Mockito.times(1)).prepararTemperatura(44418);
	}
	
}
