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
	private static int PORT = 3232;
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
				" ----------------------- 1 -  INSERIRE UN URL DA CUI SCARICARE UNA PAGINA O PIU' (DIGITARE exit PER CONCLUDERE)---------------\n"+
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

	private static void chooseOption(ArrayList<URL> urls) {
		boolean exit = false;
		int choose = scan.nextInt();
		while(!exit) {
			switch (choose) {
			case 1:
				urlList(urls);
				createNewThread(urls);
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


	private static void urlList(ArrayList<URL> urls) {
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Digitare URL: \n");
		String url;
		while(true) {
			url =scan2.nextLine();
			if(url.equals("exit"))
					break;
			if( isValidURL(url)!=null ) {
				try {
					urls.add(new URL(url));
					System.out.println("URL addato correttamente");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.print(" \nInserire un URL valido: ");
				url = scan.nextLine();
			}

		}
	}


		private static void closeProgram() {

		}

		private static void createNewThread(ArrayList<URL> urlList) {
			for(int index = 0; index < urlList.size(); index++) {
				new PageLoader("F"+countThread,pageloader,urlList.get(index));
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


