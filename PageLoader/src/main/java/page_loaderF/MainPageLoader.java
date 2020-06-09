package page_loaderF;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.Scanner;

public class MainPageLoader {

	private static LinkedList<Thread> threads;
	private static boolean connected = false;
	private static int countThread = 1;
	private static int PORT = 3232;
	private static PageLoaderInterface pageloader;

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
		

		displayMenu();
		Scanner scan = new Scanner(System.in);
		chooseOption(scan);


	}



	private static void displayMenu() {
		System.out.println("                                                                                                              \r\n " 
				+"'##::::'##:'########:'##::: ##:'##::::'##:::::::'###::::::::'######:::'######::'########:'##:::::::'########::::'###::::\r\n" + 
				" ###::'###: ##.....:: ###:: ##: ##:::: ##::::::'## ##::::::'##... ##:'##... ##: ##.....:: ##:::::::... ##..::::'## ##:::\r\n" + 
				" ####'####: ##::::::: ####: ##: ##:::: ##:::::'##:. ##::::: ##:::..:: ##:::..:: ##::::::: ##:::::::::: ##:::::'##:. ##::\r\n" + 
				" ## ### ##: ######::: ## ## ##: ##:::: ##::::'##:::. ##::::. ######:: ##::::::: ######::: ##:::::::::: ##::::'##:::. ##:\r\n" + 
				" ##. #: ##: ##...:::: ##. ####: ##:::: ##:::: #########:::::..... ##: ##::::::: ##...:::: ##:::::::::: ##:::: #########:\r\n" + 
				" ##:.:: ##: ##::::::: ##:. ###: ##:::: ##:::: ##.... ##::::'##::: ##: ##::: ##: ##::::::: ##:::::::::: ##:::: ##.... ##:\r\n" + 
				" ##:::: ##: ########: ##::. ##:. #######::::: ##:::: ##::::. ######::. ######:: ########: ########:::: ##:::: ##:::: ##:\r\n" + 
				"..:::::..::........::..::::..:::.......::::::..:::::..::::::......::::......:::........::........:::::..:::::..:::::..::\r\n"
				+"..:::::..::........::..::::..:::.......::::::..:::::..::::::......::::......:::........::........:::::..:::::..:::::..::\r\n"+
				" --------------------------------------- 1 -  INSERIRE UN URL DA CUI SCARICARE UNA PAGINA  --------------------------------\n"+
				" --------------------------------------- 2 -  Exit Program                                 -------------------------------- \n");

	}

	private static boolean startConnection() {
		try {
           
			System.out.println("Iniziando la connessione con il server ");
			Registry registry = LocateRegistry.getRegistry(PORT);
			pageloader = (PageLoaderInterface) registry.lookup("loaderServer");

		} catch (NotBoundException | RemoteException e) {
			return false;
		}
		System.out.println("Connessione stabilita");
		return true;
	}

	private static void chooseOption(Scanner in) {
		boolean exit = false;
		int choose = in.nextInt();
		while(!exit) {
			switch (choose) {
			case 1:
				createNewThread(in);
				break;
			case 2:
				closeProgram();
				break;

			default:
				System.out.println(" Inserire un numero corretto ");
				break;
			}

		}

	}


	private static void closeProgram() {
            
    }


	private static void createNewThread(Scanner in) {
		String urlStr;
		URL url;
		System.out.println("\n Inserire URL della pagina ");
		urlStr = in.next();

		while(true) {

			if( isValidURL(urlStr)!=null ) {
				System.out.println(" Creando il thread"+" F"+countThread);
				url = isValidURL(urlStr);
			    new Thread(new PageLoader(url,pageloader),"F"+countThread).start();
				break;
			} else {
				System.out.print(" \n Inserire un URL valido " );
				urlStr = in.nextLine();
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
