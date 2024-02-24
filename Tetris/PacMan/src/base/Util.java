package base;

/**
 * A classe Util fornece métodos utilitários para operações com elementos em um cenário de jogo.
 */
public class Util {

	/**
	 * Verifica se dois elementos colidem (se sobrepoem) em ambas as dimensões.
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
	 * Verifica se dois elementos colidem ao longo do eixo x (horizontal).
	 *
	 * @param a Elemento A.
	 * @param b Elemento B.
	 * @return true se os elementos colidem ao longo do eixo x, false caso contrário.
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
	 * Centraliza um elemento nas dimensões especificadas.
	 *
	 * @param el    Elemento a ser centralizado.
	 * @param larg  Largura do espaço de centralização.
	 * @param alt   Altura do espaço de centralização.
	 */
	public static void centraliza(Elemento el, int larg, int alt) {
		if (alt > 0)
			el.setPy(alt / 2 - el.getAltura() / 2);

		if (larg > 0)
			el.setPx(larg / 2 - el.getLargura() / 2);
	}

	/**
	 * Verifica se um elemento saiu dos limites especificados.
	 *
	 * @param e      Elemento a ser verificado.
	 * @param largura Largura do limite.
	 * @param altura  Altura do limite.
	 * @return true se o elemento saiu dos limites, false caso contrário.
	 */
	public static boolean saiu(Elemento e, int largura, int altura) {
		if (e.getPx() < 0 || e.getPx() + e.getLargura() > largura)
			return true;

		if (e.getPy() < 0 || e.getPy() + e.getAltura() > altura)
			return true;

		return false;
	}
}
