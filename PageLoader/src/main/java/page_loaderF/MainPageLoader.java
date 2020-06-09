package page_loaderF;

import java.util.Scanner;

public class MainPageLoader {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Digitare l'url della pagina da scaricare :\n");
		String weburl = sc.nextLine();
		PageLoader loader = new PageLoader(weburl);
		Page page = loader.getPage();
		
	}

}
