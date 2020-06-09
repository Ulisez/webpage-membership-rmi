package service_providerS;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MainServiceProvider {

	private static boolean connected = false;
	private static int PORT;

	public static void main(String[] args) throws Exception  {
		System.out.println("Server attivo");
		Scanner scan = new Scanner(System.in);
		startService(scan);
	}

	private static void startService(Scanner scan) {
		if(!connected) {
			connected  = true;
			try {
                System.out.println(" Inserire la porta ");
                PORT = scan.nextInt();
				Registry registry = LocateRegistry.createRegistry(PORT);
				System.out.println(" Server running");
				PageLoaderImpl loader = new PageLoaderImpl();
				registry.rebind("loaderServer",loader);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}
