package baseS;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Uma classe base que representa um elemento no contexto gráfico, com propriedades como posição, tamanho e cor.
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
	 * Construtor que inicializa as propriedades de posição, largura e altura do elemento.
	 *
	 * @param px      A coordenada X do elemento.
	 * @param py      A coordenada Y do elemento.
	 * @param largura A largura do elemento.
	 * @param altura  A altura do elemento.
	 */
	public Elemento(int px, int py, int largura, int altura) {
		this.px = px;
		this.py = py;
		this.largura = largura;
		this.altura = altura;
	}

	/**
	 * Método que permite realizar atualizações no elemento, sendo vazio por padrão.
	 */
	public void atualiza() {
	}

	/**
	 * Método que desenha o elemento no contexto gráfico especificado.
	 *
	 * @param g O contexto gráfico no qual desenhar o elemento.
	 */
	public void desenha(Graphics2D g) {
		g.setColor(cor);
		g.fillRect(px, py, largura, altura);
	}

	/**
	 * Obtém a largura do elemento.
	 *
	 * @return A largura do elemento.
	 */
	public int getLargura() {
		return largura;
	}

	/**
	 * Define a largura do elemento.
	 *
	 * @param largura A nova largura a ser definida para o elemento.
	 */
	public void setLargura(int largura) {
		this.largura = largura;
	}

	/**
	 * Obtém a altura do elemento.
	 *
	 * @return A altura do elemento.
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Define a altura do elemento.
	 *
	 * @param altura A nova altura a ser definida para o elemento.
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Obtém a coordenada X do elemento.
	 *
	 * @return A coordenada X do elemento.
	 */
	public int getPx() {
		return px;
	}

	/**
	 * Define a coordenada X do elemento.
	 *
	 * @param px A nova coordenada X a ser definida para o elemento.
	 */
	public void setPx(int px) {
		this.px = px;
	}

	/**
	 * Obtém a coordenada Y do elemento.
	 *
	 * @return A coordenada Y do elemento.
	 */
	public int getPy() {
		return py;
	}

	/**
	 * Define a coordenada Y do elemento.
	 *
	 * @param py A nova coordenada Y a ser definida para o elemento.
	 */
	public void setPy(int py) {
		this.py = py;
	}

	/**
	 * Obtém a velocidade do elemento.
	 *
	 * @return A velocidade do elemento.
	 */
	public int getVel() {
		return vel;
	}

	/**
	 * Define a velocidade do elemento.
	 *
	 * @param vel A nova velocidade a ser definida para o elemento.
	 */
	public void setVel(int vel) {
		this.vel = vel;
	}

	/**
	 * Verifica se o elemento está ativo.
	 *
	 * @return True se o elemento estiver ativo, false caso contrário.
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * Define o estado de ativação do elemento.
	 *
	 * @param ativo O novo estado de ativação a ser definido para o elemento.
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Obtém a cor do elemento.
	 *
	 * @return A cor do elemento.
	 */
	public Color getCor() {
		return cor;
	}

	/**
	 * Define a cor do elemento.
	 *
	 * @param cor A nova cor a ser definida para o elemento.
	 */
	public void setCor(Color cor) {
		this.cor = cor;
	}

	/**
	 * Incrementa a coordenada X do elemento por um valor especificado.
	 *
	 * @param x O valor a ser incrementado na coordenada X.
	 */
	public void incPx(int x) {
		px = px + x;
	}

	/**
	 * Incrementa a coordenada Y do elemento por um valor especificado.
	 *
	 * @param y O valor a ser incrementado na coordenada Y.
	 */
	public void incPy(int y) {
		py = py + y;
	}

	/**
	 * Retorna uma representação em string do elemento, exibindo suas coordenadas X e Y.
	 *
	 * @return Uma string representando o elemento.
	 */
	@Override
	public String toString() {
		return "Elemento [px=" + px + ", py=" + py + "]";
	}
}
