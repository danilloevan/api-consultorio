package mad.voll.api.modules.basicRegister.medico;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping({ "/medicos" })
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<MedicoDetalhamentoDTO> cadastrar(@RequestBody @Valid MedicoCadastroDTO dados, UriComponentsBuilder uriBuilder) {
		Medico medico = new Medico(dados);
		medicoRepository.save(medico);

		URI uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new MedicoDetalhamentoDTO(medico));
	}

	@GetMapping
	public ResponseEntity<Page<MedicoListagemDTO>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		Page<MedicoListagemDTO> page = medicoRepository.findAllByExcluidoFalse(paginacao).map(MedicoListagemDTO::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<MedicoDetalhamentoDTO> atualizar(@RequestBody @Valid MedicoAtualizacaoDTO dados) {
		Medico medico = medicoRepository.getReferenceById(dados.id());
		medico.atualizarMedico(dados);

		return ResponseEntity.ok(new MedicoDetalhamentoDTO(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.excluirMedico();

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<MedicoDetalhamentoDTO> detalhar(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);

		return ResponseEntity.ok(new MedicoDetalhamentoDTO(medico));
	}
}
