package base;

import java.awt.*;

/**
 * A classe abstrata CenarioPadrao define um padrão para cenários em um jogo,
 * fornecendo métodos básicos para carregar, descarregar, atualizar e desenhar.
 * Implementações concretas devem estender esta classe e fornecer
 * implementações específicas para cada método.
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
	 * Construtor que inicializa a altura e largura do cenário.
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
	 * Implementações devem fornecer lógica específica de carregamento.
	 */
	public abstract void carregar();

	/**
	 * Método abstrato para descarregar recursos do cenário.
	 * Implementações devem fornecer lógica específica de descarregamento.
	 */
	public abstract void descarregar();

	/**
	 * Método abstrato para atualizar o estado do cenário.
	 * Implementações devem fornecer lógica específica de atualização.
	 */
	public abstract void atualizar();

	/**
	 * Método abstrato para desenhar o cenário.
	 *
	 * @param g Contexto gráfico no qual o cenário será desenhado.
	 */
	public abstract void desenhar(Graphics2D g);

}
