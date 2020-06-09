package page_loaderF;

import java.net.URL;
import java.rmi.RemoteException;

public interface PageLoaderInterface  {
	

	public void loadPageOnServer(Page page, URL weburl, String client) throws RemoteException;

}
