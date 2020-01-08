//package test;
//package social_network_system;
import social_network_system.*;
import java.awt.*;  
import java.awt.event.*;  
import java.util.*;  
import javax.swing.*;  
import javax.swing.tree.*;
import java.io.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.lang.*; 
import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.mysql.jdbc.PreparedStatement;
import org.apache.derby.impl.jdbc.EmbedPreparedStatement;
import org.apache.derby.impl.jdbc.EmbedResultSet;


public  class creategroup extends JFrame implements ActionListener {  
    Server server;   
   Coordinator coordinator;
    CoordinatorSystem coordinatorSystem;
    Thread thread1;
    Thread thread2;
	Groupform groupform;
	socialgroups sgroups;
    Social_Network social_network;
	Social_NetworkSystem social_networkSystem;
	
	
	//Random seed = new Random();  
    JScrollPane scrollPane;  
    int count = 1;  
	
	private JLabel lblgroupname,lblgroupass, lbltitr;
    private JTextField tgroupname; // for instruction
	private JTextField tname; // to collect user name
  //  private JTextField tusername; // to collect username
	private JTextField tnwuser; // to collect user name
    private JPasswordField tpassword; // to collect the password
	private JPasswordField tnwpass; // to collect the password
	private JPasswordField tgroupass; //to collect the group password
    private JButton bcreategroup , clear; // control buttons
    private String loginFile;
    private Properties prop; 
	//String groupname;
	MouseEvent me = null;
	DefaultMutableTreeNode root =  new DefaultMutableTreeNode("Social Network "); 
    JTree tree = new JTree(root);
	public String inputusername= "";
	String password;
	String groupname = "m";
	String Ousername = "m";
	String message = "n";
	String sender = "";
	String reciever = "";
	ORB orb;

	
	public creategroup(String username,  ORB orb)
	{ 
	  
	  // this.orb = orb;
		inputusername = username;
		//this.password = password;
		JFrame f = new JFrame(); 
        JPanel panel = new JPanel();
	
	//super("Login Autentification");
    setSize(380,220);
    setLocation(500,280);
    //setLocation (700,280)
    panel.setLayout (null); 



    lbltitr=new JLabel("Please enter group specification you want to create",JLabel.RIGHT);
	lblgroupname=new JLabel("Groupname:",JLabel.RIGHT);
	lblgroupass=new JLabel("GroupPassword:",JLabel.RIGHT);
	
	

	
	tgroupname=new JTextField(15);
	tgroupname.setForeground(Color.black);
    tgroupname.setBackground(Color.white);
    tgroupname.setFont(new Font("Verdana",Font.PLAIN,14));
	 
		
	tgroupass=new JPasswordField(15);
	tgroupass.setForeground(Color.black);
    tgroupass.setBackground(Color.white);
    tgroupass.setFont(new Font("Verdana",Font.PLAIN,14));
	tgroupass.setEchoChar('*'); // masking character for the password field
	
	
	

	// construction of buttons and adding Listeners
    bcreategroup=new JButton("Create");
	bcreategroup.addActionListener(this);
    clear=new JButton("Reset");
	clear.addActionListener(this);
	
	
	
	lbltitr.setBounds(37 ,  50, 300 , 20);
	lblgroupname.setBounds(5,80,150,20);
	
	lblgroupass.setBounds(5,110,150,20);

	tgroupname.setBounds(165,80,150,20);
  
	tgroupass.setBounds(165,110,150,20);
    bcreategroup.setBounds(165,140,77,20);
	clear.setBounds(245,140,77,20);

 
   


    panel.add(lbltitr);
	panel.add(lblgroupname);

	panel.add(lblgroupass);

	panel.add(bcreategroup);
	panel.add(clear);
	
	panel.add(tgroupname);
  
   
	panel.add(tgroupass);

    
	
	getContentPane().add(panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
   
			  
	}
	
	////////////// event ////////////////////////////////////
	public void actionPerformed(ActionEvent e){
                java.lang.Object source=e.getSource();
                if(source==bcreategroup ) create(inputusername , password , tgroupname.getText() , orb);
        }
////////////////////////////////////////////////////////////
public void create(String username ,String password , String groupname , ORB orb)
{
    this.orb = orb;
	String result = "";
	String uname = username ;
	String gpassword ;
	gpassword = password;
	String gname = groupname;

	
	
	
	      try
		{
			
			orb = ORB.init(new String[]{"-ORBInitialPort", "5100", "-ORBInitialHost", "localhost"}, null);

	        // Obtain object reference for name service ...
           // org.omg.CORBA.Object object =  orb.resolve_initial_references("NameService"); //ghadim
		   // get the root naming context
          org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); //jadid
		  // Use NamingContextExt instead of NamingContext. This is 
          // part of the Interoperable Naming Service.  
          NamingContextExt ncRef =  NamingContextExtHelper.narrow(objRef); //jadid
		  // resolve the Object Reference in Naming
		  
		  String name = "Social_Network";
		  org.omg.CORBA.Object social_networkRef = null;
		 // try
         //   {
           //     social_networkRef = ncRef.resolve_str(name);
          //  }
        //catch(Exception e)
           // {
            //    System.out.println("not resolve name ");
           // }
            //if(social_networkRef != null)
           // {
             //   System.out.println("social_nnetwork ref after resolve not null");
			//	social_network = Social_NetworkHelper.narrow(social_networkRef);
           // }
           // else
            //{
               // System.out.println("social_network ref after resolve  null");
			   social_networkSystem = new Social_NetworkSystem(uname , uname);
                thread1 = new Thread(social_networkSystem);
               thread1.start();
              //  Thread.sleep(1000);
                try
                {
                    social_networkRef = ncRef.resolve_str(name);
                }
                catch(Exception e)
                {
                      System.out.println("social_nnetwork ref after not resolve after else");
                }
                if(social_networkRef != null)
                {
                    System.out.println("not null");                   
				   social_network = Social_NetworkHelper.narrow(social_networkRef);
                }
            //}
			result = social_network.Gcreate(uname , gpassword , gname);
			System.out.println(result);
			if(result.equals("ok"))
			{
			  JOptionPane.showMessageDialog(this,"New Group Create \n For enter your group please sign out and use login part in first page");
			}
		}
		
		catch (Exception e) 
	    {
            System.err.println ("CORBA error in create group  - " + e);
        }
	
	
	
	
	
	

 
 return ;
}	

	//////////////////// ///// ////////////////////////////
	  public static void main(String[] args) {  
        //SplitTree mytest = new SplitTree(new Log());      
	   // JFrame f = new JFrame();  
       //  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      // f.setContentPane(new SplitTree(new Log()).getContent());  
       //  f.setSize(500,400);  
      //  f.setLocation(200,200);  
      // f.setVisible(true);  
//new SplitTree().setVisible(true);

    }  
}
