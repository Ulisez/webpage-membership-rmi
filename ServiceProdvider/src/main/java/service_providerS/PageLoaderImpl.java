package service_providerS;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.PageLoaderInterface;
import utils.Page;

public class PageLoaderImpl extends UnicastRemoteObject implements PageLoaderInterface {

	private static final long serialVersionUID = 1L;

	protected PageLoaderImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void loadPageOnServer(Page page, URL urlweb, String client) throws RemoteException {
		System.out.println("Il client " +client + " Sta caricando la pagina ");
		MainServiceProvider.observable.setPage(page,urlweb);
		System.out.println("Il client " +client + " ha modificato lo stato della pagina");
	}

}
