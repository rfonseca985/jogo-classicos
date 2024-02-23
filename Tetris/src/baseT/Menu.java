package baseT;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Classe que representa um menu de opções.
 */
public class Menu extends Texto {

	/**
	 * Índice da opção selecionada.
	 */
	private short idx;

	/**
	 * Rótulo do menu.
	 */
	private String rotulo;

	/**
	 * Array de opções do menu.
	 */
	private String[] opcoes;

	/**
	 * Indica se o menu está selecionado.
	 */
	private boolean selecionado;

	/**
	 * Construtor que define um rótulo padrão para o menu.
	 *
	 * @param rotulo Rótulo do menu
	 */
	public Menu(String rotulo) {
		super();

		this.rotulo = rotulo;
		setLargura(120);
		setAltura(20);
		setCor(Color.WHITE);
	}

	/**
	 * Adiciona opções ao menu.
	 *
	 * @param opcao Array de opções a serem adicionadas
	 */
	public void addOpcoes(String... opcao) {
		opcoes = opcao;
	}

	/**
	 * Sobrescreve o método de desenho para exibir o rótulo e a opção selecionada.
	 *
	 * @param g Objeto Graphics2D para desenho
	 */
	@Override
	public void desenha(Graphics2D g) {
		if (opcoes == null)
			return;

		g.setColor(getCor());
		super.desenha(g, String.format("%s: <%s>", getRotulo(), opcoes[idx]), getPx(), getPy() + getAltura());

		if (selecionado)
			g.drawLine(getPx(), getPy() + getAltura() + 5, getPx() + getLargura(), getPy() + getAltura() + 5);
	}

	/**
	 * Obtém o rótulo do menu.
	 *
	 * @return Rótulo do menu
	 */
	public String getRotulo() {
		return rotulo;
	}

	/**
	 * Define o rótulo do menu.
	 *
	 * @param rotulo Novo rótulo a ser atribuído
	 */
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	/**
	 * Verifica se o menu está selecionado.
	 *
	 * @return true se o menu está selecionado, false caso contrário
	 */
	public boolean isSelecionado() {
		return selecionado;
	}

	/**
	 * Define o estado de seleção do menu.
	 *
	 * @param selecionado Novo estado de seleção
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	/**
	 * Obtém o ID da opção selecionada.
	 *
	 * @return ID da opção selecionada
	 */
	public int getOpcaoId() {
		return idx;
	}

	/**
	 * Obtém o texto da opção selecionada.
	 *
	 * @return Texto da opção selecionada
	 */
	public String getOpcaoTexto() {
		return opcoes[idx];
	}

	/**
	 * Atualiza a opção do menu com base na direção (esquerda ou direita).
	 *
	 * @param esquerda true para mover para a opção à esquerda, false para a opção à direita
	 */
	public void setTrocaOpcao(boolean esquerda) {
		if (!isSelecionado() || !isAtivo())
			return;

		idx += esquerda ? -1 : 1;

		if (idx < 0)
			idx = (short) (opcoes.length - 1);
		else if (idx == opcoes.length)
			idx = 0;
	}

}
