package base;

import java.awt.Font;
import java.awt.Graphics2D;

/**
 * A classe Texto representa um elemento de texto em um jogo, estendendo a classe Elemento.
 * Permite desenhar texto em uma posição específica na tela com uma fonte personalizada.
 */
public class Texto extends Elemento {

	/** A fonte utilizada para desenhar o texto. */
	private Font fonte;

	/**
	 * Construtor padrão da classe Texto.
	 * Inicializa a fonte padrão como "Tahoma" com estilo Font.PLAIN e tamanho 16.
	 */
	public Texto() {
		fonte = new Font("Tahoma", Font.PLAIN, 16);
	}

	/**
	 * Construtor parametrizado da classe Texto.
	 *
	 * @param fonte Fonte a ser utilizada para desenhar o texto.
	 */
	public Texto(Font fonte) {
		this.fonte = fonte;
	}

	/**
	 * Desenha o texto na posição atual do elemento com a fonte configurada.
	 *
	 * @param g     Contexto gráfico no qual o texto será desenhado.
	 * @param texto Texto a ser desenhado.
	 */
	public void desenha(Graphics2D g, String texto) {
		desenha(g, texto, getPx(), getPy());
	}

	/**
	 * Desenha o texto em uma posição específica na tela com a fonte configurada.
	 *
	 * @param g  Contexto gráfico no qual o texto será desenhado.
	 * @param texto Texto a ser desenhado.
	 * @param px   Posição X na tela onde o texto será desenhado.
	 * @param py   Posição Y na tela onde o texto será desenhado.
	 */
	public void desenha(Graphics2D g, String texto, int px, int py) {
		if (getCor() != null)
			g.setColor(getCor());

		g.setFont(fonte);
		g.drawString(texto, px, py);
	}

	/**
	 * Obtém a fonte utilizada para desenhar o texto.
	 *
	 * @return A fonte atual do elemento de texto.
	 */
	public Font getFonte() {
		return fonte;
	}

	/**
	 * Define a fonte a ser utilizada para desenhar o texto.
	 *
	 * @param fonte Nova fonte a ser configurada para o elemento de texto.
	 */
	public void setFonte(Font fonte) {
		this.fonte = fonte;
	}
}



