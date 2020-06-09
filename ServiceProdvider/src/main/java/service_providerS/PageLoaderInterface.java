package service_providerS;

import java.rmi.RemoteException;

public interface PageLoaderInterface  {
	

	public void loadPageOnServer(Page page) throws RemoteException;

}
