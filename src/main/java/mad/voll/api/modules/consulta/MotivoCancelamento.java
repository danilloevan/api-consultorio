package mad.voll.api.modules.consulta;

public enum MotivoCancelamento {

	DESISTENCIA_PACIENTE("Desistência do Paciente"),
	CANCELAMENTO_MEDICO("Cancelamento do Medico"),
	OUTROS("Outro motivo");

	private String descricao;
	
	MotivoCancelamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
