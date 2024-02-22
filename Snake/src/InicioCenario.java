import java.awt.Graphics2D;

import baseS.CenarioPadrao;
import baseS.Menu;
import baseS.Util;

/**
 * Classe que representa o cenário inicial do jogo.
 */
public class InicioCenario extends CenarioPadrao {

	/**
	 * Menu de seleção de fase.
	 */
	private Menu menuJogo;

	/**
	 * Menu de seleção de velocidade inicial.
	 */
	private Menu menuVelInicial;

	/**
	 * Construtor que inicializa as dimensões do cenário inicial.
	 *
	 * @param largura A largura do cenário inicial.
	 * @param altura  A altura do cenário inicial.
	 */
	public InicioCenario(int largura, int altura) {
		super(largura, altura);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void carregar() {

		menuJogo = new Menu("Fase");

		String[] opcoes = new String[Nivel.niveis.length + 1];

		for (int i = 0; i < opcoes.length; i++) {
			opcoes[i] = "Nível " + i;
		}

		opcoes[opcoes.length - 1] = "Do Russo";

		menuJogo.addOpcoes(opcoes);

		menuVelInicial = new Menu("Vel.");
		menuVelInicial.addOpcoes("Normal", "Rápido", "Lento");

		Util.centraliza(menuJogo, largura, altura);
		Util.centraliza(menuVelInicial, largura, altura);

		menuVelInicial.setPy(menuJogo.getPy() + menuJogo.getAltura());

		menuJogo.setAtivo(true);
		menuJogo.setSelecionado(true);
		menuVelInicial.setAtivo(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void descarregar() {
		JogoSnake.nivel = menuJogo.getOpcaoId();

		switch (menuVelInicial.getOpcaoId()) {
			case 0:
				JogoSnake.velocidade = 4;
				break;
			case 1:
				JogoSnake.velocidade = 8;
				break;
			case 2:
				JogoSnake.velocidade = 2;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizar() {
		if (JogoSnake.controleTecla[JogoSnake.Tecla.CIMA.ordinal()] || JogoSnake.controleTecla[JogoSnake.Tecla.BAIXO.ordinal()]) {
			if (menuJogo.isSelecionado()) {
				menuJogo.setSelecionado(false);
				menuVelInicial.setSelecionado(true);

			} else {
				menuJogo.setSelecionado(true);
				menuVelInicial.setSelecionado(false);
			}

		} else if (JogoSnake.controleTecla[JogoSnake.Tecla.ESQUERDA.ordinal()] || JogoSnake.controleTecla[JogoSnake.Tecla.DIREITA.ordinal()]) {
			menuJogo.setTrocaOpcao(JogoSnake.controleTecla[JogoSnake.Tecla.ESQUERDA.ordinal()]);
			menuVelInicial.setTrocaOpcao(JogoSnake.controleTecla[JogoSnake.Tecla.ESQUERDA.ordinal()]);

		}

		JogoSnake.liberaTeclas();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void desenhar(Graphics2D g) {
		menuJogo.desenha(g);
		menuVelInicial.desenha(g);
	}

}
