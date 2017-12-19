package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import client.clientinterface;

public class ChatServer extends UnicastRemoteObject implements serverinterface {
	private static final long serialVersionUID = 1L;
	private Vector<clientinterface> v;
	StartServer ss;
	public ChatServer(StartServer ss) throws RemoteException {
		super();
		this.ss = ss; 
		v = new Vector<clientinterface>();
	}
	public boolean login(clientinterface a) throws RemoteException {
		ss.tx.append("\n"+a.getName()+ " got connected...");
		a.tell("You have Connected successfully...");
		publish(a.getName()+ " has connected..");
		v.add(a);
		updateListAll();
		return true;
	}
	
	public boolean logout(clientinterface a) throws RemoteException {
		ss.tx.append("\n"+a.getName()+ " leave the chat room...");
		a.tell("You have logout successfully...");
		v.remove(a);
		publish(a.getName()+ " leaved the chat room ..");
		updateListAll();
		return true;
	}

	public void publish(String s) throws RemoteException {
		try {
			for(clientinterface tmp: v) {
				tmp.tell(s);
			}
		} catch(Exception e) {
			ss.tx.append("\n"+e.getMessage());
		}
			
	}
	
	public void updateListAll() {
		try {
			for(clientinterface tmp: v) {
				tmp.updateUsers(v);
			}
		} catch(Exception e) {
			ss.tx.append("\n"+e.getMessage());
		}
			
	}
	
	public Vector<clientinterface> getConnected() throws RemoteException {
		return v;
	}

}
