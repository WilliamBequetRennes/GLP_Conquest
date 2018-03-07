package map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class MapSaver {
	
	private static final int MIN_MAPS = 1;
	private static final int MAX_MAPS = 1000;
	private static final int TIMESTAMP_POS = 3;
	private String saveFile;
	private String temporarilySaveFile;
	private Timestamp timestamp;
	
	public MapSaver() {
		setSaveFile("savedMaps.txt");
		setTemporarilySaveFile("temporarilySavedMaps.txt");
		setTimestamp(new Timestamp(System.currentTimeMillis()));
	}
	
	public void saveMap(Map map) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(getSaveFile()));
			int number = MIN_MAPS;
			String current = reader.readLine();
			while (current!=null && number<MAX_MAPS) {
				number++;
				current = reader.readLine();
			}
			reader.close();
			if(number < MAX_MAPS) {
				map.setNumber(number);
				BufferedWriter writer = new BufferedWriter(new FileWriter(getSaveFile(), true));
				writer.write(number+"#");
				writer.write(map.getDimensions()+"#");
				writer.write(""+map.getSquares()[0][0].getType());
				for(int i = 0; i < map.getDimensions(); i++) {
					for (int j = 0; j < map.getDimensions(); j++) {
						if(i!=0 || j!=0) {
							writer.write(","+map.getSquares()[i][j].getType());
						}
					}
				}
				writer.write("#"+getTimestamp().getTime());
				writer.newLine();
				writer.close();
			}
			else {
				number = MIN_MAPS;
				long time = 0;
				int olderLine = MIN_MAPS;
				long olderTime = getTimestamp().getTime();
				reader = new BufferedReader(new FileReader(getSaveFile()));
				current = reader.readLine();
				while (current!=null) {
					number++;
					String[] splittedCurrent = current.split("#");
					time = Long.valueOf(splittedCurrent[TIMESTAMP_POS]).longValue();
					if(time<olderTime) {
						olderTime = time;
						olderLine = number;
					}
					current = reader.readLine();
				}
				reader.close();
				BufferedWriter writer = new BufferedWriter(new FileWriter(getTemporarilySaveFile(), false));
				reader = new BufferedReader(new FileReader(getSaveFile()));
				number = MIN_MAPS;
				current = reader.readLine();
				while (current!=null) {
					number++;
					current = reader.readLine();
					if(number!=olderLine) {
						writer.write(current);
					}
					else {
						writer.write(number+"#");
						writer.write(map.getDimensions()+"#");
						writer.write(""+map.getSquares()[0][0].getType());
						for(int i = 0; i < map.getDimensions(); i++) {
							for (int j = 1; j < map.getDimensions(); j++) {
								writer.write(","+map.getSquares()[i][j].getType());
							}
						}
						writer.write("#"+getTimestamp().getTime());
						map.setNumber(number);
					}
					writer.newLine();
				}
				reader.close();
				writer.close();
				File oldFile = new File(getSaveFile());
				oldFile.delete();
				File newFile = new File(getTemporarilySaveFile());
				newFile.renameTo(oldFile);
			}
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTemporarilySaveFile() {
		return temporarilySaveFile;
	}

	public void setTemporarilySaveFile(String temporarilySaveFile) {
		this.temporarilySaveFile = temporarilySaveFile;
	}
}