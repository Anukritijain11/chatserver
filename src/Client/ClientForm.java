//temp email-->website
//home-->start me curser
//end-->end me curser
//select+down arrow
//select+uparrow
//joinsuperset website
//update ke liye metere connection ko off
//pehle appserver run karenge then clientform

package Client;

import java.awt.event.ActionEvent;

import javax.swing.*;

import utility.ClientData;

import java.io.*;
import java.net.*;

public class ClientForm extends JFrame {
    JLabel L1,L2,L3;
	JTextField t1, t2, t3;
	JPanel p1;
	JButton b1;

	public ClientForm() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 500);
		p1 = new JPanel();
		L1 = new JLabel("Name");
		L2 = new JLabel("Email");
		L3 = new JLabel("Mobile");

		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);
		b1 = new JButton("submit to server");

		p1.add(L1);
		p1.add(t1);
		p1.add(L2);
		p1.add(t2);
		p1.add(L3);
		p1.add(t3);
		p1.add(b1);

		b1.addActionListener(e -> ActionB1(e));

		this.add(p1);
		this.setSize(300, 180);
		this.setTitle("Client Form");
	}

	public void ActionB1(ActionEvent e) {
		try {
			Socket s = new Socket("localhost", 8888);
			ClientData obj = new ClientData();
			obj.name = t1.getText();
			obj.email = t2.getText();
			obj.mobile = t3.getText();
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(obj);
			out.close();
			s.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void main(String args[]) {
		new ClientForm();
	}
}
