package client;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import javax.swing.DefaultListModel;


public class clientchat extends UnicastRemoteObject implements clientinterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ChatUI ui;
	
	public clientchat (String n) throws RemoteException {
		name = n;
	}
	
	public void tell(String st) throws RemoteException{
		System.out.println(st);
		ui.writeMsg(st);
	}
	public String getName() throws RemoteException{
		return name;
	}
	
	public void setGUI(ChatUI t){ 
		ui = t ; 
	} 
	
	public ChatUI getGUI(){ 
		return ui;
	}
	
	public void updateUsers(Vector<clientinterface> v){
	      DefaultListModel<String> listModel = new DefaultListModel<String>();
	      if(v!=null) for (int i = 0;i<v.size();i++){
	    	  try{  String tmp=((clientinterface)v.get(i)).getName();
	    	  		listModel.addElement(tmp);
	    	  }catch(Exception e){e.printStackTrace();}
	      }
	      ui.lst.setModel(listModel);
	 }
}
