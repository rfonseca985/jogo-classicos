package baseS;

/**
 * Uma classe utilitária que fornece vários métodos estáticos para detecção de colisão e manipulação de elementos.
 */
public class Util {

	/**
	 * Verifica se dois elementos colidem entre si.
	 *
	 * @param a O primeiro elemento.
	 * @param b O segundo elemento.
	 * @return True se os elementos colidirem, false caso contrário.
	 */
	public static boolean colide(Elemento a, Elemento b) {
		if (!a.isAtivo() || !b.isAtivo())
			return false;

		final int plA = a.getPx() + a.getLargura();
		final int plB = b.getPx() + b.getLargura();
		final int paA = a.getPy() + a.getAltura();
		final int paB = b.getPy() + b.getAltura();

		if (plA > b.getPx() && a.getPx() < plB && paA > b.getPy() && a.getPy() < paB) {
			return true;
		}

		return false;
	}

	/**
	 * Verifica se dois elementos colidem ao longo do eixo X.
	 *
	 * @param a O primeiro elemento.
	 * @param b O segundo elemento.
	 * @return True se os elementos colidirem ao longo do eixo X, false caso contrário.
	 */
	public static boolean colideX(Elemento a, Elemento b) {
		if (!a.isAtivo() || !b.isAtivo())
			return false;

		if (a.getPx() + a.getLargura() >= b.getPx() && a.getPx() <= b.getPx() + b.getLargura()) {
			return true;
		}

		return false;
	}

	/**
	 * Centraliza o elemento especificado dentro de uma largura e altura dadas.
	 *
	 * @param el   O elemento a ser centralizado.
	 * @param larg A largura dentro da qual centralizar o elemento.
	 * @param alt  A altura dentro da qual centralizar o elemento.
	 */
	public static void centraliza(Elemento el, int larg, int alt) {
		if (alt > 0)
			el.setPy(alt / 2 - el.getAltura() / 2);

		if (larg > 0)
			el.setPx(larg / 2 - el.getLargura() / 2);
	}

	/**
	 * Verifica se o elemento especificado saiu da largura e altura fornecidas.
	 *
	 * @param e       O elemento a ser verificado.
	 * @param largura O limite de largura.
	 * @param altura  O limite de altura.
	 * @return True se o elemento saiu, false caso contrário.
	 */
	public static boolean saiu(Elemento e, int largura, int altura) {
		if (e.getPx() < 0 || e.getPx() + e.getLargura() > largura)
			return true;

		if (e.getPy() < 0 || e.getPy() + e.getAltura() > altura)
			return true;

		return false;
	}
}
