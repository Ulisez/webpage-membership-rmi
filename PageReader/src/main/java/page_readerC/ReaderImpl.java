package page_readerC;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ObservableInterface;
import interfaces.ReaderInterface;
import utils.Page;

public class ReaderImpl extends UnicastRemoteObject implements ReaderInterface,Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObservableInterface observable;
	private int index = 0;

	public ReaderImpl(ObservableInterface observable) throws RemoteException{
		
		this.observable = observable;

	}

	public void run() {
		int random = (int) (Math.random()+10);
		try {
			System.out.println(" Il pageReader " +Thread.currentThread().getName() + " Si sta abbonando ");
			observable.subscribe(this);	
			while(random >=0 && random <6) {
				observable.unsubscribe(this);
				System.out.println("thread :"+Thread.currentThread().getName()+" si è arrestato");
				random = (int) (Math.random()*10);
			}
			try {
				Thread.currentThread().sleep(10000);		
			}
			catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println(" Il pageReader " +Thread.currentThread().getName() + " Si e' abbonato");

		} catch (RemoteException e) {
			System.err.println("Errore di connessione con l'oggetto observer");
			e.printStackTrace();			
		}
	}


	/* Ho pensato che inizialmente partono n thread e tutti si iscrivono e aspettano la update chiamata dal server,
	 * 
	 * una volta chiamato l'update il thread che esegue l'update decide randomicamente ogni 10 secondi se continuare o togliere l'iscrizione e terminare
	 */
	@Override
	public void update(Page page, URL url) throws RemoteException {
		
		System.out.println(Thread.currentThread().getName()+" sta per leggere dall'url :"+url.toString());
		if(page == null)
			System.out.println("pagina nulla");
		System.out.println(page.getInfo(index));
		index++;

	}


}
