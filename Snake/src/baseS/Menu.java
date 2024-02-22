package baseS;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Uma classe que representa um menu de opções derivado da classe Texto.
 */
public class Menu extends Texto {

	/**
	 * Índice da opção selecionada no menu.
	 */
	private short idx;

	/**
	 * Rótulo do menu.
	 */
	private String rotulo;

	/**
	 * Array de opções disponíveis no menu.
	 */
	private String[] opcoes;

	/**
	 * Indica se o menu está selecionado.
	 */
	private boolean selecionado;

	/**
	 * Construtor que inicializa o menu com um rótulo padrão e dimensões.
	 *
	 * @param rotulo O rótulo do menu.
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
	 * @param opcao As opções a serem adicionadas ao menu.
	 */
	public void addOpcoes(String... opcao) {
		opcoes = opcao;
	}

	/**
	 * {@inheritDoc}
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
	 * @return O rótulo do menu.
	 */
	public String getRotulo() {
		return rotulo;
	}

	/**
	 * Define o rótulo do menu.
	 *
	 * @param rotulo O novo rótulo a ser definido para o menu.
	 */
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	/**
	 * Verifica se o menu está selecionado.
	 *
	 * @return True se o menu estiver selecionado, false caso contrário.
	 */
	public boolean isSelecionado() {
		return selecionado;
	}

	/**
	 * Define se o menu está selecionado.
	 *
	 * @param selecionado O novo estado de seleção do menu.
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	/**
	 * Obtém o ID da opção selecionada no menu.
	 *
	 * @return O ID da opção selecionada.
	 */
	public int getOpcaoId() {
		return idx;
	}

	/**
	 * Obtém o texto da opção selecionada no menu.
	 *
	 * @return O texto da opção selecionada.
	 */
	public String getOpcaoTexto() {
		return opcoes[idx];
	}

	/**
	 * Define a troca de opção no menu, para a esquerda ou direita.
	 *
	 * @param esquerda True se a troca for para a esquerda, false se for para a direita.
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
