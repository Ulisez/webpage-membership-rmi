package service_providerS;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PageLoaderImpl extends UnicastRemoteObject implements PageLoaderInterface {

	private static final long serialVersionUID = 1L;

	protected PageLoaderImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadPageOnServer(Page page) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
