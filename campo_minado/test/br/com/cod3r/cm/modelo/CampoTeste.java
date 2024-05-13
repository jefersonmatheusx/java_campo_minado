package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {

	Campo campo;

	@BeforeEach
	void iniciaCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoRealDistancia1() {
		Campo vizinhoEsquerda = new Campo(3, 2);
		boolean resultadoEsquerda = campo.adicionarVizinho(vizinhoEsquerda);

		Campo vizinhoDireita = new Campo(3, 4);
		boolean resultadoDireita = campo.adicionarVizinho(vizinhoDireita);

		Campo vizinhoCima = new Campo(2, 3);
		boolean resultadoCima = campo.adicionarVizinho(vizinhoCima);

		Campo vizinhoBaixo = new Campo(4, 3);
		boolean resultadoBaixo = campo.adicionarVizinho(vizinhoBaixo);

		boolean resultado = resultadoEsquerda && resultadoDireita && resultadoCima && resultadoBaixo;
		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia2() {
		Campo vizinhoDiagonalEsquerdaCima = new Campo(2, 2);
		boolean resultadoEsquerdaCima = campo.adicionarVizinho(vizinhoDiagonalEsquerdaCima);

		Campo vizinhoDiagonalDireitaCima = new Campo(2, 4);
		boolean resultadoDireitaCima = campo.adicionarVizinho(vizinhoDiagonalDireitaCima);

		Campo vizinhoDiagonalEsquerdaBaixo = new Campo(4, 2);
		boolean resultadoEsquerdaBaixo = campo.adicionarVizinho(vizinhoDiagonalEsquerdaBaixo);

		Campo vizinhoDiagonalDireitaBaixo = new Campo(4, 4);
		boolean resultadoDireitaBaixo = campo.adicionarVizinho(vizinhoDiagonalDireitaBaixo);

		boolean resultado = resultadoEsquerdaCima && resultadoDireitaCima && resultadoEsquerdaBaixo
				&& resultadoDireitaBaixo;
		assertTrue(resultado);
	}

	@Test
	void testeNaoVizinho() {
		Campo naoVizinho = new Campo(6, 6);
		boolean naoVizinhoResult = campo.adicionarVizinho(naoVizinho);

		assertFalse(naoVizinhoResult);
	}

	@Test
	void testeNaoVizinhoDistancia2() {
		Campo naoVizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(naoVizinho);

		assertFalse(resultado);
	}

	@Test
	void testeValorPadraoatributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasVezes() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirUmCampoNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirUmCampoMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirUmCampoMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}

	@Test
	void testeAbrirComVizinhos1() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);

		campo.adicionarVizinho(campo22);
		campo22.adicionarVizinho(campo11);

		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	@Test
	void testeAbrirComVizinhos2() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		Campo campo22 = new Campo(2, 2);

		campo12.minar();

		campo.adicionarVizinho(campo22);

		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);

		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
}
