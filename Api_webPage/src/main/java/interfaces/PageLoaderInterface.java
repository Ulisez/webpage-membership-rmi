package interfaces;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import utils.Page;

public interface PageLoaderInterface extends Remote{
	
	public void loadPageOnServer(Page page, URL urlweb, String client) throws RemoteException;

}
