package mapGenerator;

public class SquareNumberGenerator {
	/*
	 * 0 = Water
	 * 1 = Land
	 * 2 = Desert
	 * 3 = Forest
	 * 4 = Mont
	 * 5 = Mine
	 * 6 = Farm
	 * 7 = OilWell
	 * 8 = NuclearPlant
	 * 9 = City
	 */
	private RandomNumberGenerator rng;
	
	public SquareNumberGenerator() {
		setRng(new RandomNumberGenerator());
	}
	
	public int generate(int[] probabilities){
		int dice = getRng().generate(19, 0);
		int stats = probabilities[0];
		int square = 0;
		while (dice>=stats && square<probabilities.length-1) {
			square++;
			stats+=probabilities[square];
		}
		return square;
	}

	public RandomNumberGenerator getRng() {
		return rng;
	}

	public void setRng(RandomNumberGenerator rng) {
		this.rng = rng;
	}
}
