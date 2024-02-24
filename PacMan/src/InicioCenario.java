import base.CenarioPadrao;
import base.Menu;
import base.Util;

import java.awt.*;


/**
 * Uma classe que representa o cenário inicial do jogo com um menu para escolher a dificuldade do jogo.
 * Estende a classe de cenário base (CenarioPadrao) e implementa funcionalidades relacionadas ao menu inicial.
 */
public class InicioCenario extends CenarioPadrao {

	/**
	 * Construtor para a classe InicioCenario.
	 *
	 * @param largura A largura da janela do jogo.
	 * @param altura A altura da janela do jogo.
	 */
	public InicioCenario(int largura, int altura) {
		super(largura, altura);
	}

	/** O objeto de menu responsável por lidar com as opções do menu. */
	private Menu menuJogo;

	/**
	 * Carrega o cenário inicial do jogo inicializando o menu, adicionando opções e definindo-o como ativo e selecionado.
	 */
	@Override
	public void carregar() {

		menuJogo = new Menu("Fome");
		menuJogo.addOpcoes("Sem", "Pouca", "Muita");

		Util.centraliza(menuJogo, largura, altura);

		menuJogo.setPy(menuJogo.getPy() + menuJogo.getAltura());

		menuJogo.setAtivo(true);
		menuJogo.setSelecionado(true);
	}

	/**
	 * Descarrega o cenário inicial do jogo e define o nível do jogo com base na opção selecionada no menu.
	 */
	@Override
	public void descarregar() {
		JogoPacMan.nivel = menuJogo.getOpcaoId();
	}

	/**
	 * Atualiza o cenário do jogo, lidando com a entrada do teclado para navegar pelas opções do menu.
	 */
	@Override
	public void atualizar() {
		if (JogoPacMan.controleTecla[JogoPacMan.Tecla.CIMA.ordinal()] || JogoPacMan.controleTecla[JogoPacMan.Tecla.BAIXO.ordinal()]) {
			// Nenhuma ação necessária para as teclas de seta para cima e para baixo
		} else if (JogoPacMan.controleTecla[JogoPacMan.Tecla.ESQUERDA.ordinal()] || JogoPacMan.controleTecla[JogoPacMan.Tecla.DIREITA.ordinal()]) {
			// Ajusta a opção do menu com base nas teclas de seta para a esquerda e para a direita
			menuJogo.setTrocaOpcao(JogoPacMan.controleTecla[JogoPacMan.Tecla.ESQUERDA.ordinal()]);

		}
// Libera todas as teclas pressionadas
		JogoPacMan.liberaTeclas();

	}

	/**
	 * Desenha o cenário do jogo invocando o método de desenho no objeto de menu.
	 *
	 * @param g O objeto Graphics2D usado para desenhar.
	 */
	@Override
	public void desenhar(Graphics2D g) {
		menuJogo.desenha(g);

	}

}
