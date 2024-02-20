package base;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 * A classe Elemento representa um objeto genérico em um jogo, podendo ser estendida para criar
 * elementos específicos. Ela possui propriedades como posição (px, py), largura, altura, velocidade,
 * estado de atividade e cor, além de métodos para atualização, desenho e manipulação da posição.
 */
public class Elemento {

	/** Posição X do elemento na tela. */
	private int px;

	/** Posição Y do elemento na tela. */
	private int py;

	/** Largura do elemento. */
	private int largura;

	/** Altura do elemento. */
	private int altura;

	/** Velocidade do elemento. */
	private int vel;

	/** Estado de atividade do elemento. */
	private boolean ativo;

	/** Cor do elemento. */
	private Color cor;

	/**
	 * Construtor padrão da classe Elemento.
	 */
	public Elemento() {
	}

	/**
	 * Construtor parametrizado da classe Elemento.
	 *
	 * @param px      Posição X inicial do elemento.
	 * @param py      Posição Y inicial do elemento.
	 * @param largura Largura do elemento.
	 * @param altura  Altura do elemento.
	 */
	public Elemento(int px, int py, int largura, int altura) {
		this.px = px;
		this.py = py;
		this.largura = largura;
		this.altura = altura;
	}

	/**
	 * Atualiza o estado do elemento (método a ser implementado nas subclasses).
	 */
	public void atualiza() {
		// Implementação específica para cada elemento
	}

	/**
	 * Desenha o elemento na tela usando o contexto gráfico fornecido.
	 *
	 * @param g Contexto gráfico no qual o elemento será desenhado.
	 */
	public void desenha(Graphics2D g) {
		g.drawRect(px, py, largura, altura);
	}

	// Métodos de acesso e modificação omitidos para brevidade...

	/**
	 * Incrementa a posição X do elemento.
	 *
	 * @param x Valor a ser incrementado na posição X.
	 */
	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}

	public int getVel() {
		return vel;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
	public void incPx(int x) {
		px = px + x;
	}

	/**
	 * Incrementa a posição Y do elemento.
	 *
	 * @param y Valor a ser incrementado na posição Y.
	 */
	public void incPy(int y) {
		py = py + y;
	}
}
