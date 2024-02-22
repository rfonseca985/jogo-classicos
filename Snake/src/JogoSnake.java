import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import baseS.CenarioPadrao;
import baseS.Texto;

/**
 * Classe principal que representa o jogo Snake.
 */
public class JogoSnake extends JFrame {

	/**
	 * Número de série da classe para serialização.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Taxa de quadros por segundo (FPS).
	 */
	private static final int FPS = 1000 / 20;

	/**
	 * Altura da janela do jogo.
	 */
	private static final int JANELA_ALTURA = 450;

	/**
	 * Largura da janela do jogo.
	 */
	private static final int JANELA_LARGURA = 450;

	/**
	 * Painel de desenho do jogo.
	 */
	private JPanel tela;

	/**
	 * Objeto para manipulação de gráficos.
	 */
	private Graphics2D g2d;

	/**
	 * Buffer de imagem para evitar flickering.
	 */
	private BufferedImage buffer;

	/**
	 * Cenário atual do jogo.
	 */
	private CenarioPadrao cenario;

	/**
	 * Texto a ser exibido durante a pausa.
	 */
	public static final Texto textoPausa = new Texto(new Font("Tahoma", Font.PLAIN, 40));

	/**
	 * Enumeração representando as teclas do jogo.
	 */
	public enum Tecla {
		CIMA, BAIXO, ESQUERDA, DIREITA, BA, BB
	}

	/**
	 * Array de controle das teclas.
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

	/**
	 * Método para configurar o estado de uma tecla.
	 *
	 * @param tecla       Código da tecla.
	 * @param pressionada Indica se a tecla está pressionada.
	 */
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
			case KeyEvent.VK_ENTER:
				controleTecla[Tecla.BA.ordinal()] = pressionada;
		}
	}

	/**
	 * Nível do jogo.
	 */
	public static int nivel;

	/**
	 * Velocidade do jogo.
	 */
	public static int velocidade;

	/**
	 * Estado de pausa do jogo.
	 */
	public static boolean pausado;

	/**
	 * Construtor da classe JogoSnake.
	 */
	public JogoSnake() {
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
	 * Método para carregar o jogo.
	 */
	private void carregarJogo() {
		cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
		cenario.carregar();
	}

	/**
	 * Método para iniciar o jogo.
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

						if (JogoSnake.nivel < Nivel.niveis.length) {
							cenario = new JogoCenario(tela.getWidth(), tela.getHeight());
						} else {
							cenario = new JogoCenarioDoRusso(tela.getWidth(), tela.getHeight());
						}

						cenario.carregar();

					} else {
						JogoSnake.pausado = !JogoSnake.pausado;
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
					if (!JogoSnake.pausado)
						cenario.atualizar();

					cenario.desenhar(g2d);
				}

				tela.repaint();
				prxAtualizacao = System.currentTimeMillis() + FPS;
			}
		}
	}

	/**
	 * Método principal para iniciar o jogo Snake.
	 *
	 * @param args Argumentos da linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		JogoSnake jogo = new JogoSnake();
		jogo.carregarJogo();
		jogo.iniciarJogo();
	}

}
