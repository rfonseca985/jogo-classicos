package baseP;

import java.awt.Graphics2D;

/**
 * A classe Menu representa um menu de opções com a capacidade de selecionar entre diferentes opções.
 * Herda características da classe Texto.
 */
public class Menu extends Texto {

	private short idx;
	private String rotulo;
	private String[] opcoes;
	private boolean selecionado;

	/**
	 * Construtor que recebe um rótulo para o menu.
	 *
	 * @param rotulo Rótulo do menu.
	 */
	public Menu(String rotulo) {
		super();

		this.rotulo = rotulo;
		setLargura(120);
		setAltura(20);
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
	 * Método de desenho do menu, exibindo o rótulo e a opção selecionada.
	 *
	 * @param g Objeto Graphics2D para desenhar o menu.
	 */
	@Override
	public void desenha(Graphics2D g) {
		if (opcoes == null)
			return;

		g.setColor(getCor());
		super.desenha(g, getRotulo() + ": <" + opcoes[idx] + ">", getPx(), getPy() + getAltura());

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
	 * @return true se o menu está selecionado, false caso contrário.
	 */
	public boolean isSelecionado() {
		return selecionado;
	}

	/**
	 * Define o estado de seleção do menu.
	 *
	 * @param selecionado Novo estado de seleção do menu.
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	/**
	 * Obtém o ID da opção atualmente selecionada no menu.
	 *
	 * @return ID da opção.
	 */
	public int getOpcaoId() {
		return idx;
	}

	/**
	 * Obtém o texto da opção atualmente selecionada no menu.
	 *
	 * @return Texto da opção.
	 */
	public String getOpcaoTexto() {
		return opcoes[idx];
	}

	/**
	 * Troca a opção selecionada no menu, avançando para a próxima ou voltando para a anterior.
	 *
	 * @param esquerda true para avançar para a opção anterior, false para avançar para a próxima.
	 */
	public void trocaOpcao(boolean esquerda) {
		if (!isSelecionado() || !isAtivo())
			return;

		idx += esquerda ? -1 : 1;

		if (idx < 0)
			idx = (short) (opcoes.length - 1);
		else if (idx == opcoes.length)
			idx = 0;
	}
}
