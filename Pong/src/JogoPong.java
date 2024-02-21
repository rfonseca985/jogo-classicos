import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import baseP.CenarioPadrao;

/**
 * A classe JogoPong representa o ambiente principal do jogo, estendendo a classe JFrame.
 * Responsável por controlar a execução do jogo, capturar entradas do teclado e do mouse, e gerenciar os cenários.
 */
public class JogoPong extends JFrame {

	/**
	 * Número de versão para serialização.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Taxa de quadros por segundo.
	 */
	private static final int FPS = 1000 / 20;

	/**
	 * Altura da janela do jogo.
	 */
	private static final int JANELA_ALTURA = 480;

	/**
	 * Largura da janela do jogo.
	 */
	private static final int JANELA_LARGURA = 640;

	/**
	 * Painel para desenhar os gráficos.
	 */
	private JPanel tela;

	/**
	 * Contexto gráfico 2D para desenhar.
	 */
	private Graphics2D g2d;

	/**
	 * Imagem de buffer para evitar flickering.
	 */
	private BufferedImage buffer;

	/**
	 * Cenário atual do jogo.
	 */
	private CenarioPadrao cenario;

	/**
	 * Enumeração das teclas utilizadas no jogo.
	 */
	public enum Tecla {
		CIMA, BAIXO, ESQUERDA, DIREITA, BA, BB
	}

	/**
	 * Posição Y do mouse.
	 */
	public static int mouseY;

	/**
	 * Array de controle de teclas.
	 */
	public static boolean[] controleTecla = new boolean[Tecla.values().length];

	/**
	 * Libera todas as teclas, resetando o array de controle.
	 */
	public static void liberaTeclas() {
		for (int i = 0; i < controleTecla.length; i++) {
			controleTecla[i] = false;
		}
	}

	/**
	 * Define o estado de uma tecla no array de controle.
	 *
	 * @param tecla        Código da tecla.
	 * @param pressionada  Estado da tecla (pressionada ou não).
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
	 * Estado de pausa do jogo.
	 */
	public static boolean pausado;

	/**
	 * Velocidade do jogo.
	 */
	public static int velocidade;

	/**
	 * Modo de jogo.
	 */
	public static boolean modoNormal;

	/**
	 * Construtor da classe JogoPong.
	 * Configura os listeners de teclado e mouse, cria o buffer e exibe a janela.
	 */
	public JogoPong() {
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
		};

		tela.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				mouseY = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});

		getContentPane().add(tela);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(JANELA_LARGURA, JANELA_ALTURA);

		setVisible(true);
		tela.repaint();

	}

	/**
	 * Carrega o cenário inicial do jogo.
	 */
	private void carregarJogo() {
		cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
		cenario.carregar();
	}

	/**
	 * Inicia a execução do jogo.
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
						cenario = new JogoCenario(tela.getWidth(), tela.getHeight());
						cenario.carregar();

					} else if (cenario instanceof JogoCenario) {
						JogoPong.pausado = !JogoPong.pausado;
					}

					liberaTeclas();

				} else if (controleTecla[Tecla.BB.ordinal()]) {
					// Pressionou ESQ
					if (cenario instanceof JogoCenario) {
						cenario.descarregar();
						cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
						cenario.carregar();
					}

					liberaTeclas();
				}

				if (cenario == null) {
					g2d.setColor(Color.WHITE);
					g2d.drawString("Carregando...", 20, 20);

				} else {
					cenario.atualizar();
					cenario.desenhar(g2d);
				}

				tela.repaint();
				prxAtualizacao = System.currentTimeMillis() + FPS;
			}
		}
	}

	/**
	 * Método principal que cria uma instância do jogo, carrega o cenário inicial e inicia o jogo.
	 *
	 * @param args Argumentos da linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		JogoPong jogoPong = new JogoPong();
		jogoPong.carregarJogo();
		jogoPong.iniciarJogo();
	}

}
