package baseP;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A classe Elemento representa um elemento gráfico com posição, dimensões e propriedades visuais.
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
	 * Construtor padrão da classe Elemento.
	 */
	public Elemento() {
	}

	/**
	 * Construtor que inicializa a posição (px, py), largura e altura do elemento.
	 *
	 * @param px      Coordenada x da posição do elemento.
	 * @param py      Coordenada y da posição do elemento.
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
	 */
	public void atualiza() {
		// Implementação vazia por padrão, pode ser sobrescrito nas subclasses.
	}

	/**
	 * Método para desenhar o elemento utilizando um contexto gráfico.
	 *
	 * @param g Objeto Graphics2D para desenhar o elemento.
	 */
	public void desenha(Graphics2D g) {
		g.setColor(cor);
		g.fillRect(px, py, largura, altura);
	}

	/**
	 * Obtém a largura do elemento.
	 *
	 * @return Largura do elemento.
	 */
	public int getLargura() {
		return largura;
	}

	/**
	 * Define a largura do elemento.
	 *
	 * @param largura Nova largura do elemento.
	 */
	public void setLargura(int largura) {
		this.largura = largura;
	}

	/**
	 * Obtém a altura do elemento.
	 *
	 * @return Altura do elemento.
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Define a altura do elemento.
	 *
	 * @param altura Nova altura do elemento.
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Obtém a coordenada x da posição do elemento.
	 *
	 * @return Coordenada x da posição do elemento.
	 */
	public int getPx() {
		return px;
	}

	/**
	 * Define a coordenada x da posição do elemento.
	 *
	 * @param px Nova coordenada x da posição do elemento.
	 */
	public void setPx(int px) {
		this.px = px;
	}

	/**
	 * Obtém a coordenada y da posição do elemento.
	 *
	 * @return Coordenada y da posição do elemento.
	 */
	public int getPy() {
		return py;
	}

	/**
	 * Define a coordenada y da posição do elemento.
	 *
	 * @param py Nova coordenada y da posição do elemento.
	 */
	public void setPy(int py) {
		this.py = py;
	}

	/**
	 * Obtém a velocidade do elemento.
	 *
	 * @return Velocidade do elemento.
	 */
	public int getVel() {
		return vel;
	}

	/**
	 * Define a velocidade do elemento.
	 *
	 * @param vel Nova velocidade do elemento.
	 */
	public void setVel(int vel) {
		this.vel = vel;
	}

	/**
	 * Verifica se o elemento está ativo.
	 *
	 * @return true se o elemento está ativo, false caso contrário.
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * Define o estado de ativação do elemento.
	 *
	 * @param ativo Novo estado de ativação do elemento.
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Obtém a cor do elemento.
	 *
	 * @return Cor do elemento.
	 */
	public Color getCor() {
		return cor;
	}

	/**
	 * Define a cor do elemento.
	 *
	 * @param cor Nova cor do elemento.
	 */
	public void setCor(Color cor) {
		this.cor = cor;
	}

	/**
	 * Incrementa a coordenada x da posição do elemento.
	 *
	 * @param x Valor a ser incrementado na coordenada x.
	 */
	public void incPx(int x) {
		px = px + x;
	}

	/**
	 * Incrementa a coordenada y da posição do elemento.
	 *
	 * @param y Valor a ser incrementado na coordenada y.
	 */
	public void incPy(int y) {
		py = py + y;
	}

	/**
	 * Representação em formato de String do elemento, exibindo as coordenadas x e y da posição.
	 *
	 * @return Representação do elemento em String.
	 */
	@Override
	public String toString() {
		return "Elemento [px=" + px + ", py=" + py + "]";
	}

}
