import java.awt.Graphics2D;

import baseP.CenarioPadrao;
import baseP.Menu;
import baseP.Util;

/**
 * A classe InicioCenario representa o cenário inicial do jogo, estendendo a classe CenarioPadrao.
 * Responsável por exibir as opções de modo de jogo e velocidade.
 */
public class InicioCenario extends CenarioPadrao {

	private Bola bola;

	private Menu menuModo;

	private Menu menuVeloc;

	/**
	 * Construtor da classe InicioCenario, recebe a largura e altura do cenário.
	 *
	 * @param largura Largura do cenário.
	 * @param altura  Altura do cenário.
	 */
	public InicioCenario(int largura, int altura) {
		super(largura, altura);
	}

	/**
	 * Inicializa os elementos do cenário no carregamento.
	 */
	@Override
	public void carregar() {
		bola = new Bola();

		menuModo = new Menu("Modo");
		menuModo.addOpcoes("Normal", "Em casa");

		menuVeloc = new Menu("Vel.");
		menuVeloc.addOpcoes("Normal", "Rápido", "Lento");

		Util.centraliza(bola, largura, altura);
		Util.centraliza(menuModo, largura, altura);
		Util.centraliza(menuVeloc, largura, altura);

		menuModo.setPy(menuModo.getPy() + 20);
		menuVeloc.setPy(menuModo.getPy() + menuModo.getAltura());

		bola.setAtivo(true);
		menuModo.setSelecionado(true);
		menuModo.setAtivo(true);
		menuVeloc.setAtivo(true);
	}

	/**
	 * Descarrega recursos do cenário antes de mudar para outro cenário.
	 */
	@Override
	public void descarregar() {
		JogoPong.velocidade = bola.getVel();
		JogoPong.modoNormal = menuModo.getOpcaoId() == 0;
	}

	/**
	 * Atualiza a lógica do cenário.
	 */
	@Override
	public void atualizar() {
		if (JogoPong.controleTecla[JogoPong.Tecla.CIMA.ordinal()] || JogoPong.controleTecla[JogoPong.Tecla.BAIXO.ordinal()]) {
			if (menuModo.isSelecionado()) {
				menuModo.setSelecionado(false);
				menuVeloc.setSelecionado(true);

			} else {
				menuModo.setSelecionado(true);
				menuVeloc.setSelecionado(false);
			}

		} else if (JogoPong.controleTecla[JogoPong.Tecla.ESQUERDA.ordinal()] || JogoPong.controleTecla[JogoPong.Tecla.DIREITA.ordinal()]) {

			boolean esquerda = JogoPong.controleTecla[JogoPong.Tecla.ESQUERDA.ordinal()];
			menuModo.trocaOpcao(esquerda);
			menuVeloc.trocaOpcao(esquerda);

			if (menuVeloc.getOpcaoId() == 0) {
				bola.setVel(Bola.VEL_INICIAL);

			} else if (menuVeloc.getOpcaoId() == 1) {
				bola.setVel(Bola.VEL_INICIAL * 2);

			} else {
				bola.setVel(Bola.VEL_INICIAL / 2);
			}

		}

		JogoPong.liberaTeclas();

		// Controle da bola
		bola.incPx();
		bola.incPy();

		if (Util.colide(menuModo, bola) || Util.colide(menuVeloc, bola)) {
			bola.inverteX();
			bola.inverteY();
		}

		if (bola.getPx() < 0 || bola.getPx() + bola.getLargura() > largura) {
			// Colisao nas laterais da tela
			bola.inverteX();

		} else if (bola.getPy() <= 0 || bola.getPy() + bola.getAltura() >= altura) {
			// Colisao no topo ou base da tela
			bola.inverteY();
		}

		if (bola.getPy() < 0)
			bola.setPy(0);
		else if (bola.getPy() + bola.getAltura() > altura)
			bola.setPy(altura - bola.getAltura());
	}

	/**
	 * Desenha os elementos gráficos do cenário.
	 *
	 * @param g Objeto Graphics2D para desenhar no cenário.
	 */
	@Override
	public void desenhar(Graphics2D g) {
		bola.desenha(g);
		menuModo.desenha(g);
		menuVeloc.desenha(g);
	}
}
