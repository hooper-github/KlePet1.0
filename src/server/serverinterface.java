package server;

import java.rmi.*;
import java.util.*;

import client.clientinterface;
 
public interface serverinterface extends Remote{	
	public boolean login (clientinterface a)throws RemoteException ;
	public boolean logout(clientinterface a) throws RemoteException;
	public void publish (String s)throws RemoteException ;
	public Vector<clientinterface> getConnected() throws RemoteException ;
}