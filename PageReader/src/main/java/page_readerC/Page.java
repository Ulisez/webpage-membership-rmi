package page_readerC;

import java.io.Serializable;
import java.util.ArrayList;

public class Page implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<String> info;
	
	public Page(ArrayList<String> info) {
		this.info = info;
	}
	
	public String getInfo(int index) {
		return info.get(index);
	}
	
	public ArrayList<String> getAllInfo(){
		return info;
	}
	
	public void addInfo(String stringInfo) {
		info.add(stringInfo);
	}

}
