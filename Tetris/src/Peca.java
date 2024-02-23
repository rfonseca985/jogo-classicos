import java.awt.Color;

/**
 * Classe que representa as peças do jogo.
 */
public class Peca {

	/**
	 * Cores disponíveis para as peças.
	 */
	public static Color[] Cores = { Color.GREEN, Color.ORANGE, Color.YELLOW, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.WHITE };

	/**
	 * Array tridimensional que define as configurações das peças.
	 */
	public static final int[][][] PECAS = {
			{
					{0, 1, 0},
					{0, 1, 0},
					{1, 1, 0}
			},
			{
					{0, 1, 0},
					{0, 1, 0},
					{0, 1, 1}
			},
			{
					{1, 1, 1},
					{0, 1, 0},
					{0, 0, 0}
			},
			{
					{1, 0, 0},
					{1, 1, 0},
					{0, 1, 0}
			},
			{
					{0, 0, 1},
					{0, 1, 1},
					{0, 1, 0}
			},
			{
					{1, 1},
					{1, 1}
			},
			{
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0}
			}
	};

}
