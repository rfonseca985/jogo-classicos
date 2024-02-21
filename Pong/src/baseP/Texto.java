package baseP;

import java.awt.Font;
import java.awt.Graphics2D;

/**
 * A classe Texto representa um elemento gráfico que exibe texto em um ambiente gráfico.
 * Herda características da classe Elemento.
 */
public class Texto extends Elemento {

	private Font fonte;

	/**
	 * Construtor padrão que inicializa a fonte do texto com uma fonte padrão.
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
	 * Desenha o texto na posição atual do objeto Texto.
	 *
	 * @param g     Objeto Graphics2D para desenhar o texto.
	 * @param texto Texto a ser desenhado.
	 */
	public void desenha(Graphics2D g, String texto) {
		desenha(g, texto, getPx(), getPy());
	}

	/**
	 * Desenha o texto em uma posição específica.
	 *
	 * @param g  Objeto Graphics2D para desenhar o texto.
	 * @param texto Texto a ser desenhado.
	 * @param px   Coordenada x da posição de desenho.
	 * @param py   Coordenada y da posição de desenho.
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
	 * @return Fonte do texto.
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
