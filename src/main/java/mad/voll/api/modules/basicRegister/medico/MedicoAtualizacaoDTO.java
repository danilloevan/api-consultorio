package mad.voll.api.modules.basicRegister.medico;

import jakarta.validation.constraints.NotNull;
import mad.voll.api.modules.basicRegister.endereco.EnderecoDTO;

public record MedicoAtualizacaoDTO(

		@NotNull Long id, String nome, String telefone, EnderecoDTO endereco) {

}
