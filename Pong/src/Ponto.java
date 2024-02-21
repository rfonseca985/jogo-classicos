import java.awt.Font;
import java.awt.Graphics2D;

import baseP.Texto;

/**
 * A classe Ponto representa um elemento gráfico que exibe um ponto em um ambiente gráfico,
 * herda características da classe Texto.
 */
public class Ponto extends Texto {

	/**
	 * Tamanho da fonte utilizado para exibir o ponto.
	 */
	public static final int TAMANHO_FONTE = 60;

	/**
	 * Fonte específica utilizada para exibir o ponto.
	 */
	public static final Font fonte = new Font("Consolas", Font.PLAIN, TAMANHO_FONTE);

	private short ponto;

	/**
	 * Construtor da classe Ponto, que configura a fonte para exibição.
	 */
	public Ponto() {
		super.setFonte(fonte);
	}

	/**
	 * Obtém a quantidade de pontos.
	 *
	 * @return Quantidade de pontos.
	 */
	public short getPonto() {
		return ponto;
	}

	/**
	 * Define a quantidade de pontos.
	 *
	 * @param ponto Nova quantidade de pontos.
	 */
	public void setPonto(short ponto) {
		this.ponto = ponto;
	}

	/**
	 * Adiciona um ponto à contagem atual.
	 */
	public void add() {
		ponto++;
	}

	/**
	 * Desenha a quantidade de pontos utilizando um objeto Graphics2D.
	 *
	 * @param g Objeto Graphics2D para desenhar a quantidade de pontos.
	 */
	@Override
	public void desenha(Graphics2D g) {
		super.desenha(g, Short.toString(ponto), getPx(), getPy());
	}

}
