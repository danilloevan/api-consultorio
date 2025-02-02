package mad.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import mad.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(

		@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
