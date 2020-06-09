package service_providerS;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PageRequestInterface extends Remote{
   public void subscribe() throws RemoteException;
   public void unSuscribe() throws RemoteException;
}
