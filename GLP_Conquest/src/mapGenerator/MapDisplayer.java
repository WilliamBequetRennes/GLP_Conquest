package mapGenerator;

import exceptions.InvalidMapSizeNumberException;
import map.Map;

public class MapDisplayer {
	public static void main(String[] args) {
		try {
			MapGenerator mapGenerator = new MapGenerator();
			Map map = mapGenerator.generate(0);
			System.out.println(map.toString());
		} catch (InvalidMapSizeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
