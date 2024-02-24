import base.Elemento;

import java.awt.*;

/**
 * A classe Pizza representa um elemento do tipo pizza em um cenário de jogo.
 * Pode assumir diferentes modos, como PRESO, ATIVO, INATIVO, FANTASMA, CACANDO e FUGINDO.
 */
public class Pizza extends Elemento {

	/**
	 * Enumeração que representa os modos possíveis para a pizza.
	 */
	public enum Modo {
		PRESO, ATIVO, INATIVO, FANTASMA, CACANDO, FUGINDO;
	}

	private int dx, dy;
	private Modo modo = Modo.PRESO;
	private JogoCenario.Direcao direcao = JogoCenario.Direcao.OESTE;

	/**
	 * Construtor que recebe a posição, largura e altura da pizza.
	 *
	 * @param px      Posição X da pizza.
	 * @param py      Posição Y da pizza.
	 * @param largura Largura da pizza.
	 * @param altura  Altura da pizza.
	 */
	public Pizza(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
	}

	/**
	 * Atualiza a posição da pizza com base na direção e velocidade.
	 */
	@Override
	public void atualiza() {
		incPx(getVel() * getDx());
		incPy(getVel() * getDy());
	}

	/**
	 * Desenha a pizza no contexto gráfico especificado.
	 *
	 * @param g Contexto gráfico no qual a pizza será desenhada.
	 */
	@Override
	public void desenha(Graphics2D g) {
		if (modo == Modo.FUGINDO)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(getCor());

		if (modo == Modo.FANTASMA)
			g.drawOval(getPx(), getPy() + JogoCenario.ESPACO_TOPO, getLargura(), getAltura());
		else
			g.fillOval(getPx(), getPy() + JogoCenario.ESPACO_TOPO, getLargura(), getAltura());
	}

	/**
	 * Obtém a componente X da direção da pizza.
	 *
	 * @return Componente X da direção.
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * Define a componente X da direção da pizza.
	 *
	 * @param dx Nova componente X da direção.
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * Obtém a componente Y da direção da pizza.
	 *
	 * @return Componente Y da direção.
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * Define a componente Y da direção da pizza.
	 *
	 * @param dy Nova componente Y da direção.
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * Obtém a direção da pizza.
	 *
	 * @return Direção da pizza.
	 */
	public JogoCenario.Direcao getDirecao() {
		return direcao;
	}

	/**
	 * Define a direção da pizza.
	 *
	 * @param direcao Nova direção da pizza.
	 */
	public void setDirecao(JogoCenario.Direcao direcao) {
		this.direcao = direcao;
	}

	/**
	 * Obtém o modo atual da pizza.
	 *
	 * @return Modo atual da pizza.
	 */
	public Modo getModo() {
		return modo;
	}

	/**
	 * Define o modo da pizza.
	 *
	 * @param modo Novo modo da pizza.
	 */
	public void setModo(Modo modo) {
		this.modo = modo;
	}
}
