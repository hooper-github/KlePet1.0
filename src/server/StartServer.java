package server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.*;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StartServer {
	serverinterface b;
	Registry rmiRegistry;
	JFrame frame;
	JPanel main;
	JButton bt;
	JTextArea tx;
	
	public static void main(String[] args) {		
		StartServer a = new StartServer();
		a.dispGUI();
	}
	
    public void start(){
    	try {
    		b = new ChatServer(this);
    		rmiRegistry = LocateRegistry.createRegistry(1099);
    	    
    	} catch(ExportException e) {
    		try {
    			rmiRegistry = LocateRegistry.getRegistry(1099);
    		}
    		catch(Exception ee) {
    			tx.append(ee.getMessage());
    		}
    	} catch(Exception e) {
    		tx.append(e.getMessage());
    	}
    	try {
    		rmiRegistry.rebind("server",b);
    	} catch(Exception e) {
    		tx.append(e.getMessage());
    	}
    	
        tx.append("\n"+"Server started");
    }
    public void dispGUI() {
    	frame = new JFrame("Group Chat Server");
	    main = new JPanel();
	    bt = new JButton("Start");
	    tx = new JTextArea();
	    main.setLayout(new BorderLayout(5,5)); 
	    main.add(bt, BorderLayout.NORTH);
	    main.add(new JScrollPane(tx), BorderLayout.CENTER);  
	    bt.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e){
		    	  if(bt.getText() == "Start") {
		    		  start();
		    		  bt.setText("Stop");
		    	  }
		    	  else {
		    		  stop();
		    		  bt.setText("Start");
		    	  }
		      }
		      
	    });
	    frame.setContentPane(main);
	    frame.setSize(600,600);
	    frame.setVisible(true);  
    }
    public void stop() {
    	try {
    		b.publish("Server stopped..");
    		rmiRegistry.unbind("server");
    		UnicastRemoteObject.unexportObject(b, true);
    		//UnicastRemoteObject.unexportObject(rmiRegistry, true);
    		tx.append("\n"+"Server stopped");
    	} catch(Exception e) {
    		tx.append(e.getMessage());
    	}

    }
}