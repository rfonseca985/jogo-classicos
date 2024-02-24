package base;

import java.awt.*;

/**
 * A classe Menu representa um elemento de menu em um cenário de jogo.
 * Pode ser utilizado para criar menus interativos com opções selecionáveis.
 */
public class Menu extends Texto {

	private short idx;
	private String rotulo;
	private String[] opcoes;
	private boolean selecionado;

	/**
	 * Construtor que recebe o rótulo do menu.
	 *
	 * @param rotulo Rótulo do menu.
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
	 * @param opcao Array de strings representando as opções do menu.
	 */
	public void addOpcoes(String... opcao) {
		opcoes = opcao;
	}

	/**
	 * Sobrescreve o método de desenho para exibir o menu.
	 *
	 * @param g Contexto gráfico no qual o menu será desenhado.
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
	 * @return Rótulo do menu.
	 */
	public String getRotulo() {
		return rotulo;
	}

	/**
	 * Define o rótulo do menu.
	 *
	 * @param rotulo Novo rótulo do menu.
	 */
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	/**
	 * Verifica se o menu está selecionado.
	 *
	 * @return true se o menu estiver selecionado, false caso contrário.
	 */
	public boolean isSelecionado() {
		return selecionado;
	}

	/**
	 * Define se o menu está selecionado.
	 *
	 * @param selecionado true se o menu estiver selecionado, false caso contrário.
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;

	}

	/**
	 * Obtém o ID da opção selecionada no menu.
	 *
	 * @return ID da opção selecionada.
	 */
	public int getOpcaoId() {
		return idx;
	}

	/**
	 * Obtém o texto da opção selecionada no menu.
	 *
	 * @return Texto da opção selecionada.
	 */
	public String getOpcaoTexto() {
		return opcoes[idx];
	}

	/**
	 * Altera a opção selecionada no menu.
	 *
	 * @param esquerda true para selecionar a opção à esquerda, false para selecionar a opção à direita.
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
