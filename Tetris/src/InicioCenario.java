import java.awt.Graphics2D;

import baseT.CenarioPadrao;
import baseT.Menu;
import baseT.Util;

/**
 * Classe que representa o cenário de início do jogo.
 */
public class InicioCenario extends CenarioPadrao {

	/**
	 * Menu do jogo para seleção de níveis.
	 */
	private Menu menuJogo;

	/**
	 * Construtor que define a largura e altura do cenário de início.
	 *
	 * @param largura Largura do cenário
	 * @param altura  Altura do cenário
	 */
	public InicioCenario(int largura, int altura) {
		super(largura, altura);
	}

	/**
	 * Carrega os elementos necessários para o cenário de início, incluindo o menu de seleção de níveis.
	 */
	@Override
	public void carregar() {
		menuJogo = new Menu("Nível");
		menuJogo.addOpcoes("1", "2", "3", "4", "5", "6", "7", "8", "9");

		Util.centraliza(menuJogo, largura, altura);
		menuJogo.setPy(menuJogo.getPy() + menuJogo.getAltura());
		menuJogo.setAtivo(true);
		menuJogo.setSelecionado(true);
	}

	/**
	 * Descarrega o cenário de início, definindo o nível selecionado no jogo.
	 */
	@Override
	public void descarregar() {
		JogoTetris.nivel = menuJogo.getOpcaoId() + 1;
	}

	/**
	 * Atualiza o cenário de início, permitindo a troca de opções do menu.
	 */
	@Override
	public void atualizar() {
		if (JogoTetris.controleTecla[JogoTetris.Tecla.ESQUERDA.ordinal()] || JogoTetris.controleTecla[JogoTetris.Tecla.DIREITA.ordinal()]) {
			menuJogo.setTrocaOpcao(JogoTetris.controleTecla[JogoTetris.Tecla.ESQUERDA.ordinal()]);
		}
		JogoTetris.liberaTeclas();
	}

	/**
	 * Desenha o cenário de início, exibindo o menu de seleção de níveis.
	 *
	 * @param g Objeto Graphics2D para desenho
	 */
	@Override
	public void desenhar(Graphics2D g) {
		menuJogo.desenha(g);
	}

}
