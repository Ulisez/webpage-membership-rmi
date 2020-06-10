package service_providerS;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import interfaces.ObservableInterface;
import interfaces.ReaderInterface;
import utils.Page;

public class ObservableImpl extends UnicastRemoteObject implements ObservableInterface {
	private LinkedList<ReaderInterface> observerList;
	private static final long serialVersionUID = 1L;
	private Page page = null;
	private URL url;
	
	public ObservableImpl(LinkedList<ReaderInterface> observerList) throws RemoteException{
		this.observerList = observerList;
		
	}
	
	public void setPage(Page page, URL url) {
		this.page = page;
		this.url = url;
		notifyObservers();
	}
	
	public Page getPage() {
		return page;
	}

	@Override
	public synchronized void subscribe(ReaderInterface reader) throws RemoteException {
		if(reader == null)
			System.err.println("L'oggetto indicato è nullo");
		else {
			observerList.add(reader);
			System.out.println("Il reader: " +reader + "e' stato aggiunto");
		}
			
	}

	@Override
	public synchronized void unsubscribe(ReaderInterface reader) throws RemoteException {
		if(observerList.contains(reader))
			observerList.remove(reader);
		else
			System.err.println("La liste degli osservatori non contiene questo oggetto :"+reader.toString());
		
	}
	
	public void notifyObservers() {
		if(observerList.isEmpty())
			System.out.println("Al momento non esiste alcun lettore abbonato");
		else
		{
			    observerList.forEach(ReaderInterface -> {
				try {
					ReaderInterface.update(page,url);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});
		}
	}

	public URL getUrl() {
		return url;
	}
	
	

}
