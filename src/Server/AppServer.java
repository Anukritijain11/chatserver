package Server;
import java.io.*;
import java.net.*;

import utility.ClientData;

public class AppServer implements Runnable{

	ServerSocket ss;
	Socket s;
	Thread t1;
	
	public AppServer() {
	try {
		ss=new ServerSocket(8888);
		t1=new Thread(this);
		t1.start();
		System.out.println("server started....");
	}
	catch(Exception ex) {
		
	}
	}
	public void run() {
		for(;;) {
			try {
				s=ss.accept();
				Connect c=new Connect(s);
			}
			catch(Exception ex) {
				
			}
		}
	}
	
	class Connect{
		Connect(Socket s){
			try{
				ObjectInputStream ois=new ObjectInputStream(
					s.getInputStream());
			ClientData obj=(ClientData)ois.readObject();
			System.out.println("name :"+obj.name);
			System.out.println("email :"+obj.email);
			System.out.println("mobile:"+obj.mobile);
			
			}	
			catch(Exception ex) {
				
			}
	}
	}
	public static void main(String args[]) {
		new AppServer();
	}
}
//port scanner project banana hai cyber security ke liye	
//jamtara web series
//docs.oracle.com
//127.0.0.1 means localhost