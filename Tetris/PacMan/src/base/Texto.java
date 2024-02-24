package base;

import java.awt.*;

/**
 * A classe Texto representa um elemento de texto em um cenário de jogo.
 * Pode ser utilizado para exibir mensagens ou informações na tela.
 */
public class Texto extends Elemento {

	private Font fonte;

	/**
	 * Construtor padrão que define uma fonte padrão para o texto.
	 */
	public Texto() {
		fonte = new Font("Tahoma", Font.PLAIN, 16);
	}

	/**
	 * Construtor que permite especificar a fonte do texto.
	 *
	 * @param fonte Fonte a ser utilizada para o texto.
	 */
	public Texto(Font fonte) {
		this.fonte = fonte;
	}

	/**
	 * Desenha o texto na posição atual do elemento.
	 *
	 * @param g     Contexto gráfico no qual o texto será desenhado.
	 * @param texto Texto a ser exibido.
	 */
	public void desenha(Graphics2D g, String texto) {
		desenha(g, texto, getPx(), getPy());
	}

	/**
	 * Desenha o texto em uma posição específica.
	 *
	 * @param g  Contexto gráfico no qual o texto será desenhado.
	 * @param texto Texto a ser exibido.
	 * @param px   Coordenada x de onde o texto será desenhado.
	 * @param py   Coordenada y de onde o texto será desenhado.
	 */
	public void desenha(Graphics2D g, String texto, int px, int py) {
		if (getCor() != null)
			g.setColor(getCor());

		g.setFont(fonte);
		g.drawString(texto, px, py);
	}

	/**
	 * Obtém a fonte utilizada para o texto.
	 *
	 * @return A fonte do texto.
	 */
	public Font getFonte() {
		return fonte;
	}

	/**
	 * Define a fonte a ser utilizada para o texto.
	 *
	 * @param fonte Nova fonte do texto.
	 */
	public void setFonte(Font fonte) {
		this.fonte = fonte;
	}
}
