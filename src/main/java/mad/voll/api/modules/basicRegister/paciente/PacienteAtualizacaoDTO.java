package mad.voll.api.modules.basicRegister.paciente;

import jakarta.validation.constraints.NotNull;
import mad.voll.api.modules.basicRegister.endereco.EnderecoDTO;

public record PacienteAtualizacaoDTO(

		@NotNull Long id, String nome, String telefone, EnderecoDTO endereco) {
}
