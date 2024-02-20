package base;

/**
 * A classe Util contém métodos utilitários para operações com elementos em um jogo.
 */
public class Util {

	/**
	 * Verifica se dois elementos colidem (se estão sobrepostos) em ambas as dimensões (X e Y).
	 *
	 * @param a Elemento A.
	 * @param b Elemento B.
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
	 * Verifica se dois elementos colidem na dimensão X (horizontal).
	 *
	 * @param a Elemento A.
	 * @param b Elemento B.
	 * @return true se os elementos colidem na dimensão X, false caso contrário.
	 */
	public static boolean colideX(Elemento a, Elemento b) {
		if (!a.isAtivo() || !b.isAtivo())
			return false;

		if (a.getPx() + a.getLargura() >= b.getPx() && a.getPx() <= b.getPx() + b.getLargura()) {
			return true;
		}

		return false;
	}
}
