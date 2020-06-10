package service_providerS;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import interfaces.PageLoaderInterface;

public class MainServiceProvider {

	private static boolean connected = false;
	private static int PORT;

	public static void main(String[] args) throws Exception  {
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
				PageLoaderInterface loader = new PageLoaderImpl();
				registry.rebind("loaderServer",loader);
	            System.out.println("                                     SSSSSSSSSSSSSSS EEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR   VVVVVVVV           VVVVVVVVEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR        \r\n" + 
	            		"                                              SS:::::::::::::::SE::::::::::::::::::::ER::::::::::::::::R  V::::::V           V::::::VE::::::::::::::::::::ER::::::::::::::::R       \r\n" + 
	            		"                                             S:::::SSSSSS::::::SE::::::::::::::::::::ER::::::RRRRRR:::::R V::::::V           V::::::VE::::::::::::::::::::ER::::::RRRRRR:::::R      \r\n" + 
	            		"                                             S:::::S     SSSSSSSEE::::::EEEEEEEEE::::ERR:::::R     R:::::RV::::::V           V::::::VEE::::::EEEEEEEEE::::ERR:::::R     R:::::R     \r\n" + 
	            		"                                             S:::::S              E:::::E       EEEEEE  R::::R     R:::::R V:::::V           V:::::V   E:::::E       EEEEEE  R::::R     R:::::R     \r\n" + 
	            		"                                             S:::::S              E:::::E               R::::R     R:::::R  V:::::V         V:::::V    E:::::E               R::::R     R:::::R     \r\n" + 
	            		"                                              S::::SSSS           E::::::EEEEEEEEEE     R::::RRRRRR:::::R    V:::::V       V:::::V     E::::::EEEEEEEEEE     R::::RRRRRR:::::R      \r\n" + 
	            		"                                               SS::::::SSSSS      E:::::::::::::::E     R:::::::::::::RR      V:::::V     V:::::V      E:::::::::::::::E     R:::::::::::::RR       \r\n" + 
	            		"                                                 SSS::::::::SS    E:::::::::::::::E     R::::RRRRRR:::::R      V:::::V   V:::::V       E:::::::::::::::E     R::::RRRRRR:::::R      \r\n" + 
	            		"                                                    SSSSSS::::S   E::::::EEEEEEEEEE     R::::R     R:::::R      V:::::V V:::::V        E::::::EEEEEEEEEE     R::::R     R:::::R     \r\n" + 
	            		"                                                         S:::::S  E:::::E               R::::R     R:::::R       V:::::V:::::V         E:::::E               R::::R     R:::::R     \r\n" + 
	            		"                                                         S:::::S  E:::::E       EEEEEE  R::::R     R:::::R        V:::::::::V          E:::::E       EEEEEE  R::::R     R:::::R     \r\n" + 
	            		"                                             SSSSSSS     S:::::SEE::::::EEEEEEEE:::::ERR:::::R     R:::::R         V:::::::V         EE::::::EEEEEEEE:::::ERR:::::R     R:::::R     \r\n" + 
	            		"                                             S::::::SSSSSS:::::SE::::::::::::::::::::ER::::::R     R:::::R          V:::::V          E::::::::::::::::::::ER::::::R     R:::::R     \r\n" + 
	            		"                                             S:::::::::::::::SS E::::::::::::::::::::ER::::::R     R:::::R           V:::V           E::::::::::::::::::::ER::::::R     R:::::R     \r\n" + 
	            		"                                              SSSSSSSSSSSSSSS   EEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR            VVV            EEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR     \r\n" + 
	            		"                                                 RRRRRRRRRRRRRRRRR   EEEEEEEEEEEEEEEEEEEEEE               AAA               DDDDDDDDDDDDD       YYYYYYY       YYYYYYY               \r\n" + 
	            		"                                                 R::::::::::::::::R  E::::::::::::::::::::E              A:::A              D::::::::::::DDD    Y:::::Y       Y:::::Y               \r\n" + 
	            		"                                                 R::::::RRRRRR:::::R E::::::::::::::::::::E             A:::::A             D:::::::::::::::DD  Y:::::Y       Y:::::Y               \r\n" + 
	            		"                                                 RR:::::R     R:::::REE::::::EEEEEEEEE::::E            A:::::::A            DDD:::::DDDDD:::::D Y::::::Y     Y::::::Y               \r\n" + 
	            		"                                                   R::::R     R:::::R  E:::::E       EEEEEE           A:::::::::A             D:::::D    D:::::DYYY:::::Y   Y:::::YYY               \r\n" + 
	            		"                                                   R::::R     R:::::R  E:::::E                       A:::::A:::::A            D:::::D     D:::::D  Y:::::Y Y:::::Y                  \r\n" + 
	            		"                                                   R::::RRRRRR:::::R   E::::::EEEEEEEEEE            A:::::A A:::::A           D:::::D     D:::::D   Y:::::Y:::::Y                   \r\n" + 
	            		"                                                   R:::::::::::::RR    E:::::::::::::::E           A:::::A   A:::::A          D:::::D     D:::::D    Y:::::::::Y                    \r\n" + 
	            		"                                                   R::::RRRRRR:::::R   E:::::::::::::::E          A:::::A     A:::::A         D:::::D     D:::::D     Y:::::::Y                     \r\n" + 
	            		"                                                   R::::R     R:::::R  E::::::EEEEEEEEEE         A:::::AAAAAAAAA:::::A        D:::::D     D:::::D      Y:::::Y                      \r\n" + 
	            		"                                                   R::::R     R:::::R  E:::::E                  A:::::::::::::::::::::A       D:::::D     D:::::D      Y:::::Y                      \r\n" + 
	            		"                                                   R::::R     R:::::R  E:::::E       EEEEEE    A:::::AAAAAAAAAAAAA:::::A      D:::::D    D:::::D       Y:::::Y                      \r\n" + 
	            		"                                                 RR:::::R     R:::::REE::::::EEEEEEEE:::::E   A:::::A             A:::::A   DDD:::::DDDDD:::::D        Y:::::Y                      \r\n" + 
	            		"                                                 R::::::R     R:::::RE::::::::::::::::::::E  A:::::A               A:::::A  D:::::::::::::::DD      YYYY:::::YYYY                   \r\n" + 
	            		"                                                 R::::::R     R:::::RE::::::::::::::::::::E A:::::A                 A:::::A D::::::::::::DDD        Y:::::::::::Y                   \r\n" + 
	            		"                                                 RRRRRRRR     RRRRRRREEEEEEEEEEEEEEEEEEEEEEAAAAAAA                   AAAAAAADDDDDDDDDDDDD           YYYYYYYYYYYYY");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}
