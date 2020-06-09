package page_loaderF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class PageLoader {

	private String weburl;
	private Page page;

	public PageLoader(String weburl) {
		this.weburl = weburl;
		download();
	}

	public Page getPage() {
		return page;
	}



	private void download() {
		try {
			URL url = new URL(weburl);
			page = new Page(new ArrayList<String>());
			BufferedReader readr = 	new BufferedReader(new InputStreamReader(url.openStream()));  
			String line; 
			int max = 0;
			System.out.println("Download Iniziato");
			while ((line = readr.readLine()) != null && max < 200) { 
				page.addInfo(line);
				max++;
			} 

			readr.close(); 
			System.out.println("Download riuscito correttamente."); 

		}
		catch(IOException io) {
			io.printStackTrace();
		}
	}


}
