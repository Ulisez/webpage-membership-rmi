package page_loaderF;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import interfaces.PageLoaderInterface;

public class MainPageLoader {

	private static LinkedList<Thread> threads;
	private static boolean connected = false;
	private static int countThread = 1;
	private static int PORT = 1099;
	private static PageLoaderInterface pageloader = null;
	static Scanner scan;

	public static void main(String[] args) {
		ArrayList<URL> urls = new ArrayList<URL>();

		if(!startConnection()) {
			System.out.println("Non e' stato possibile connettersi correttamente al server");
			System.exit(0);
		}
		scan = new Scanner(System.in);
		displayMenu();
		chooseOption(urls);
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
				" ----------------------- 1  INSERIRE UN URL DA CUI SCARICARE UNA PAGINA O PIU' (DIGITARE exit PER CONCLUDERE)---------------\n"+
				" ---------------------------------------2 INSERIRE IL DIGITO 2 PER CHIUDERE IL PROGRAM--------------------------------------\n");
	}

	private static boolean startConnection() {
		try {

			System.out.println("\n\n *****************************  INIZIANDO LA CONNESSIONE CON IL SERVER **************************************\n\n");
			Registry registry = LocateRegistry.getRegistry(PORT);
			pageloader = (PageLoaderInterface)registry.lookup("loaderServer");

		} catch (NotBoundException | RemoteException e) {
			return false;
		}
		System.out.println("\n\n********************************** CONNESSIONE STABILITA CORRETTAMENTE* *****************************************\n\n");
		return true;
	}

	private static void chooseOption(ArrayList<URL> urls) {
		boolean exit = false;
		int choose = scan.nextInt();
		while(!exit) {
			switch (choose) {
			case 1:{
				urlList(urls);
				createThreads(urls);
				break;
			}
			case 2: {closeProgram();
			exit = true;
			break; 
			}

			default:{
				displayMenu();
				System.out.print(" Inserire un numero corretto ");
				choose = scan.nextInt();
				break;
			}

			}
		}

	}


	private static void urlList(ArrayList<URL> urls) {
		Scanner scan2 = new Scanner(System.in);
		String url;
		while(true) {
			System.out.print("Inserire un URL: ");
			url =scan2.nextLine();
			if(url.equalsIgnoreCase("exit")) {
				break;
			}
			if( isValidURL(url)!= null) {
				try {
					urls.add(new URL(url));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			} else {
				System.out.print("\n Inserire un URL valido: ");
				url = scan.nextLine();
			}

		}
	}


	public static void closeProgram() {
       System.out.println("Chiudendo il programma........");
		if(PageLoader.countLoader > 0) {
    	   System.out.println("Spiacenti non è possibile chiudere il programma, attendere la finalità dei thread");
       }else {
    	   System.out.println("PROGRAMMA CHIUSO");
    	   System.exit(0);
       }
    	   
	}

	private static void createThreads(ArrayList<URL> urlList) {
		for(int index = 0; index < urlList.size(); index++) {
			countThread++;
			new PageLoader("PageLoader-"+countThread,pageloader,urlList.get(index));
		}

	}

	private static URL isValidURL(String urlStr) {
		try {
			if(urlStr == null) {
				return null;
			}else {
				URL url = new URL(urlStr);
				return url;
			}
		}catch(MalformedURLException e ){
			e.printStackTrace();
			return null;
		}
	}
}


