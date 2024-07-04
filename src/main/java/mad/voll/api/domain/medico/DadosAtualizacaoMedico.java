package mad.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import mad.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(

		@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

}
