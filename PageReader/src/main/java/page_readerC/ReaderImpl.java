package page_readerC;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReaderImpl extends Thread implements ReaderInterface{
	
	private ObserverInterface observe;
	
	public ReaderImpl() {
		start();
	}
	
	public void run() {
		
		try {
			Registry reg = LocateRegistry.createRegistry(8080);	//porta a caso
			reg.rebind("clientObserver", this);
			observe = (ObserverInterface) Naming.lookup("ObserverServer");		//Se non funziona ricorda che come argomento va un URL/nome oggetto
			observe.subscribe(this);	
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	
	/* Ho pensato che inizialmente partono n thread e tutti si iscrivono e aspettano la update chiamata dal server,
	 * 
	 * una volta chiamato l'update il thread che esegue l'update decide randomicamente ogni 10 secondi se continuare o togliere l'iscrizione e terminare
	 */
	@Override
	public void update(Page page) throws RemoteException {
		int random = (int) (Math.random()+10);
		int index = 0;
		if(random >=0 && random <6) {
			System.out.println(page.getInfo(index));
			index++;
		}
		else {
			observe.unsubscribe(this);
			Thread.currentThread().interrupt(); // controllate come terminare un thread
			System.out.println("thread :"+Thread.currentThread().getId()+" si è arrestato");
		}
		try {
			Thread.currentThread().sleep(10000);		//il try catch l'ho messo alla fine così in caso che faccia l'unsuscribe viene catturata la flag e arrestato
		}
		catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}

}
