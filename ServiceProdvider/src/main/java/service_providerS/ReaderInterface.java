package service_providerS;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReaderInterface extends Remote{
	 void update(Page page) throws RemoteException;
}
