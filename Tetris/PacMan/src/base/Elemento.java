package base;

import java.awt.*;

/**
 * A classe Elemento representa um elemento gráfico em um cenário de jogo.
 * Pode ser utilizado como base para diferentes tipos de elementos como personagens,
 * obstáculos, entre outros.
 */
public class Elemento {

	private int px;
	private int py;
	private int largura;
	private int altura;

	private int vel;
	private boolean ativo;
	private Color cor;

	/**
	 * Construtor padrão sem parâmetros.
	 */
	public Elemento() {
	}

	/**
	 * Construtor que define a posição e dimensões iniciais do elemento.
	 *
	 * @param px      Coordenada x inicial.
	 * @param py      Coordenada y inicial.
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
	 * Método para atualizar o estado do elemento.
	 * Implementações concretas podem fornecer lógica específica de atualização.
	 */
	public void atualiza() {
		// Lógica de atualização específica (pode ser sobrescrito nas subclasses)
	}

	/**
	 * Método para desenhar o elemento no contexto gráfico.
	 *
	 * @param g Contexto gráfico no qual o elemento será desenhado.
	 */
	public void desenha(Graphics2D g) {
		g.setColor(cor);
		g.fillRect(px, py, largura, altura);
	}

	// Métodos getters e setters
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

	/**
	 * Incrementa a coordenada x do elemento.
	 *
	 * @param x Valor a ser incrementado na coordenada x.
	 */
	public void incPx(int x) {
		px = px + x;
	}

	/**
	 * Incrementa a coordenada y do elemento.
	 *
	 * @param y Valor a ser incrementado na coordenada y.
	 */
	public void incPy(int y) {
		py = py + y;
	}

	// Sobrescrita do método toString

	@Override
	public String toString() {
		return "Elemento [px=" + px + ", py=" + py + "]";
	}
}
