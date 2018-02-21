package datas;

public class Diplomacy {
	
	private int status;

	public Diplomacy(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		if (status==0) {
			this.status = status;
		}
		if (status==1) {
			this.status = status;
		}
		if (status==2) {
			this.status = status;
		}
	}

}