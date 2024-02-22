package baseS;

import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Uma classe que representa um elemento de texto, derivado da classe Elemento.
 */
public class Texto extends Elemento {

	private Font fonte;

	/**
	 * Construtor padrão que inicializa a fonte com "Tahoma", estilo Font.PLAIN e tamanho 16.
	 */
	public Texto() {
		fonte = new Font("Tahoma", Font.PLAIN, 16);
	}

	/**
	 * Construtor que permite especificar a fonte do texto.
	 *
	 * @param fonte A fonte a ser utilizada para o texto.
	 */
	public Texto(Font fonte) {
		this.fonte = fonte;
	}

	/**
	 * Desenha o texto utilizando a fonte atual na posição atual do elemento.
	 *
	 * @param g     O contexto gráfico no qual desenhar o texto.
	 * @param texto O texto a ser desenhado.
	 */
	public void desenha(Graphics2D g, String texto) {
		desenha(g, texto, getPx(), getPy());
	}

	/**
	 * Desenha o texto utilizando a fonte atual nas coordenadas especificadas.
	 *
	 * @param g  O contexto gráfico no qual desenhar o texto.
	 * @param texto O texto a ser desenhado.
	 * @param px A coordenada X para a posição do texto.
	 * @param py A coordenada Y para a posição do texto.
	 */
	public void desenha(Graphics2D g, String texto, int px, int py) {
		if (getCor() != null)
			g.setColor(getCor());

		g.setFont(fonte);
		g.drawString(texto, px, py);
	}

	/**
	 * Obtém a fonte atual do texto.
	 *
	 * @return A fonte atual do texto.
	 */
	public Font getFonte() {
		return fonte;
	}

	/**
	 * Define a fonte do texto.
	 *
	 * @param fonte A nova fonte a ser definida para o texto.
	 */
	public void setFonte(Font fonte) {
		this.fonte = fonte;
	}
}
