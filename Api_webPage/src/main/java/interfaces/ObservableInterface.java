package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObservableInterface extends Remote {
	
	void subscribe(ReaderInterface reader) throws RemoteException;
	void unsubscribe(ReaderInterface reader) throws RemoteException;
	
}
