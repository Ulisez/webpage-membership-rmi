package page_readerC;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.ObservableInterface;

public class MainPageReader {
	
	private static ObservableInterface observable;
	private static Registry readerRegister;
 
	public static void main(String[] args) throws RemoteException {
		readerRegister = LocateRegistry.createRegistry(8080);	//porta a caso
		createReaders();
	}

	private static void createReaders() {
		Registry reg;
		
		try {
			reg = LocateRegistry.getRegistry(3232);
			observable = (ObservableInterface) reg.lookup("ObservableServer");
			 for(int i=0; i<20; i++) {
				 System.out.println(" Creando il Reader R"+i);
				 Thread t = new Thread(new ReaderImpl(observable, readerRegister),"R"+i);
				 t.start();
			 }
		} catch (RemoteException  | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
	}
}
