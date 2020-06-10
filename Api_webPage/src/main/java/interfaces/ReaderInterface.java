package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import utils.Page;

public interface ReaderInterface extends Remote{
	
	void update(Page page) throws RemoteException;
}
