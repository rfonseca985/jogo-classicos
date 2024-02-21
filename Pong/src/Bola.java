import java.awt.Color;
import java.awt.Graphics2D;

import baseP.Elemento;

/**
 * A classe Bola representa uma bola em um ambiente gráfico, que herda características da classe Elemento.
 * Possui propriedades como direção, velocidade, e métodos para movimentação e inversão de direção.
 */
public class Bola extends Elemento {

	/**
	 * Velocidade inicial da bola.
	 */
	public static final int VEL_INICIAL = 3;

	private int dirX = -1;

	private int dirY = -1;

	private float velX;

	private float velY;

	/**
	 * Construtor padrão da classe Bola, inicializando a velocidade, largura, altura e cor.
	 */
	public Bola() {
		velX = velY = VEL_INICIAL;
		setAltura(10);
		setLargura(10);
		setCor(Color.WHITE);
	}

	/**
	 * Desenha a bola utilizando um objeto Graphics2D.
	 *
	 * @param g Objeto Graphics2D para desenhar a bola.
	 */
	@Override
	public void desenha(Graphics2D g) {
		if (!isAtivo())
			return;

		g.setColor(getCor());
		g.fillOval(getPx(), getPy(), getLargura(), getAltura());
	}

	/**
	 * Obtém a velocidade no eixo horizontal da bola.
	 *
	 * @return Velocidade no eixo horizontal.
	 */
	public float getVelX() {
		return velX;
	}

	/**
	 * Obtém a velocidade no eixo vertical da bola.
	 *
	 * @return Velocidade no eixo vertical.
	 */
	public float getVelY() {
		return velY;
	}

	/**
	 * Define a direção da bola no eixo horizontal.
	 *
	 * @param dirX Direção no eixo horizontal (-1 ou 1).
	 */
	public void setDirX(int dirX) {
		this.dirX = dirX;
	}

	/**
	 * Define a direção da bola no eixo vertical.
	 *
	 * @param dirY Direção no eixo vertical (-1 ou 1).
	 */
	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	/**
	 * Incrementa as velocidades horizontal e vertical da bola.
	 *
	 * @param vx Velocidade no eixo horizontal.
	 * @param vy Velocidade no eixo vertical.
	 */
	public void incVel(float vx, float vy) {
		velX = vx;
		velY = vy;
	}

	/**
	 * Define a velocidade da bola para um valor específico.
	 *
	 * @param vel Velocidade desejada.
	 */
	@Override
	public void setVel(int vel) {
		velX = velY = vel;
	}

	/**
	 * Obtém a velocidade da bola.
	 *
	 * @return Velocidade da bola.
	 */
	@Override
	public int getVel() {
		return (int) velX;
	}

	/**
	 * Incrementa a posição da bola ao longo do eixo horizontal baseado na velocidade e direção.
	 */
	public void incPx() {
		incPx((int) velX * dirX);
	}

	/**
	 * Incrementa a posição da bola ao longo do eixo vertical baseado na velocidade e direção.
	 */
	public void incPy() {
		incPy((int) velY * dirY);
	}

	/**
	 * Inverte a direção da bola no eixo horizontal.
	 */
	public void inverteX() {
		dirX *= -1;
	}

	/**
	 * Inverte a direção da bola no eixo vertical.
	 */
	public void inverteY() {
		dirY *= -1;
	}

}
