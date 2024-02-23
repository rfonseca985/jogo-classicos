package baseT;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Classe base que representa um elemento gráfico.
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
	 * Construtor padrão.
	 */
	public Elemento() {
	}

	/**
	 * Construtor que permite definir a posição e dimensões iniciais do elemento.
	 *
	 * @param px      Posição X do elemento
	 * @param py      Posição Y do elemento
	 * @param largura Largura do elemento
	 * @param altura  Altura do elemento
	 */
	public Elemento(int px, int py, int largura, int altura) {
		this.px = px;
		this.py = py;
		this.largura = largura;
		this.altura = altura;
	}

	/**
	 * Método de atualização do elemento. Pode ser sobrescrito nas subclasses para implementar lógica específica.
	 */
	public void atualiza() {
	}

	/**
	 * Método de desenho do elemento.
	 *
	 * @param g Objeto Graphics2D para desenho
	 */
	public void desenha(Graphics2D g) {
		g.setColor(cor);
		g.fillRect(px, py, largura, altura);
	}

	/**
	 * Obtém a largura do elemento.
	 *
	 * @return Largura do elemento
	 */
	public int getLargura() {
		return largura;
	}

	/**
	 * Define a largura do elemento.
	 *
	 * @param largura Nova largura a ser atribuída
	 */
	public void setLargura(int largura) {
		this.largura = largura;
	}

	/**
	 * Obtém a altura do elemento.
	 *
	 * @return Altura do elemento
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Define a altura do elemento.
	 *
	 * @param altura Nova altura a ser atribuída
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Obtém a posição X do elemento.
	 *
	 * @return Posição X do elemento
	 */
	public int getPx() {
		return px;
	}

	/**
	 * Define a posição X do elemento.
	 *
	 * @param px Nova posição X a ser atribuída
	 */
	public void setPx(int px) {
		this.px = px;
	}

	/**
	 * Obtém a posição Y do elemento.
	 *
	 * @return Posição Y do elemento
	 */
	public int getPy() {
		return py;
	}

	/**
	 * Define a posição Y do elemento.
	 *
	 * @param py Nova posição Y a ser atribuída
	 */
	public void setPy(int py) {
		this.py = py;
	}

	/**
	 * Obtém a velocidade do elemento.
	 *
	 * @return Velocidade do elemento
	 */
	public int getVel() {
		return vel;
	}

	/**
	 * Define a velocidade do elemento.
	 *
	 * @param vel Nova velocidade a ser atribuída
	 */
	public void setVel(int vel) {
		this.vel = vel;
	}

	/**
	 * Verifica se o elemento está ativo.
	 *
	 * @return true se o elemento está ativo, false caso contrário
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * Define o estado de ativação do elemento.
	 *
	 * @param ativo Novo estado de ativação
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Obtém a cor do elemento.
	 *
	 * @return Cor do elemento
	 */
	public Color getCor() {
		return cor;
	}

	/**
	 * Define a cor do elemento.
	 *
	 * @param cor Nova cor a ser atribuída
	 */
	public void setCor(Color cor) {
		this.cor = cor;
	}

	/**
	 * Incrementa a posição X do elemento.
	 *
	 * @param x Valor a ser incrementado na posição X
	 */
	public void incPx(int x) {
		px = px + x;
	}

	/**
	 * Incrementa a posição Y do elemento.
	 *
	 * @param y Valor a ser incrementado na posição Y
	 */
	public void incPy(int y) {
		py = py + y;
	}

	/**
	 * Representação em formato de string do elemento, exibindo as posições X e Y.
	 *
	 * @return String representando o elemento
	 */
	@Override
	public String toString() {
		return "Elemento [px=" + px + ", py=" + py + "]";
	}

}
