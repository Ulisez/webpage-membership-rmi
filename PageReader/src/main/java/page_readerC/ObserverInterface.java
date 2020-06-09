package page_readerC;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverInterface extends Remote {
	
	void subscribe(ReaderInterface reader) throws RemoteException;
	void unsubscribe(ReaderInterface reader) throws RemoteException;
	
}
