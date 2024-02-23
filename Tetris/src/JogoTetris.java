import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import baseT.CenarioPadrao;

/**
 * Classe principal que representa o jogo Tetris.
 */
public class JogoTetris extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Taxa de quadros por segundo.
	 */
	private static final int FPS = 1000 / 20;

	/**
	 * Altura da janela do jogo.
	 */
	private static final int JANELA_ALTURA = 672;

	/**
	 * Largura da janela do jogo.
	 */
	private static final int JANELA_LARGURA = 500;

	private JPanel tela;

	private Graphics2D g2d;

	private BufferedImage buffer;

	private CenarioPadrao cenario;

	/**
	 * Enumeração das teclas utilizadas no jogo.
	 */
	public enum Tecla {
		CIMA, BAIXO, ESQUERDA, DIREITA, BA, BB, BC
	}

	public static boolean[] controleTecla = new boolean[Tecla.values().length];

	/**
	 * Libera o estado das teclas, definindo-as como não pressionadas.
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

	public static int nivel = 1;

	public static boolean pausado;

	/**
	 * Construtor da classe JogoTetris.
	 */
	public JogoTetris() {
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
	 * Carrega o cenário do jogo.
	 */
	private void carregarJogo() {
		cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
		cenario.carregar();
	}

	/**
	 * Inicia o loop principal do jogo.
	 */
	public void iniciarJogo() {
		long proximaAtualizacao = 0;

		while (true) {
			if (System.currentTimeMillis() >= proximaAtualizacao) {

				g2d.setColor(Color.DARK_GRAY);
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
						JogoTetris.pausado = !JogoTetris.pausado;
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
					g2d.drawString("O Cenário é uma ilusão...", 20, 20);

				} else {
					if (!JogoTetris.pausado)
						cenario.atualizar();

					cenario.desenhar(g2d);

					if (JogoTetris.pausado) {
						g2d.setColor(Color.WHITE);
						g2d.drawString("Pausado", tela.getWidth() / 2 - 4, tela.getHeight() / 2);
					}
				}

				tela.repaint();
				proximaAtualizacao = System.currentTimeMillis() + FPS;
			}
		}
	}

	/**
	 * Método principal que inicia o jogo.
	 *
	 * @param args Argumentos da linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		JogoTetris jogo = new JogoTetris();
		jogo.carregarJogo();
		jogo.iniciarJogo();
	}
}
