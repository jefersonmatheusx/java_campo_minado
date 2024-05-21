package br.com.cod3r.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

	private Campo campo;
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);

	public BotaoCampo(Campo campo) {

		this.campo = campo;
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		addMouseListener(this);
		campo.registrarObservador(this);
	}

	@Override
	public void eventoOcorreu(Campo c, CampoEvento evento) {
		switch (evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;

		default:
			aplicarEstiloPadrao();
			break;
		}

	}

	private void aplicarEstiloPadrao() {
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		setText("");
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
	}

	private void aplicarEstiloAbrir() {
		if (campo.isMarcado()) {
			return;
		}
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if (campo.isMinado()) {
			aplicarEstiloExplodir();
		} else {

			setBackground(BG_PADRAO);
			switch (campo.minasNaVizinhanca()) {
			case 1:
				setForeground(TEXTO_VERDE);
				break;
			case 2:
				setForeground(Color.BLUE);
				break;
			case 3:

				setForeground(Color.YELLOW);
				break;
			case 4:
			case 5:
			case 6:
				setForeground(Color.RED);
				break;
			default:
				setForeground(Color.PINK);
				break;
			}
			String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
			setText(valor);
		}
	}

	// Interface eventos Mouse

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1)// botao esquerdo
		{
			campo.abrir();
		} else if (e.getButton() == 3) {
			campo.alternarMarcacao();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
