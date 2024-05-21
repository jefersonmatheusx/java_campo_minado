package br.com.cod3r.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		int linhas = tabuleiro.getLinhas();
		int colunas = tabuleiro.getColunas();
		setLayout(new GridLayout(linhas, colunas));

		tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));
		tabuleiro.registrarObservador(e -> {

			SwingUtilities.invokeLater(() -> {
				if (e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Ganhou :D ");
				} else {
					JOptionPane.showMessageDialog(this, "Perdeu :( ");
				}
				tabuleiro.reiniciar();
			});
			// TODO - mostrar resultado pro usuário ! ganhou ou perdeu.
			// TODO - reiniciar tabuleiro
		});
	}
}
