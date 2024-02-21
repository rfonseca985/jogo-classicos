package baseP;

/**
 * A classe Util contém métodos utilitários para operações com elementos gráficos.
 */
public class Util {

	/**
	 * Verifica se dois elementos colidem entre si, levando em consideração suas posições, larguras e alturas.
	 *
	 * @param a Elemento A a ser verificado.
	 * @param b Elemento B a ser verificado.
	 * @return true se os elementos colidem, false caso contrário.
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
	 * Verifica se dois elementos colidem ao longo do eixo horizontal, considerando apenas as posições e larguras.
	 *
	 * @param a Elemento A a ser verificado.
	 * @param b Elemento B a ser verificado.
	 * @return true se os elementos colidem ao longo do eixo horizontal, false caso contrário.
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
	 * Centraliza um elemento em relação a uma largura e altura específicas.
	 *
	 * @param el   Elemento a ser centralizado.
	 * @param larg Largura de referência para centralização.
	 * @param alt  Altura de referência para centralização.
	 */
	public static void centraliza(Elemento el, int larg, int alt) {
		if (alt > 0)
			el.setPy(alt / 2 - el.getAltura() / 2);

		if (larg > 0)
			el.setPx(larg / 2 - el.getLargura() / 2);
	}

}
