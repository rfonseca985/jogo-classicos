package baseT;

import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Classe que representa um elemento de texto.
 */
public class Texto extends Elemento {

	private Font fonte;

	/**
	 * Construtor padrão que utiliza uma fonte padrão com tamanho 16.
	 */
	public Texto() {
		this(16);
	}

	/**
	 * Construtor que permite especificar o tamanho da fonte.
	 *
	 * @param tamanho Tamanho da fonte
	 */
	public Texto(int tamanho) {
		this("Tahoma", tamanho);
	}

	/**
	 * Construtor que permite especificar o nome da fonte e o tamanho.
	 *
	 * @param nomeFonte Nome da fonte
	 * @param tamanho   Tamanho da fonte
	 */
	public Texto(String nomeFonte, int tamanho) {
		fonte = new Font(nomeFonte, Font.PLAIN, tamanho);
	}

	/**
	 * Desenha o texto na posição atual do elemento.
	 *
	 * @param g     Objeto Graphics2D para desenho
	 * @param texto Texto a ser desenhado
	 */
	public void desenha(Graphics2D g, String texto) {
		desenha(g, texto, getPx(), getPy());
	}

	/**
	 * Desenha o texto em uma posição específica.
	 *
	 * @param g  Objeto Graphics2D para desenho
	 * @param texto Texto a ser desenhado
	 * @param px   Posição X para desenho
	 * @param py   Posição Y para desenho
	 */
	public void desenha(Graphics2D g, String texto, int px, int py) {
		if (getCor() != null)
			g.setColor(getCor());

		g.setFont(fonte);
		g.drawString(texto, px, py);
	}

	/**
	 * Obtém a fonte atual do elemento de texto.
	 *
	 * @return Objeto Font representando a fonte do texto
	 */
	public Font getFonte() {
		return fonte;
	}

	/**
	 * Define a fonte do elemento de texto.
	 *
	 * @param fonte Nova fonte a ser atribuída ao texto
	 */
	public void setFonte(Font fonte) {
		this.fonte = fonte;
	}

}
