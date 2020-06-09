package service_providerS;

import java.net.URL;
import java.rmi.RemoteException;

public interface PageLoaderInterface {
	
	public void loadPageOnServer(Page page, URL urlweb, String client) throws RemoteException;

}
