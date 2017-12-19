package client;

import java.rmi.*;
import java.util.Vector;
 
public interface clientinterface extends Remote{	
	public void tell (String name)throws RemoteException ;
	public String getName()throws RemoteException;
	public void updateUsers(Vector<clientinterface> v)throws RemoteException;
}