package page_loaderF;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.Scanner;

import interfaces.PageLoaderInterface;

public class MainPageLoader {

	private static LinkedList<Thread> threads;
	private static boolean connected = false;
	private static int countThread = 1;
	private static int PORT = 3232;
	private static PageLoaderInterface pageloader = null;
	static Scanner scan;

	public static void main(String[] args) {

		/*if(args[0] == null ) {
			System.out.println("Errore, e' necessario inserire la porta all'avvio del programma ");
		}*/
		//else
		//PORT = Integer.parseInt(args[0]);     
		if(!startConnection()) {
			System.out.println("Non e' stato possibile connettersi correttamente al server");
			System.exit(0);
		}
		scan = new Scanner(System.in);
		
		for(int i=0; i<3; i++) {
			createNewThread();
		}
		displayMenu();
		chooseOption();


	}



	private static void displayMenu() {
		System.out.println("                                                                                                              \r\n " 
				+"##::::'##:'########:'##::: ##:'##::::'##:::::::'###::::::::'######:::'######::'########:'##:::::::'########::::'###::::\r\n" + 
				" ###::'###: ##.....:: ###:: ##: ##:::: ##::::::'## ##::::::'##... ##:'##... ##: ##.....:: ##:::::::... ##..::::'## ##:::\r\n" + 
				" ####'####: ##::::::: ####: ##: ##:::: ##:::::'##:. ##::::: ##:::..:: ##:::..:: ##::::::: ##:::::::::: ##:::::'##:. ##::\r\n" + 
				" ## ### ##: ######::: ## ## ##: ##:::: ##::::'##:::. ##::::. ######:: ##::::::: ######::: ##:::::::::: ##::::'##:::. ##:\r\n" + 
				" ##. #: ##: ##...:::: ##. ####: ##:::: ##:::: #########:::::..... ##: ##::::::: ##...:::: ##:::::::::: ##:::: #########:\r\n" + 
				" ##:.:: ##: ##::::::: ##:. ###: ##:::: ##:::: ##.... ##::::'##::: ##: ##::: ##: ##::::::: ##:::::::::: ##:::: ##.... ##:\r\n" + 
				" ##:::: ##: ########: ##::. ##:. #######::::: ##:::: ##::::. ######::. ######:: ########: ########:::: ##:::: ##:::: ##:\r\n" + 
				"..:::::..::........::..::::..:::.......::::::..:::::..::::::......::::......:::........::........:::::..:::::..:::::..::\r\n"
				+"..:::::..::........::..::::..:::.......::::::..:::::..::::::......::::......:::........::........:::::..:::::..:::::..::\r\n"+
				" --------------------------------------- 1 -  INSERIRE UN URL DA CUI SCARICARE UNA PAGINA  --------------------------------\n"+
				" --------------------------------------- 2 -  Exit PROGRAM                                -------------------------------- \n");

	}

	private static boolean startConnection() {
		try {

			System.out.println("****************************  INIZIANDO LA CONNESSIONE CON IL SERVER ************************************* ");
			Registry registry = LocateRegistry.getRegistry(PORT);
			pageloader = (PageLoaderInterface)registry.lookup("loaderServer");

		} catch (NotBoundException | RemoteException e) {
			return false;
		}
		System.out.println("********************************** CONNESSIONE STABILITA CORRETTAMENTE* ******************************************");
		return true;
	}

	private static void chooseOption() {
		boolean exit = false;
		int choose = scan.nextInt();
		while(!exit) {
			switch (choose) {
			case 1:
				createNewThread();
				break;
			case 2:
				closeProgram();
				break;

			default:{
				displayMenu();
				System.out.print("\nInserire un numero corretto ");
				choose = scan.nextInt();
				break;
			}
			}

		}

	}


	private static void closeProgram() {

	}


	private static void createNewThread() {
		String urlStr;
		URL url;
		System.out.println(" Inserire URL della pagina: ");
		urlStr = scan.next();

		while(true) {

			if( isValidURL(urlStr)!=null ) {
				System.out.println(" Creando il thread"+" F"+countThread);
				url = isValidURL(urlStr);
				new Thread(new PageLoader(url,pageloader),"F"+countThread).start();
				break;
			} else {
				System.out.print(" \nInserire un URL valido: ");
				urlStr = scan.nextLine();
			}
		}

	}

	private static URL isValidURL(String urlStr) {
		try {
			if(urlStr.isEmpty()) {
				return null;
			}else {
				URL url = new URL(urlStr);
				return url;
			}
		}catch(MalformedURLException e ){
			return null;
		}

	}

}
