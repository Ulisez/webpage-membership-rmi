package service_providerS;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ObserverImpl implements ObserverInterface{
	
	private ArrayList<ReaderInterface> observerList;
	
	public ObserverImpl() {
		observerList = new ArrayList<ReaderInterface>();
		
	}

	@Override
	public void subscribe(ReaderInterface reader) throws RemoteException {
		if(reader == null)
			System.err.println("L'oggetto indicato è nullo");
		else
			observerList.add(reader);
	}

	@Override
	public void unsubscribe(ReaderInterface reader) throws RemoteException {
		if(observerList.contains(reader))
			observerList.remove(reader);
		else
			System.err.println("La liste degli osservatori non contiene questo oggetto :"+reader.toString());
		
	}

}
