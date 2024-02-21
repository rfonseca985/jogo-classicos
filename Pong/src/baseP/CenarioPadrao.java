package baseP;

import java.awt.Graphics2D;

/**
 * A classe abstrata CenarioPadrao define um padrão para cenários em um ambiente gráfico.
 * Cada cenário possui uma largura e altura específicas.
 */
public abstract class CenarioPadrao {

	/**
	 * Altura do cenário.
	 */
	protected int altura;

	/**
	 * Largura do cenário.
	 */
	protected int largura;

	/**
	 * Construtor que inicializa a largura e altura do cenário.
	 *
	 * @param largura Largura do cenário.
	 * @param altura  Altura do cenário.
	 */
	public CenarioPadrao(int largura, int altura) {
		this.altura = altura;
		this.largura = largura;
	}

	/**
	 * Método abstrato para carregar recursos do cenário.
	 */
	public abstract void carregar();

	/**
	 * Método abstrato para descarregar recursos do cenário.
	 */
	public abstract void descarregar();

	/**
	 * Método abstrato para atualizar o estado do cenário.
	 */
	public abstract void atualizar();

	/**
	 * Método abstrato para desenhar o cenário utilizando um contexto gráfico.
	 *
	 * @param g Objeto Graphics2D para desenhar o cenário.
	 */
	public abstract void desenhar(Graphics2D g);

}
