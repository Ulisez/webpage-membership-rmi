package page_readerC;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.ObservableInterface;

public class MainPageReader {
	
	private static ObservableInterface observable;
 
	public static void main(String[] args) throws RemoteException {
		createReaders();
	}

	private static void createReaders() {
		Registry reg;
		
		try {
			reg = LocateRegistry.getRegistry(1099);
			observable = (ObservableInterface) reg.lookup("ObservableServer");
			 for(int i=0; i<20; i++) {
				 System.out.println(" Creando il Reader R"+i);
				 Thread t = new Thread(new ReaderImpl(observable),"R"+i);
				 t.start();
				 t.join();
			 }
		} catch (RemoteException  | NotBoundException | InterruptedException e) {
			e.printStackTrace();
		} 	
		
	}
}
