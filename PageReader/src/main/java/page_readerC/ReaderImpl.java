package page_readerC;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import interfaces.ObservableInterface;
import interfaces.ReaderInterface;
import utils.Page;

public class ReaderImpl extends UnicastRemoteObject implements ReaderInterface,Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObservableInterface observable;

	public ReaderImpl(ObservableInterface observable) throws RemoteException{

		this.observable = observable;

	}

	public void run() {
		try {
			Random random = new Random();
			System.out.println("Il pageReader " +Thread.currentThread().getName() + " Si sta abbonando ");
			observable.subscribe(this);
			try {
				do {
					Thread.sleep(10000);
				}while(random.nextBoolean());
				observable.unsubscribe(this);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		System.out.println("Il pageReader " +Thread.currentThread().getName() + " Si e' disabbonato ");

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
		  page.getAllInfo().forEach(String -> System.out.println(Thread.currentThread().getName() + String));
		/*for(int i = 0; i<page.getSize();i++) {
			System.out.println(page.getInfo(i));*/
		}

	}


//}
