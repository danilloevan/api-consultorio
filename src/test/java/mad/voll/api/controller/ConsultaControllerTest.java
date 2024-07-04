package mad.voll.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import mad.voll.api.domain.consulta.AgendaDeConsultas;
import mad.voll.api.domain.consulta.DadosAgendamentoConsulta;
import mad.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import mad.voll.api.domain.medico.Especialidade;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

	@Autowired private MockMvc mock;
	
	@Autowired
	private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoJson;
	
	@Autowired
	private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoJson;
	
	@MockBean
	private AgendaDeConsultas agendaService;
	
	@Test
	@DisplayName("Deve retornar erro 400 quando parametros inválidos")
	@WithMockUser
	void testAgendarCenario1() throws Exception {
		MockHttpServletResponse mockResponse =  mock.perform(
					post("/consultas").content("{}").contentType(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
	
		assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	@DisplayName("Deve retornar status 200 quando parametros válidos")
	@WithMockUser
	void testAgendarCenario2() throws Exception {
		LocalDateTime dataHora = LocalDateTime.now().plusHours(1); 
		DadosDetalhamentoConsulta dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2L, 10L, dataHora);
		
		when(agendaService.agendar(any())).thenReturn(dadosDetalhamento);
		
		MockHttpServletResponse mockResponse =  mock.perform(
				post("/consultas").contentType(MediaType.APPLICATION_JSON)
					.content(dadosAgendamentoJson.write(
							new DadosAgendamentoConsulta(2L, 10L, dataHora, Especialidade.CARDIOLOGIA)).getJson())
				).andReturn().getResponse();
	
		assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		String jsonResponse = dadosDetalhamentoJson.write(dadosDetalhamento).getJson();
		
		assertThat(mockResponse.getContentAsString()).isEqualTo(jsonResponse);
	}
}
