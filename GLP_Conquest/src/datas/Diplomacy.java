package datas;

public class Diplomacy {
	
	private String status;

	public Diplomacy(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status.equals("War")) {
			this.status = "War";
		}
		if (status.equals("Neutral")) {
			this.status = "Neutral";
		}
		if (status.equals("Allied")) {
			this.status = "Allied";
		}
	}

}