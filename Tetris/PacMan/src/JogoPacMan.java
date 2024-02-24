import base.CenarioPadrao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Classe que representa a janela principal do jogo Pac-Man.
 */
public class JogoPacMan extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FPS = 1000 / 20;

	private static final int JANELA_ALTURA = 550;

	private static final int JANELA_LARGURA = 448;

	private JPanel tela;

	private Graphics2D g2d;

	private BufferedImage buffer;

	private CenarioPadrao cenario;

	/**
	 * Enumeração que representa as teclas do jogo.
	 */
	public enum Tecla {
		CIMA, BAIXO, ESQUERDA, DIREITA, BA, BB, BC
	}

	/**
	 * Array de controle para armazenar o estado de cada tecla.
	 */
	public static boolean[] controleTecla = new boolean[Tecla.values().length];

	/**
	 * Método para liberar todas as teclas.
	 */
	public static void liberaTeclas() {
		for (int i = 0; i < controleTecla.length; i++) {
			controleTecla[i] = false;
		}
	}

	private void setaTecla(int tecla, boolean pressionada) {
		switch (tecla) {
			case KeyEvent.VK_UP:
				controleTecla[Tecla.CIMA.ordinal()] = pressionada;
				break;
			case KeyEvent.VK_DOWN:
				controleTecla[Tecla.BAIXO.ordinal()] = pressionada;
				break;
			case KeyEvent.VK_LEFT:
				controleTecla[Tecla.ESQUERDA.ordinal()] = pressionada;
				break;
			case KeyEvent.VK_RIGHT:
				controleTecla[Tecla.DIREITA.ordinal()] = pressionada;
				break;

			case KeyEvent.VK_ESCAPE:
				controleTecla[Tecla.BB.ordinal()] = pressionada;
				break;

			case KeyEvent.VK_SPACE:
				controleTecla[Tecla.BC.ordinal()] = pressionada;
				break;

			case KeyEvent.VK_ENTER:
				controleTecla[Tecla.BA.ordinal()] = pressionada;
		}
	}

	public static int nivel;

	public static boolean pausado;

	/**
	 * Construtor da classe JogoPacMan.
	 */
	public JogoPacMan() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setaTecla(e.getKeyCode(), false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				setaTecla(e.getKeyCode(), true);
			}
		});

		buffer = new BufferedImage(JANELA_LARGURA, JANELA_ALTURA, BufferedImage.TYPE_INT_RGB);

		g2d = buffer.createGraphics();

		tela = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(buffer, 0, 0, null);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(JANELA_LARGURA, JANELA_ALTURA);
			}

			@Override
			public Dimension getMinimumSize() {
				return getPreferredSize();
			}
		};

		getContentPane().add(tela);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();

		setVisible(true);
		tela.repaint();

	}

	/**
	 * Método para carregar o jogo inicial.
	 */
	private void carregarJogo() {
		cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
		cenario.carregar();
	}

	/**
	 * Método para iniciar o loop principal do jogo.
	 */
	public void iniciarJogo() {
		long prxAtualizacao = 0;

		while (true) {
			if (System.currentTimeMillis() >= prxAtualizacao) {

				g2d.setColor(Color.BLACK);
				g2d.fillRect(0, 0, JANELA_LARGURA, JANELA_ALTURA);

				if (controleTecla[Tecla.BA.ordinal()]) {
					// Pressionou espaço ou enter
					if (cenario instanceof InicioCenario) {
						cenario.descarregar();
						cenario = null;
						cenario = new JogoCenario(tela.getWidth(), tela.getHeight());

						g2d.setColor(Color.WHITE);
						g2d.drawString("Carregando...", 20, 20);
						tela.repaint();

						cenario.carregar();

					} else {
						JogoPacMan.pausado = !JogoPacMan.pausado;
					}

					liberaTeclas();

				} else if (controleTecla[Tecla.BB.ordinal()]) {
					// Pressionou ESQ
					if (!(cenario instanceof InicioCenario)) {
						cenario.descarregar();

						cenario = null;
						cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
						cenario.carregar();
					}

					liberaTeclas();

				}

				if (cenario == null) {
					g2d.setColor(Color.WHITE);
					g2d.drawString("Carregando...", 20, 20);

				} else {
					if (!JogoPacMan.pausado)
						cenario.atualizar();

					cenario.desenhar(g2d);

					if (JogoPacMan.pausado) {
						g2d.setColor(Color.WHITE);
						g2d.drawString("Pausado", tela.getWidth() / 2 - 30, tela.getHeight() / 2);
					}
				}

				tela.repaint();
				prxAtualizacao = System.currentTimeMillis() + FPS;
			}
		}
	}

	/**
	 * Método principal que cria uma instância do jogo e inicia o mesmo.
	 *
	 * @param args Argumentos de linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		JogoPacMan jogo = new JogoPacMan();
		jogo.carregarJogo();
		jogo.iniciarJogo();
	}

}
