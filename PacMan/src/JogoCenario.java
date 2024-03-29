import base.CenarioPadrao;
import base.Elemento;
import base.Texto;
import base.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * A classe JogoCenario representa o cenário do jogo Pac-Man.
 * Implementa as regras e lógicas específicas do jogo.
 */
public class JogoCenario extends CenarioPadrao {

	/**
	 * Enumeração que define os estados possíveis do jogo.
	 */
	public enum Estado {
		JOGANDO, GANHOU, PERDEU;
	}

	/**
	 * Enumeração que define as direções possíveis.
	 */
	public enum Direcao {
		NORTE, SUL, OESTE, LESTE;
	}

	private static final boolean depurar = false;

	private Direcao prxDirecao = Direcao.OESTE;

	private int temporizadorPizza;
	private int temporizadorFantasma;

	private Pizza pizza;

	private Pizza[] inimigos;

	private int[][] grade;

	private Texto texto = new Texto();

	private Random rand = new Random();

	private Estado estado = Estado.JOGANDO;

	public JogoCenario(int largura, int altura) {
		super(largura, altura);
	}

	private ImageIcon fundo;

	private int largEl;

	private int espLinha = 6; // Espaço grossura linha

	public static final int ESPACO_TOPO = 25; // Espacamento topo

	private int totalPastilha;
	private int pontos;

	private int pontoFugaCol;
	private int pontoFugaLin;

	private int pontoVoltaCol;
	private int pontoVoltaLin;

	private boolean superPizza;

	@Override
	public void carregar() {
		grade = Nivel.cenario; // copiaNivel(Nivel.cenario);
		largEl = largura / grade[0].length; // 16

		texto.setCor(Color.WHITE);

		// fundo = new ImageIcon("imagem/fundo_original.png");

		pizza = new Pizza(0, 0, largEl, largEl);
		pizza.setVel(4);
		pizza.setAtivo(true);
		pizza.setCor(Color.YELLOW);
		pizza.setDirecao(Direcao.OESTE);

		// Inimigos
		inimigos = new Pizza[2];

		inimigos[0] = new Pizza(0, 0, largEl, largEl);
		inimigos[0].setVel(3 + JogoPacMan.nivel);
		inimigos[0].setAtivo(true);
		inimigos[0].setCor(Color.RED);
		inimigos[0].setDirecao(Direcao.OESTE);
		inimigos[0].setModo(Pizza.Modo.CACANDO);

		inimigos[1] = new Pizza(0, 0, largEl, largEl);
		inimigos[1].setVel(2 + JogoPacMan.nivel);
		inimigos[1].setAtivo(false);
		inimigos[1].setCor(Color.PINK);
		inimigos[1].setDirecao(Direcao.NORTE);
		inimigos[1].setModo(Pizza.Modo.PRESO);

		for (int lin = 0; lin < grade.length; lin++) {
			for (int col = 0; col < grade[0].length; col++) {
				if (grade[lin][col] == Nivel.CN || grade[lin][col] == Nivel.SC) {
					totalPastilha++;

				} else if (grade[lin][col] == Nivel.PI) {
					pizza.setPx(converteInidicePosicao(col));
					pizza.setPy(converteInidicePosicao(lin));

				} else if (grade[lin][col] == Nivel.P1) {
					inimigos[0].setPx(converteInidicePosicao(col));
					inimigos[0].setPy(converteInidicePosicao(lin));

				} else if (grade[lin][col] == Nivel.P2) {
					inimigos[1].setPx(converteInidicePosicao(col));
					inimigos[1].setPy(converteInidicePosicao(lin));

				} else if (grade[lin][col] == Nivel.PF) {
					pontoFugaCol = col;
					pontoFugaLin = lin;

				} else if (grade[lin][col] == Nivel.PV) {
					pontoVoltaCol = col;
					pontoVoltaLin = lin;
				}
			}
		}
	}

	protected int[][] copiaNivel(int[][] cenario) {
		int[][] temp = new int[cenario.length][cenario[0].length];
		for (int lin = 0; lin < cenario.length; lin++) {
			for (int col = 0; col < cenario[0].length; col++) {
				temp[lin][col] = cenario[lin][col];
			}
		}

		return temp;
	}

	/**
	 * Reinicia o jogo, resetando todas as configurações.
	 */
	public void reiniciar() {
		superPizza = false;
		temporizadorFantasma = 0;
		prxDirecao = Direcao.OESTE;

		pizza.setDirecao(Direcao.OESTE);

		inimigos[0].setDirecao(Direcao.OESTE);
		inimigos[0].setModo(Pizza.Modo.CACANDO);
		inimigos[0].setAtivo(true);

		inimigos[1].setDirecao(Direcao.NORTE);
		inimigos[1].setModo(Pizza.Modo.PRESO);
		inimigos[1].setAtivo(false);

		for (int lin = 0; lin < grade.length; lin++) {
			for (int col = 0; col < grade[0].length; col++) {
				if (grade[lin][col] == Nivel.PI) {
					pizza.setPx(converteInidicePosicao(col));
					pizza.setPy(converteInidicePosicao(lin));

				} else if (grade[lin][col] == Nivel.P1) {
					inimigos[0].setPx(converteInidicePosicao(col));
					inimigos[0].setPy(converteInidicePosicao(lin));

				} else if (grade[lin][col] == Nivel.P2) {
					inimigos[1].setPx(converteInidicePosicao(col));
					inimigos[1].setPy(converteInidicePosicao(lin));

				}
			}
		}
	}

	/**
	 * Descarrega recursos e elementos do jogo.
	 */
	@Override
	public void descarregar() {
		pizza = null;
		grade = null;
		inimigos = null;
	}

	/**
	 * Atualiza a lógica do jogo.
	 */
	@Override
	public void atualizar() {

		if (estado != Estado.JOGANDO) {
			return;
		}

		if (JogoPacMan.controleTecla[JogoPacMan.Tecla.ESQUERDA.ordinal()]) {
			prxDirecao = Direcao.OESTE;

		} else if (JogoPacMan.controleTecla[JogoPacMan.Tecla.DIREITA.ordinal()]) {
			prxDirecao = Direcao.LESTE;

		} else if (JogoPacMan.controleTecla[JogoPacMan.Tecla.CIMA.ordinal()]) {
			prxDirecao = Direcao.NORTE;

		} else if (JogoPacMan.controleTecla[JogoPacMan.Tecla.BAIXO.ordinal()]) {
			prxDirecao = Direcao.SUL;
		}

		pizza.setDirecao(prxDirecao);
		atualizaDirecao(pizza);
		corrigePosicao(pizza);
		comePastilha(pizza);
		pizza.atualiza();

		if (superPizza && temporizadorPizza > 200) {
			temporizadorPizza = 0;
			superPizza(false);

		} else
			temporizadorPizza += 1;

		for (Pizza el : inimigos) {
			if (el == null)
				continue;

			atualizaDirecaoInimigos(el);
			corrigePosicao(el);
			el.atualiza();

			if (Util.colide(pizza, el)) {

				if (el.getModo() == Pizza.Modo.CACANDO) {
					reiniciar(); // Jogador perdeu
				} else if (el.getModo() == Pizza.Modo.FUGINDO) {
					el.setAtivo(false);
					el.setModo(Pizza.Modo.FANTASMA);
					pontos += 50;
				}
			}
		}
	}

	/**
	 * Valida se a direção é possível para o elemento.
	 *
	 * @param dir Direção a ser validada.
	 * @param el  Elemento a ser considerado.
	 * @return true se a direção é válida, false caso contrário.
	 */
	private boolean validaDirecao(Direcao dir, Pizza el) {

		if (dir == Direcao.OESTE && validaMovimento(el, -1, 0))
			return true;

		else if (dir == Direcao.LESTE && validaMovimento(el, 1, 0))
			return true;

		else if (dir == Direcao.NORTE && validaMovimento(el, 0, -1))
			return true;

		else if (dir == Direcao.SUL && validaMovimento(el, 0, 1))
			return true;

		return false;
	}

	/**
	 * Valida se o movimento é possível para o elemento.
	 *
	 * @param el Elemento a ser considerado.
	 * @param dx Deslocamento horizontal.
	 * @param dy Deslocamento vertical.
	 * @return true se o movimento é válido, false caso contrário.
	 */
	private boolean validaMovimento(Pizza el, int dx, int dy) {
		// Proxima posicao x e y
		int prxPosX = el.getPx() + el.getVel() * dx;
		int prxPosY = el.getPy() + el.getVel() * dy;

		// Coluna e linha
		int col = convertePosicaoIndice(prxPosX);
		int lin = convertePosicaoIndice(prxPosY);

		// Coluna + largura e linha + altura
		int colLarg = convertePosicaoIndice(prxPosX + el.getLargura() - el.getVel());
		int linAlt = convertePosicaoIndice(prxPosY + el.getAltura() - el.getVel());

		if (foraDaGrade(col, lin) || foraDaGrade(colLarg, linAlt))
			return true;

		if (grade[lin][col] == Nivel.BL || grade[lin][colLarg] == Nivel.BL || grade[linAlt][col] == Nivel.BL
				|| grade[linAlt][colLarg] == Nivel.BL) {

			return false;
		}

		// Validar linha branca
		if (el.isAtivo() || el.getModo() == Pizza.Modo.PRESO) {
			if (grade[lin][col] == Nivel.LN || grade[lin][colLarg] == Nivel.LN || grade[linAlt][col] == Nivel.LN
					|| grade[linAlt][colLarg] == Nivel.LN) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Atualiza a direção do elemento.
	 *
	 * @param el Elemento a ser considerado.
	 */
	private void atualizaDirecao(Pizza el) {

		if (foraDaTela(el))
			return;

		// Temporario Direcao X e Y
		int tempDx = el.getDx();
		int tempDy = el.getDy();

		Direcao direcao = el.getDirecao();

		if (validaDirecao(direcao, el)) {
			if (direcao == Direcao.OESTE)
				tempDx = -1;
			else if (direcao == Direcao.LESTE)
				tempDx = 1;

			if (direcao == Direcao.NORTE)
				tempDy = -1;
			else if (direcao == Direcao.SUL)
				tempDy = 1;

		}

		if (!validaMovimento(el, tempDx, tempDy))
			tempDx = tempDy = 0;

		el.setDx(tempDx);
		el.setDy(tempDy);
	}

	/**
	 * Atualiza a direção dos inimigos.
	 *
	 * @param el Inimigo a ser considerado.
	 */
	private void atualizaDirecaoInimigos(Pizza el) {

		if (foraDaTela(el))
			return;

		int col = convertePosicaoIndice(el.getPx());
		int lin = convertePosicaoIndice(el.getPy());

		Direcao direcao = el.getDirecao();

		// Variaveis auxiliares
		Direcao tempDir = null;
		int tempDx = 0, tempDy = 0;
		int xCol = 0, yLin = 0;

		if (el.getModo() == Pizza.Modo.PRESO) {
			if (el.getDirecao() == Direcao.SUL && !validaDirecao(Direcao.SUL, el))
				el.setDirecao(Direcao.NORTE);

			else if (el.getDirecao() == Direcao.NORTE && !validaDirecao(Direcao.NORTE, el))
				el.setDirecao(Direcao.SUL);

			else if (el.getDirecao() != Direcao.NORTE && el.getDirecao() != Direcao.SUL)
				el.setDirecao(Direcao.NORTE);

			if (temporizadorFantasma > 50)
				el.setModo(Pizza.Modo.ATIVO);
			else
				temporizadorFantasma++;

		} else if (el.getModo() == Pizza.Modo.ATIVO) {
			xCol = pontoFugaCol;
			yLin = pontoFugaLin;

			int colLarg = convertePosicaoIndice(el.getPx() + el.getLargura() - el.getVel());
			int linAlt = convertePosicaoIndice(el.getPy() + el.getAltura() - el.getVel());

			if (lin > yLin && validaDirecao(Direcao.NORTE, el))
				el.setDirecao(Direcao.NORTE);

			else if (lin < yLin && validaDirecao(Direcao.SUL, el))
				el.setDirecao(Direcao.SUL);

			else if (col < xCol && validaDirecao(Direcao.LESTE, el))
				el.setDirecao(Direcao.LESTE);

			else if (col > xCol && validaDirecao(Direcao.OESTE, el))
				el.setDirecao(Direcao.OESTE);

			else if (col == xCol && lin == yLin && colLarg == xCol && linAlt == yLin) {
				el.setAtivo(true);
				el.setModo(Pizza.Modo.CACANDO);
			}

		} else if (el.getModo() == Pizza.Modo.CACANDO || el.getModo() == Pizza.Modo.FUGINDO) {

			xCol = convertePosicaoIndice(pizza.getPx());
			yLin = convertePosicaoIndice(pizza.getPy());

			// Inverte posicao para fugir
			if (el.getModo() == Pizza.Modo.FUGINDO) {
				xCol = xCol * -1;
				yLin = yLin * -1;
			}

			// TODO melhorar, problema de leg
			boolean perdido = rand.nextInt(100) == 35;

			if (el.isAtivo() && perdido) {
				tempDir = sorteiaDirecao();

			} else if (direcao == null) {
				direcao = sorteiaDirecao();

			} else if (direcao == Direcao.NORTE || direcao == Direcao.SUL) {
				if (xCol < col && validaDirecao(Direcao.OESTE, el))
					tempDir = Direcao.OESTE;
				else if (xCol > col && validaDirecao(Direcao.LESTE, el))
					tempDir = Direcao.LESTE;

			} else {
				/* direcao = OESTE ou LESTE */
				if (yLin < lin && validaDirecao(Direcao.NORTE, el))
					tempDir = Direcao.NORTE;
				else if (yLin > lin && validaDirecao(Direcao.SUL, el))
					tempDir = Direcao.SUL;
			}

			if (tempDir != null && validaDirecao(tempDir, el))
				el.setDirecao(tempDir);
			else if (!validaDirecao(el.getDirecao(), el))
				el.setDirecao(sorteiaDirecao());

		} else if (el.getModo() == Pizza.Modo.FANTASMA) {
			xCol = pontoFugaCol;
			yLin = pontoFugaLin;

			if (direcao == Direcao.NORTE || direcao == Direcao.SUL) {
				if (xCol < col && validaDirecao(Direcao.OESTE, el))
					tempDir = Direcao.OESTE;
				else if (xCol > col && validaDirecao(Direcao.LESTE, el))
					tempDir = Direcao.LESTE;

			} else {
				if (yLin < lin && validaDirecao(Direcao.NORTE, el))
					tempDir = Direcao.NORTE;
				else if (yLin > lin && validaDirecao(Direcao.SUL, el))
					tempDir = Direcao.SUL;
			}

			if (tempDir != null && validaDirecao(tempDir, el))
				el.setDirecao(tempDir);
			else if (!validaDirecao(el.getDirecao(), el))
				el.setDirecao(trocaDirecao(el.getDirecao()));

			if (col == xCol && lin == yLin)
				el.setModo(Pizza.Modo.INATIVO);

		} else if (el.getModo() == Pizza.Modo.INATIVO) {
			xCol = pontoVoltaCol;
			yLin = pontoVoltaLin;

			if (lin > yLin && validaDirecao(Direcao.NORTE, el))
				el.setDirecao(Direcao.NORTE);

			else if (lin < yLin && validaDirecao(Direcao.SUL, el))
				el.setDirecao(Direcao.SUL);

			else if (col < xCol && validaDirecao(Direcao.LESTE, el))
				el.setDirecao(Direcao.LESTE);

			else if (col > xCol && validaDirecao(Direcao.OESTE, el))
				el.setDirecao(Direcao.OESTE);

			else if (col == xCol && lin == yLin)
				el.setModo(Pizza.Modo.PRESO);
		}

		if (validaDirecao(el.getDirecao(), el)) {
			if (el.getDirecao() == Direcao.NORTE)
				tempDy = -1;
			else if (el.getDirecao() == Direcao.SUL)
				tempDy = 1;
			else if (el.getDirecao() == Direcao.OESTE)
				tempDx = -1;
			else if (el.getDirecao() == Direcao.LESTE)
				tempDx = 1;
		}

		el.setDx(tempDx);
		el.setDy(tempDy);
	}

	private Direcao trocaDirecao(Direcao direcao) {
		if (direcao == Direcao.NORTE)
			return Direcao.OESTE;
		else if (direcao == Direcao.OESTE)
			return Direcao.SUL;
		else if (direcao == Direcao.SUL)
			return Direcao.LESTE;
		else
			return Direcao.NORTE;
	}

	/**
	 * Sorteia uma direção aleatória.
	 *
	 * @return Direção sorteada.
	 */
	private Direcao sorteiaDirecao() {
		return Direcao.values()[rand.nextInt(Direcao.values().length)];
	}

	/**
	 * Verifica se o índice está fora da grade.
	 *
	 * @param col Índice da coluna.
	 * @param lin Índice da linha.
	 * @return true se estiver fora da grade, false caso contrário.
	 */
	private boolean foraDaGrade(int coluna, int linha) {
		if (linha < 0 || linha >= grade.length)
			return true;

		if (coluna < 0 || coluna >= grade[0].length)
			return true;

		return false;
	}

	/**
	 * Verifica se o elemento está fora da tela.
	 *
	 * @param el Elemento a ser verificado.
	 * @return true se estiver fora da tela, false caso contrário.
	 */
	private boolean foraDaTela(Elemento el) {
		if (el.getPx() < 0 || el.getPx() + el.getLargura() > largura)
			return true;

		if (el.getPy() < 0 || el.getPy() + el.getAltura() > altura)
			return true;

		return false;
	}

	/**
	 * Corrige a posição do elemento quando está fora da grade.
	 *
	 * @param el Elemento a ser corrigido.
	 */
	private void corrigePosicao(Pizza el) {
		int novaPx = el.getPx(); // Nova posição x
		int novaPy = el.getPy(); // Nova posição y

		int col = convertePosicaoIndice(el.getPx()) * largEl;
		int lin = convertePosicaoIndice(el.getPy()) * largEl;

		if (el.getDx() == 0 && novaPx != col)
			novaPx = col;
		else if (el.getPx() + largEl < 0)
			novaPx = largura;
		else if (el.getPx() > largura)
			novaPx = -largEl;

		if (el.getDy() == 0 && novaPy != lin)
			novaPy = lin;
		else if (el.getPy() + largEl < 0)
			novaPy = altura;
		else if (el.getPy() > altura)
			novaPy = -largEl;

		el.setPx(novaPx);
		el.setPy(novaPy);
	}

	/**
	 * Verifica se o elemento colide com uma pastilha.
	 *
	 * @param el Elemento a ser verificado.
	 */
	private void comePastilha(Elemento el) {
		int col = convertePosicaoIndice(el.getPx());
		int lin = convertePosicaoIndice(el.getPy());

		if (foraDaGrade(col, lin)) {
			return;
		}

		if (grade[lin][col] == Nivel.CN || grade[lin][col] == Nivel.SC) {
			pontos += grade[lin][col] == Nivel.CN ? 5 : 25;
			totalPastilha--;

			if (totalPastilha == 0)
				estado = JogoCenario.Estado.GANHOU;
			else if (grade[lin][col] == Nivel.SC)
				superPizza(true);

			grade[lin][col] = Nivel.EV;
		}
	}

	/**
	 * Ativa ou desativa o modo "Super Pizza".
	 *
	 * @param ativar true para ativar, false para desativar.
	 */
	private void superPizza(boolean modoSuper) {
		superPizza = modoSuper;
		temporizadorPizza = 0;

		for (Pizza el : inimigos) {
			if (el == null)
				continue;

			if (modoSuper && el.getModo() == Pizza.Modo.CACANDO)
				el.setModo(Pizza.Modo.FUGINDO);
			else if (!modoSuper && el.getModo() == Pizza.Modo.FUGINDO)
				el.setModo(Pizza.Modo.CACANDO);
		}
	}
	/**
	 * Converte o índice da grade para a posição do elemento.
	 *
	 * @param indice Índice na grade.
	 * @return Posição correspondente do elemento.
	 */
	private int converteInidicePosicao(int linhaColuna) {
		return linhaColuna * largEl;
	}

	/**
	 * Converte a posição do elemento para o índice da grade.
	 *
	 * @param pos Posição do elemento.
	 * @return Índice correspondente na grade.
	 */
	private int convertePosicaoIndice(int eixoXY) {
		return eixoXY / largEl;
	}

	protected void imprimeMovimento(int x, int y, int ix, int iy, int dx, int dy) {

		int px, py;

		px = x + ix * dx;
		py = y + iy * dy;

		int col = convertePosicaoIndice(px);
		int lin = convertePosicaoIndice(py);

		int col2 = convertePosicaoIndice(px + 14);
		int lin2 = convertePosicaoIndice(py + 14);

		System.out.print("[x=" + x + ", y=" + y + ", ix=" + ix + ", iy=" + iy + ", dx=" + dx + ", dy=" + dy + "]");
		System.out.print("[lin=" + lin + ", col=" + col + "]");
		System.out.println("[lin2=" + lin2 + ", col2=" + col2 + "]");

	}

	/**
	 * Desenha os elementos do jogo na tela.
	 *
	 * @param g2d Objeto Graphics2D para desenho.
	 */
	@Override
	public void desenhar(Graphics2D g) {

		if (fundo != null) {
			g.drawImage(fundo.getImage(), 0, ESPACO_TOPO, null);

		} else {
			for (int lin = 0; lin < grade.length; lin++) {
				for (int col = 0; col < grade[0].length; col++) {
					int valor = grade[lin][col];

					if (valor == Nivel.BL) {
						g.setColor(superPizza ? Color.DARK_GRAY : Color.BLUE);
						g.fillRect(col * largEl, lin * largEl + ESPACO_TOPO, largEl, largEl);

					} else if (valor == Nivel.CN) {
						g.setColor(Color.WHITE);
						g.fillRect(col * largEl + espLinha, lin * largEl + espLinha + ESPACO_TOPO, largEl - espLinha * 2, largEl
								- espLinha * 2);

					} else if (valor == Nivel.SC) {
						g.setColor(Color.WHITE);
						g.fillRect(col * largEl + espLinha / 2, lin * largEl + espLinha / 2 + ESPACO_TOPO, largEl - espLinha, largEl - espLinha);

					} else if (valor == Nivel.LN) {
						g.setColor(Color.WHITE);
						g.fillRect(col * largEl, lin * largEl + espLinha + ESPACO_TOPO, largEl, largEl - espLinha * 2);
					}
				}
			}
		}

		texto.desenha(g, "Pontos: " + pontos, 10, 20);

		pizza.desenha(g);

		for (Elemento el : inimigos) {
			if (el == null)
				continue;

			el.desenha(g);
		}

		if (depurar) {
			g.setColor(Color.WHITE);
			for (int i = 0; i < grade.length; i++) {
				for (int j = 0; j < grade[0].length; j++)
					g.drawRect(j * largEl, i * largEl + ESPACO_TOPO, largEl, largEl);

			}

			int col = convertePosicaoIndice(pizza.getPx());
			int lin = convertePosicaoIndice(pizza.getPy());

			g.setColor(Color.ORANGE);
			g.fillRect(col * 16, lin * 16 + ESPACO_TOPO, 16, 16);
		}

	}

}
