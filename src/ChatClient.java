import java.awt.*;
import javax.swing.*;


import java.io.*;
import java.net.*;
import java.awt.event.*;

public class ChatClient extends JFrame implements 
Runnable{

  JLabel L1,L2;
  JTextArea ta1;
  JTextField t1;
  JButton b1,b2;
  JPanel p1,inputPanel,centerPanel,buttonPanel;
  
  //ServerSocket ss;
  Socket s;
  Thread th1;
  BufferedReader br;
  BufferedWriter bw;
  String name;
  
  
  
  public ChatClient()
  {
  this.setVisible(true);
  this.setSize(300,300);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setTitle("Chat Client");
  p1 = new JPanel();
  L1 = new JLabel("Name");
  L2 = new JLabel();
  ta1 = new JTextArea(5,20);
  t1 = new JTextField(20);
  b1 = new JButton("Send");
  b2 = new JButton("Cancel");
  inputPanel = new JPanel();
  centerPanel = new JPanel();
  buttonPanel = new JPanel();
  p1.setLayout(new BorderLayout());
  inputPanel.add(L1);
  inputPanel.add(L2);
  L2.setForeground(Color.red);
  p1.add(inputPanel,"North");
  centerPanel.setLayout(new BorderLayout());//gui/io/thread bhi hai iss project me
  //study real time web protocol
  //serversocket  in java study
  JScrollPane js = new JScrollPane(ta1);
  centerPanel.add(js,"Center");
  centerPanel.add(t1,"South");
  p1.add(centerPanel,"Center");
  buttonPanel.add(b1);
  buttonPanel.add(b2);
  p1.add(buttonPanel,"South");
  p1.setBorder(BorderFactory.createTitledBorder("Chat Server"));
  this.add(p1);
  this.setSize(300,350);
  
  name = JOptionPane.showInputDialog("Enter Name:");
  L2.setText(name);
  b1.addActionListener(e->actionB1(e));
  b2.addActionListener(e->actionB2(e));
  //coarse location -->exact location
  //network location-->
  try
  {
    //ss = new ServerSocket(8888);
    //s = ss.accept();//incoming client socket
    
    s = new Socket("localhost",8888);//or ip address in place of localhost
    
    
    br = new BufferedReader(new 
        InputStreamReader(s.getInputStream()));
  
    bw = new BufferedWriter(new 
        OutputStreamWriter(s.getOutputStream()));
    bw.write("Hello");
    bw.newLine();
    bw.flush();
    th1 = new Thread(this);
    th1.start();
  }
  
  catch(Exception ex)
  {
    
  }
  
  }
  
  public void run()
  {
    for(;;)
    {
      try {
        ta1.append(br.readLine()+"\n");
      }
      catch(Exception ex)
      {
        
      }
    }
  }
  public void actionB1(ActionEvent e) {
    try {
      String msg = name+" says-->"+t1.getText();
      bw.write(msg);
      bw.newLine();
      bw.flush();
      ta1.append(msg+"\n");
    }
    catch(Exception ex)
    {
      
    }
  }
  public void actionB2(ActionEvent e)
  {
    System.exit(0);
  }
  
  public static void main(String args[])
  {new ChatClient();
  }
}
