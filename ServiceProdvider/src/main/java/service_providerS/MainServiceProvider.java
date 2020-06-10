package service_providerS;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Scanner;
import interfaces.PageLoaderInterface;
import interfaces.ReaderInterface;

public class MainServiceProvider {

	private static boolean connected = false;
	private static int PORT = 1099;
	protected static ObservableImpl observable;
	private static LinkedList<ReaderInterface> observerList; 
	private static PageLoaderInterface loader;
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception  {
	      startService();
	      String stop = in.nextLine();
	      
		while (!stop.equalsIgnoreCase("stop")) {
	           stop = in.nextLine(); 
	        }
	         
	      stopServer();
	}

	private static void stopServer() {
		 try {
			Registry registry = LocateRegistry.getRegistry();
			registry.unbind("loaderServer");
			registry.unbind("ObservableServer");
			UnicastRemoteObject.unexportObject(loader,false);
			UnicastRemoteObject.unexportObject(observable,false);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		 
		 new Thread() {
			 public void run() {
				 System.out.println("Spegnendo il server .......");
				 try {
					 sleep(30000);
				 }catch(InterruptedException e) {}
				 System.exit(0);
			 }
		 }.start();
		
	}

	private static void startService() {
		observerList = new LinkedList<ReaderInterface>();
		if(!connected) {
			connected  = true;
			try {
				Registry registry = LocateRegistry.createRegistry(PORT);
				 loader = new PageLoaderImpl();
				registry.rebind("loaderServer",loader);
				observable = new ObservableImpl(observerList);
				registry.rebind("ObservableServer", observable);
	            System.out.println("        _______. _______ .______     ____    ____  _______ .______         \r\n" + 
	            		"                           /       ||   ____||   _  \\    \\   \\  /   / |   ____||   _  \\        \r\n" + 
	            		"                          |   (----`|  |__   |  |_)  |    \\   \\/   /  |  |__   |  |_)  |         \r\n" + 
	            		"                           \\   \\    |   __|  |      /      \\      /   |   __|  |      /        \r\n" + 
	            		"                       .----)   |   |  |____ |  |\\  \\----.  \\    /    |  |____ |  |\\  \\----.   \r\n" + 
	            		"                       |_______/    |_______|| _| `._____|   \\__/     |_______|| _| `._____|      \r\n" + 
	            		"                         .______       _______     ___       _______  ____    ____                  \r\n" + 
	            		"                         |   _  \\     |   ____|   /   \\     |       \\ \\   \\  /   /            \r\n" + 
	            		"                         |  |_)  |    |  |__     /  ^  \\    |  .--.  | \\   \\/   /               \r\n" + 
	            		"                         |      /     |   __|   /  /_\\  \\   |  |  |  |  \\_    _/                \r\n" + 
	            		"                         |  |\\  \\----.|  |____ /  _____  \\  |  '--'  |    |  |                  \r\n" + 
	            		"                         | _| `._____||_______/__/     \\__\\ |_______/     |__|                   \r\n" + 
	            		"  -------------------------------------------------------------------------------------------------\r\n" + 
	            		"  ---------------------------------INSERIRE stop(Stop) per spegnere il server ---------------------\r\n ");
	            
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}
