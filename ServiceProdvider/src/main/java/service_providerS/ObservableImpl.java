package service_providerS;

import java.rmi.RemoteException;
import java.util.ArrayList;

import interfaces.ObservableInterface;
import interfaces.ReaderInterface;
import utils.Page;

public class ObservableImpl implements ObservableInterface {
	
	private ArrayList<ReaderInterface> observerList;
	private Page page = null;
	
	public ObservableImpl() {
		observerList = new ArrayList<ReaderInterface>();
		
	}
	
	public void setPage(Page page) {
		this.page = page;
	}
	
	public Page getPage() {
		return page;
	}

	@Override
	public void subscribe(ReaderInterface reader) throws RemoteException {
		if(reader == null)
			System.err.println("L'oggetto indicato è nullo");
		else {
			System.out.println("Il reader: " +reader + "e' stato aggiunto");
			observerList.add(reader);
		}
			
	}

	@Override
	public void unsubscribe(ReaderInterface reader) throws RemoteException {
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
					ReaderInterface.update(getPage());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}

}
