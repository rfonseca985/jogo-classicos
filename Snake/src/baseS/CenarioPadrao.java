package baseS;

import java.awt.Graphics2D;

/**
 * Uma classe abstrata que define o padrão para cenários no contexto gráfico, especificando métodos para carregar, descarregar, atualizar e desenhar o cenário.
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
	 * Construtor que inicializa as dimensões do cenário.
	 *
	 * @param largura A largura do cenário.
	 * @param altura  A altura do cenário.
	 */
	public CenarioPadrao(int largura, int altura) {
		this.altura = altura;
		this.largura = largura;
	}

	/**
	 * Método abstrato para carregar recursos ou inicializar o cenário.
	 */
	public abstract void carregar();

	/**
	 * Método abstrato para descarregar recursos ou liberar memória quando o cenário não for mais necessário.
	 */
	public abstract void descarregar();

	/**
	 * Método abstrato para atualizar a lógica do cenário.
	 */
	public abstract void atualizar();

	/**
	 * Método abstrato para desenhar o cenário no contexto gráfico fornecido.
	 *
	 * @param g O contexto gráfico no qual desenhar o cenário.
	 */
	public abstract void desenhar(Graphics2D g);

}
