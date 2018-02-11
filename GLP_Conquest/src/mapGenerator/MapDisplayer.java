package mapGenerator;

import exceptions.InvalidSizeMapNumberException;
import map.Map;

public class MapDisplayer {
	public static void main(String[] args) {
		try {
			MapGenerator mapGenerator = new MapGenerator();
			Map map = mapGenerator.generate(0);
			System.out.println(map.toString());
		} catch (InvalidSizeMapNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
