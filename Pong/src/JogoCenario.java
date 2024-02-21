import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import baseP.CenarioPadrao;
import baseP.Elemento;
import baseP.Texto;
import baseP.Util;

/**
 * A classe JogoCenario representa o cenário do jogo Pong, estendendo a classe CenarioPadrao.
 * Contém a lógica do jogo, elementos como a bola, raquetes e pontos.
 */
public class JogoCenario extends CenarioPadrao {

	private float inc = 0.5f;

	private Ponto pontoA, pontoB;

	private Bola bola;

	private Elemento esquerda;

	private Elemento direita;

	private boolean reiniciarJogada;

	private final Texto textoPausa = new Texto(Ponto.fonte);

	// Modo em casa
	private int idx;

	private Bola[] bolaArr = new Bola[0];

	private Random rand;

	/**
	 * Construtor da classe JogoCenario, recebe a largura e altura do cenário.
	 *
	 * @param largura Largura do cenário.
	 * @param altura  Altura do cenário.
	 */
	public JogoCenario(int largura, int altura) {
		super(largura, altura);

		bola = new Bola();
		esquerda = new Elemento();
		direita = new Elemento();

		pontoA = new Ponto();
		pontoB = new Ponto();
	}

	/**
	 * Inicializa os elementos do jogo no carregamento do cenário.
	 */
	@Override
	public void carregar() {
		bola.setVel(JogoPong.velocidade);

		pontoA.setPx(largura / 2 - 120);
		pontoA.setPy(Ponto.TAMANHO_FONTE);

		pontoB.setPx(largura / 2 + 120 - Ponto.TAMANHO_FONTE / 2);
		pontoB.setPy(Ponto.TAMANHO_FONTE);

		esquerda.setVel(5);
		esquerda.setAltura(70);
		esquerda.setLargura(5);
		esquerda.setCor(Color.WHITE);

		direita.setVel(5);
		direita.setAltura(70);
		direita.setLargura(5);
		direita.setCor(Color.WHITE);
		direita.setPx(largura - direita.getLargura());

		Util.centraliza(bola, largura, altura);
		Util.centraliza(direita, 0, altura);
		Util.centraliza(esquerda, 0, altura);

		bola.setAtivo(true);
		direita.setAtivo(true);
		esquerda.setAtivo(true);

		if (!JogoPong.modoNormal) {
			rand = new Random();
			bolaArr = new Bola[30];

			for (int i = 0; i < bolaArr.length; i++) {
				int v = rand.nextInt(3) + 1;

				bolaArr[i] = new Bola();
				bolaArr[i].setDirX(i % 2 == 0 ? -1 : 1);

				bolaArr[i].setVel(Bola.VEL_INICIAL * v);
				bolaArr[i].setAltura(bola.getAltura() * v);
				bolaArr[i].setLargura(bola.getLargura() * v);

				Util.centraliza(bolaArr[i], largura, altura);
			}
		}
	}

	/**
	 * Descarrega recursos do cenário.
	 */
	@Override
	public void descarregar() {
	}

	/**
	 * Atualiza a lógica do jogo.
	 */
	@Override
	public void atualizar() {

		if (JogoPong.pausado)
			return;

		bola.incPx();
		bola.incPy();

		if (JogoPong.controleTecla[JogoPong.Tecla.CIMA.ordinal()]) {
			esquerda.incPy(esquerda.getVel() * -1);
		} else if (JogoPong.controleTecla[JogoPong.Tecla.BAIXO.ordinal()]) {
			esquerda.incPy(esquerda.getVel());
		}

		if (direita.getPy() + direita.getAltura() / 2 > JogoPong.mouseY + direita.getVel())
			direita.incPy(direita.getVel() * -1);
		else if (direita.getPy() + direita.getAltura() / 2 < JogoPong.mouseY - direita.getVel())
			direita.incPy(direita.getVel());

		validaPosicao(esquerda);
		validaPosicao(direita);

		if (reiniciarJogada) {
			reiniciarJogada = false;
			bola.inverteX();
			bola.setVel(JogoPong.velocidade);
			Util.centraliza(bola, largura, altura);

		} else {
			reiniciarJogada = validaColisao(bola);
		}

		validaPosicao(bola);

		for (Bola b : bolaArr) {

			if (!b.isAtivo())
				continue;

			b.incPx();
			b.incPy();

			boolean saiu = validaColisao(b);
			if (saiu) {
				b.setAtivo(false);
				Util.centraliza(b, largura, altura);
			} else
				validaPosicao(b);

		}
	}

	/**
	 * Desenha os elementos gráficos do cenário.
	 *
	 * @param g Objeto Graphics2D para desenhar no cenário.
	 */
	@Override
	public void desenhar(Graphics2D g) {
		// Desenha linha de fundo
		for (int i = 0; i < altura; i += 20) {
			g.setColor(Color.WHITE);
			g.drawRect(largura / 2 - 2, i, 4, 10);
		}

		pontoA.desenha(g);
		pontoB.desenha(g);

		bola.desenha(g);

		for (Bola b : bolaArr) {
			b.desenha(g);
		}

		esquerda.desenha(g);
		direita.desenha(g);

		if (JogoPong.pausado)
			textoPausa.desenha(g, "PAUSA", largura / 2 - Ponto.TAMANHO_FONTE, altura / 2);

	}

	private boolean validaColisao(Bola b) {
		boolean saiu = false;

		if (Util.colide(esquerda, b)) {
			rebate(esquerda, b);

		} else if (Util.colide(direita, b)) {
			rebate(direita, b);

		} else if (b.getPx() < 0 || b.getPx() + b.getLargura() > largura) {

			saiu = true;

			if (b.getPx() < 0)
				pontoB.add();
			else
				pontoA.add();

		} else if (b.getPy() <= 0 || b.getPy() + b.getAltura() >= altura) {
			// Colisao no topo ou base da tela
			b.inverteY();
		}

		return saiu;
	}

	/**
	 * Método para depurar colisões. Não afeta o funcionamento do jogo.
	 *
	 * @param el Elemento a ser depurado.
	 * @param g2d Objeto Graphics2D para desenhar as linhas de depuração.
	 */
	public void depurarColisao(Elemento el, Graphics2D g2d) {
		int p = el.getPx() == 0 ? 6 : -6;
		int x1 = el.getPx() + p;

		g2d.setColor(Color.RED);
		g2d.drawLine(x1, el.getPy(), el.getPx() + p, el.getPy() + el.getAltura() / 3);

		g2d.setColor(Color.GREEN);
		g2d.drawLine(x1, el.getPy() + el.getAltura() - el.getAltura() / 3, el.getPx() + p, el.getPy() + el.getAltura());
	}

	/**
	 * Método para rebater a bola após colisão com uma raquete.
	 *
	 * @param raquete Raquete que colidiu.
	 * @param bola    Bola que colidiu.
	 */
	public void rebate(Elemento raquete, Bola bola) {
		float vx = bola.getVelX();
		float vy = bola.getVelY();

		if (bola.getPy() < raquete.getPy() + raquete.getAltura() / 3) {
			bola.setDirY(-1);

			vx += inc;
			vy += inc;

			if (bola.getPy() < raquete.getPy()) {
				vy += inc;
			}

		} else if (bola.getPy() > raquete.getPy() + raquete.getAltura() - raquete.getAltura() / 3) {
			bola.setDirY(1);

			vx += inc;
			vy += inc;

			if (bola.getPy() + bola.getAltura() > raquete.getPy() + raquete.getAltura()) {
				vy += inc;
			}

		} else {
			vx += inc;
			vy = 1;
		}

		bola.inverteX();
		bola.incVel(vx, vy);

		if (bolaArr.length > 0) {
			if (idx < bolaArr.length) {
				bolaArr[idx++].setAtivo(true);

			} else {
				idx = 0;
			}
		}
	}

	/**
	 * Valida a posição de um elemento para garantir que não ultrapasse os limites do cenário.
	 *
	 * @param el Elemento a ter a posição validada.
	 */
	private void validaPosicao(Elemento el) {
		if (el.getPy() < 0)
			el.setPy(0);
		else if (el.getPy() + el.getAltura() > altura)
			el.setPy(altura - el.getAltura());
	}

}
