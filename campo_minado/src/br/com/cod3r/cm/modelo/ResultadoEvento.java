package br.com.cod3r.cm.modelo;

public class ResultadoEvento {
	
	public ResultadoEvento(boolean ganhou) {
		this.ganhou = ganhou;
	}

	private final boolean ganhou;

	public boolean isGanhou() {
		return ganhou;
	}

}
