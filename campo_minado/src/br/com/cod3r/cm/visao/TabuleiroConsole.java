package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.excecao.SairException;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executaJogo();
	}

	private void executaJogo() {
		try {
			boolean continuar = true;

			while (continuar) {
				cicloDoJogo();
				System.out.println("Outra partida?(S/n) ");
				String resposta = entrada.nextLine();
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau!!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				String digitado = capturarValorDigitado(
						"Digite linha e coluna: x,y | digite 'sair' para encerrar o jogo.");

				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();
				int linha = xy.next();
				int coluna = xy.next();

				String acao = capturarValorDigitado("1 - Abrir, 2 - Marcar/Desmarcar ou digite 'sair'");
				String acaoDigitada = acao;

				if ("1".equals(acaoDigitada)) {
					tabuleiro.abrir(linha, coluna);
				} else if ("2".equals(acaoDigitada)) {
					tabuleiro.marcar(linha, coluna);
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {
			tabuleiro.mostrarBombas();
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}

	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();

		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}
