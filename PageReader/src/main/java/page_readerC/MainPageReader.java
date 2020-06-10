package page_readerC;

public class MainPageReader {
 
	public static void main(String[] args) {
	  
		createReaders();
	}

	private static void createReaders() {
	 for(int i=0; i<5; i++) {
		 System.out.println(" Creando il Reader R"+i);
		 new Thread(new ReaderImpl(),"R"+i);
	 }
		
	}
}
