//package social_network_system;
//import social_network_system.social_networkPackage.*;
import social_network_system.*;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
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
//import java.util.properties;


//
//
// socialnetworkClient
//
// This client demonstrates the socialnetwork server and servant.
// A menu is presented to the user, allow he or she to add
// users, and look up their names & email addresses
//
//
 class SocialNetworkClient extends JFrame   implements ActionListener
{
   ////////////////////////////////////////////////
   
    Server server;   
    Coordinator coordinator;
    CoordinatorSystem coordinatorSystem;
    Thread thread1;
    Thread thread2;
	Groupform groupform;
	socialgroups sgroups;
    Social_Network social_network;
	Social_NetworkSystem social_networkSystem;
	String inputusername;
	String inputgroupname;
	String inputgroupass;

   
   
   ////////////////////The GUI widgets
    private JLabel lblusername,lblpassword,lblgroupass,lblnwuser,lblnwpass,lblname,lblgroupname;
    private JTextField ta; // for instruction
    private JTextField tname,tgroupname; // to collect user name
    public JTextField tusername; // to collect username
	private JTextField tnwuser; // to collect user name
    public JPasswordField tpassword; // to collect the password
	private JPasswordField tnwpass; // to collect the password
	private JPasswordField tgroupass; //to collect the group password
    private JButton login,clear,join; // control buttons
    private String loginFile;
    private Properties prop; 
	//String args[];
	//public social_network SocialNetwork;
    //static void checkLogin(String args[]);
    // SocialNetworkServer socialserver;
    //Thread thread1;
    //Groupform groupform;
	Connection conn = null;
    String driver = "org.apache.derby.jdbc.ClientDriver";
    ResultSet res = null;
	PreparedStatement ps = null;

	
///////////////////////// Constructor ////////////////////////////////////////////////////
  public SocialNetworkClient()
{
    
	JPanel panel = new JPanel();
	//super("Login Autentification");
    setSize(700,500);
    setLocation(500,280);
    //setLocation (700,280)
    panel.setLayout (null); 



    lblusername=new JLabel("Username:",JLabel.RIGHT);
	lblgroupname=new JLabel("Groupname",JLabel.RIGHT);
    lblpassword=new JLabel("Password:",JLabel.RIGHT);
	lblgroupass=new JLabel("GroupPassword:",JLabel.RIGHT);
	lblname=new JLabel("Name:",JLabel.RIGHT);
	lblnwuser=new JLabel("Username:",JLabel.RIGHT);
	lblnwpass=new JLabel("Password:",JLabel.RIGHT);
	
	tname=new JTextField(15);
	tname.setForeground(Color.black);
    tname.setBackground(Color.white);
    tname.setFont(new Font("Verdana",Font.PLAIN,14));
	
	tgroupname=new JTextField(15);
	tgroupname.setForeground(Color.black);
    tgroupname.setBackground(Color.white);
    tgroupname.setFont(new Font("Verdana",Font.PLAIN,14));
	 
	 
	 
	tusername=new JTextField(15);
	tusername.setForeground(Color.black);
    tusername.setBackground(Color.white);
    tusername.setFont(new Font("Verdana",Font.PLAIN,14));
	
    tpassword=new JPasswordField(15);
	tpassword.setForeground(Color.black);
    tpassword.setBackground(Color.white);
    tpassword.setFont(new Font("Verdana",Font.PLAIN,14));
	tpassword.setEchoChar('*'); // masking character for the password field
	
	
	tgroupass=new JPasswordField(15);
	tgroupass.setForeground(Color.black);
    tgroupass.setBackground(Color.white);
    tgroupass.setFont(new Font("Verdana",Font.PLAIN,14));
	tgroupass.setEchoChar('*'); // masking character for the password field
	
	tnwuser=new JTextField(15);
	tnwuser.setForeground(Color.black);
    tnwuser.setBackground(Color.white);
    tnwuser.setFont(new Font("Verdana",Font.PLAIN,14));
	
	
	tnwpass=new JPasswordField(15);
	tnwpass.setForeground(Color.black);
    tnwpass.setBackground(Color.white);
    tnwpass.setFont(new Font("Verdana",Font.PLAIN,14));
	tnwpass.setEchoChar('*'); // masking character for the password field
	
	
	// construction of buttons and adding Listeners
    login=new JButton("Login");
	login.addActionListener(this);
    clear=new JButton("Reset");
	clear.addActionListener(this);
	join=new JButton("Join");
	join.addActionListener(this);
	
	
	lblusername.setBounds(40,150,150,20);
	lblgroupname.setBounds(40,180,150,20);
	lblpassword.setBounds(40,180,150,20);
	lblgroupass.setBounds(40,210,150,20);
    tusername.setBounds(200,150,150,20);
	tgroupname.setBounds(200,180,150,20);
    tpassword.setBounds(200,180,150,20);
	tgroupass.setBounds(200,210,150,20);
    login.setBounds(200,240,77,20);
	clear.setBounds(280,240,77,20);

    lblname.setBounds(300,150,150,20);
	lblnwuser.setBounds(300,180,150,20);
    lblnwpass.setBounds(300,210,150,20);
	tname.setBounds(460,150,150,20);
	tnwuser.setBounds(460,180,150,20);
    tnwpass.setBounds(460,210,150,20);
    join.setBounds(490,240,80,20);


    panel.add(lblusername);
	panel.add(lblgroupname);
	panel.add(lblname);
	//panel.add(lblpassword);
	panel.add(lblgroupass);
	panel.add(lblnwuser);
	panel.add(lblnwpass);
	panel.add(login);
	panel.add(clear);
	panel.add(tname);
	panel.add(tgroupname);
    panel.add(tusername);
	panel.add(lblname);
    //panel.add(tpassword);
	panel.add(tgroupass);
	panel.add(tnwuser);
	panel.add(tnwpass);
    panel.add(join);
	
	getContentPane().add(panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   // setVisible(true);
   
  
	
	
	
}	
 
///////////////////////////////////////////////////////////////

    public void actionPerformed(ActionEvent e)
	{
        java.lang.Object source=e.getSource();
        if(source==clear) clearFields();
        if(source==login) checkLogin();
	    if(source==join) register();
    }

    void clearFields()
	{
        tusername.setText(null);
        tpassword.setText(null);
	    tgroupass.setText(null);
    }
////////////////////////////////////////////////////////////////////////	
   public static void main(String args[]) throws IOException
    {
        new SocialNetworkClient().setVisible(true);
	  
	    /* try
	    {
            

            for (;;)
            {
                try
                {
		            // Print menu
                    System.out.println ("1- Add user");
                    System.out.println ("2- Look up email");
                    System.out.println ("3- Look up name");
					System.out.println ("4- login");
					System.out.println ("5- register");
					System.out.println ("6- sendmsg");
                    System.out.println ("7- Exit");

                    System.out.print ("Command :");

                    // Read a line from user
                    String command = din.readLine();

                    // Convert to number
                    Integer num = new Integer (command);
                    int choice = num.intValue();

                    // Variables we'll need for service calls
                    String name;
                    String email;
					String username;
					String password;
					String grouppass;
					String result;
					String groupname;
					String sender;
					String rcvusername;
					String message;
					

                    switch (choice)
                    {
                        case 1:
		                       	System.out.print ("Name:");
			                    name  = din.readLine();
			                    System.out.print ("Email:");
			                    email = din.readLine();

			                    // Call socialnetwork service
			                    SocialNetwork.record_user(name,email);
			                    break;
			
			            case 2:
			                    System.out.println ("Name:");
			                    name  = din.readLine();

			                    // Call socialnetwork service
			                    email = SocialNetwork.email_from_name(name);
			                    System.out.println ("Email of " + name + " is " + email);
			                    break;
			
			            case 3:
			                    System.out.println ("Email:");
			                    email  = din.readLine();

        		                // Call socialnetwork service
			                    name = SocialNetwork.name_from_email(email);
			                    System.out.println ("Name of " + email + " is " + name);		
			                    break;
			
			           case 4:
			                    System.out.print ("UserName:");
			                    username  = din.readLine();
			                    System.out.print ("password:");
			                    password = din.readLine();
			                    System.out.print ("grouppass:");
			                    grouppass  = din.readLine();

			                    // Call socialnetwork service
			                    result = SocialNetwork.login(username,password,grouppass);
			                    System.out.println (result);
			                    break;
			           case 5:
			                    System.out.print ("Name:");
			                    name   = din.readLine();
			                    System.out.print ("Username:");
			                    username = din.readLine();
			                    System.out.print ("password:");
			                    password = din.readLine();
			                    System.out.print ("Groupname:");
			                    groupname = din.readLine();
			

			                   // Call socialnetwork service
			                    result = SocialNetwork.register(name,username,password,groupname);
			                    System.out.println (result);
			                     break;
			           case 6:
			                    System.out.print ("sender username :");
			                    sender   = din.readLine();
			                    System.out.print ("send message to Username:");
			                    rcvusername = din.readLine();
			                    //System.out.print ("password:");
			                    //password = din.readLine();
			                    //System.out.print ("Groupname:");
			                    //groupname = din.readLine();
			                    System.out.print ("enter your message: ");
			                    message = din.readLine();
			

			                    // Call socialnetwork service
			                    result = SocialNetwork.sendmsg(sender,rcvusername ,message);
			                    System.out.println (result);
			                    break;
	
			           case 7:
			                    System.exit(0);
                    }
                }
		       catch (user_exists  already_there)
		        {
		            System.out.println ("User already exists - cannot be added to address book");
		        }
		       catch (unknown_user bad_user)
		        {
		            System.out.println ("User doesn't exist");
		        }
            }
        }
	
	    catch (Exception e)
	    {
            System.err.println ("CORBA error - " + e);
	   }*/
    }

//////////////////////////////////////////////////////////////////	
	public String getname()
	{
		String inputusername=tusername.getText();
		return(inputusername);
	}
	
	
/////////////////////// check login /////////////////////////////////////////	
	
		
	public void checkLogin()
	{
            String result = "" ;
			//String inputusername=tusername.getText();
			inputusername = tusername.getText();
			//String inputgroupname=tgroupname.getText();
			inputgroupname = tgroupname.getText();
			//String inputpassword=new String(tpassword.getPassword());
		    //String inputgroupass=new String(tgroupass.getPassword());
			inputgroupass = new String(tgroupass.getPassword());
			String groupname = "";
			String answer = "";
			
   
		    try
		    {
			
			    ORB orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);

	            // Obtain object reference for name service ...
                // org.omg.CORBA.Object object =  orb.resolve_initial_references("NameService"); //ghadim
		        // get the root naming context
                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); //jadid
		        // Use NamingContextExt instead of NamingContext. This is 
                // part of the Interoperable Naming Service.  
                NamingContextExt ncRef =  NamingContextExtHelper.narrow(objRef); //jadid
		        // resolve the Object Reference in Naming
         
		       
		        String name = inputgroupname + "-coordinator";
		        org.omg.CORBA.Object coordinatorRef = null;
		        try
                {
                    coordinatorRef = ncRef.resolve_str(name);
                }
               catch(Exception e)
                {

                }
                if(coordinatorRef != null)
                {
                    System.out.println("coordinator ref after resolve not null");
				    coordinator = CoordinatorHelper.narrow(coordinatorRef);
                }
               else
                {
                    System.out.println("coordinator ref after resolve  null");
				    coordinatorSystem = new CoordinatorSystem(inputusername , inputgroupname, inputgroupass);
                    thread1 = new Thread(coordinatorSystem);
                    thread1.start();
                    //Thread.sleep(1000);
                    try
                    {
                        coordinatorRef = ncRef.resolve_str(name);
                    }
                   catch(Exception e)
                    {

                    }
                    if(coordinatorRef != null)
                    {
                        System.out.println("not null");                   
				        coordinator = CoordinatorHelper.narrow(coordinatorRef);
                    }
                }
			    result = coordinator.login(inputusername,inputgroupname, inputgroupass);
			    System.out.println(result);
		        if (result.equals ("new user"))
			    {
			        int dialogButton = JOptionPane.YES_NO_OPTION;
			        JOptionPane.showConfirmDialog (null, "You are a new user Would You Like to join to this group?","Warning",dialogButton);

			        if(dialogButton == JOptionPane.YES_OPTION)
			        {
			            result = coordinator.join(inputusername,inputusername, inputgroupname);
				        if(result.equals("ok"))
				        {
				            JOptionPane.showMessageDialog(this,"you should wait till all other user accept you to be a new user of group");
				        }
			        }
			    }
				if(result.equals("new group"))
				{
				    JOptionPane.showMessageDialog(this,"This group that you entered doesn't exist before you create it and you are the coordinator of this new group ");
				}
			   else
			    {
			        String r1;
			        String r2;
 			        String[] names = result.split(":");
			        String[] names1 = names[0].split(",");
			        String[] names2 = names[1].split(",");
			
                    for(String name1 : names1)
					{
                        System.out.println(name1);
					}
		           for(String name2 : names2)
				    {
                        System.out.println(name2);
				    }
		            System.out.println(result);
			        if(names1[0].equals("ok"))
                    {
			            JOptionPane.showMessageDialog(this,"correct login.\n welcome.");
			            this.setVisible(false);
			            r1 = result.substring(3);
			            server = new Server(inputusername, inputgroupname);
                        thread2 = new Thread(server);
                        thread2.start();
		                groupform = new Groupform(r1 ,inputgroupname,inputusername , inputgroupass );
			             groupform.setVisible(true);
			            //System.out.println((names2.lenght).toString());
			            if(!(names2[0].equals("")) )
			            {
				            System.out.println("names2[0] not equal nothing ");
				            for(String name2 : names2)
				            {
                                System.out.println(name2);
				            }
			   
			                StringBuilder sentence = new StringBuilder() ;
			                String sentence1;
			                System.out.println(answer);
			                for(String name2 : names2)
                            {
                                answer = "?";
				                System.out.println(name2);
				                sentence.append("A new user with username ").append(name2).append(" wants to join this group \n Do you accept it ?");
				                sentence1 = sentence.toString();
				                //System.out.println(name2);
				                int dialogButton1 =   JOptionPane.showConfirmDialog (this, sentence1,"Question",JOptionPane.YES_NO_OPTION);
                                //System.out.println(dialogButton1);
			                    // if(dialogButton1 == JOptionPane.YES_OPTION)
			                    System.out.println(answer);
			                    switch(dialogButton1)
			                    {
                                    case JOptionPane.YES_OPTION:     answer = "ok"; break;
                                    case JOptionPane.NO_OPTION:      answer = "nok"; break;
                                    //case JOptionPane.CANCEL_OPTION:
                                    case JOptionPane.CLOSED_OPTION:  answer = "nok"; break;
                                }
			   	                System.out.println(answer);
				                result = coordinator.answer(inputusername,name2, inputgroupname , answer);
				                System.out.println(result);
				                if(result.equals("ok"))
				                {
				                    JOptionPane.showMessageDialog(this,"you answer that newuser join to this group or not ");
				                }
			   
				            }
			            }	
			            //social_network_system.groupform nextform = new social_network_system.groupform(r,inputgroupass,inputusername , orb);
			   
			            // coordinatorSystem.Stop();
				        //groupform = new Groupform(inputusername, inputgroupname, this,orb );
				        //groupform = new Groupform(r1 ,inputgroupname,inputusername );
			            // groupform.setVisible(true);
                      //  groupform.setVisible(true);
                        //this.setVisible(false); //ino bayad emtahan konam
                        Thread.sleep(200);
			        }
		        }	
			
		    }
		   catch (Exception e) 
	        {
                System.err.println ("CORBA error - " + e);
            }
    	
		  
      

            	  
				
		    return ;
	}
////////////////////////////leave the group ////////////////////////////////////////	
public void leave()
{
   String result = "";
  // result = coordinator.leave(inputusername , inputgroupname);
}
///////////////////////////SIGN OUT /////////////////////////////////////////////	
 public void signout()
    {
        System.out.println("enter sign out"); 
		server.stop();
		System.out.println("server stop"); 
        groupform.setVisible(false);
        //groupform.dispose();
        this.setVisible(true);
       /* if(thread1!= null && thread1.isAlive())
        {
            coordinatorSystem.Stop();
        }*/


    }
	
///////////////////////////// REGISTER ///////////////////////////////////////////////////////	

public void register()
{
    String result = "" ;
	String inputname=tname.getText();
	//String inputpassword=new String(tnwpass.getPassword());
	//String groupname = "";
	String inputusername= tnwuser.getText();
	System.out.println(inputname);
	System.out.println(inputusername);
	//System.out.println(inputpassword);
	
	try
		{
			
			ORB orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);

	        // Obtain object reference for name service ...
            // org.omg.CORBA.Object object =  orb.resolve_initial_references("NameService"); //ghadim
		    // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); //jadid
		    // Use NamingContextExt instead of NamingContext. This is 
            // part of the Interoperable Naming Service.  
            NamingContextExt ncRef =  NamingContextExtHelper.narrow(objRef); //jadid
		    // resolve the Object Reference in Naming
         
		    //String name = "social_network";  //jadid
		    String name = "Social_Network";
		    org.omg.CORBA.Object social_networkRef = null;
		    try
            {
                social_networkRef = ncRef.resolve_str(name);
            }
           catch(Exception e)
            {

            }
            if(social_networkRef != null)
            {
                System.out.println("social_nnetwork ref after resolve not null");
				social_network = Social_NetworkHelper.narrow(social_networkRef);
            }
            else
            {
                System.out.println("social_network ref after resolve  null");
				social_networkSystem = new Social_NetworkSystem(inputname , inputusername);
                thread1 = new Thread(social_networkSystem);
                thread1.start();
                Thread.sleep(1000);
                try
                {
                    social_networkRef = ncRef.resolve_str(name);
                }
                catch(Exception e)
                {

                }
                if(social_networkRef != null)
                {
                    System.out.println("not null");                   
				    social_network = Social_NetworkHelper.narrow(social_networkRef);
                }
            }
			result = social_network.sregister(inputname,inputusername);
			System.out.println(result);
			String r;
			String[] names = result.split(",");
            for(String name1 : names){
            System.out.println(name1);}
			if(names[0].equals("ok"))
            {
			    JOptionPane.showMessageDialog(this,"welcom.\n you joined to this social network.");
			    this.setVisible(false);
			    r = result.substring(3);
			    //server = new Server(inputusername, inputgroupname);
                // thread2 = new Thread(server);
                // thread2.start();
			    sgroups = new socialgroups(r ,inputusername,inputusername,orb );
			    sgroups.setVisible(true);
			    //social_network_system.groupform nextform = new social_network_system.groupform(r,inputgroupass,inputusername , orb);
			   
			    // coordinatorSystem.Stop();
				//groupform = new Groupform(inputusername, inputgroupname, this,orb );
                //groupform.setVisible(true);
                //this.setVisible(false); //ino bayad emtahan konam
                //Thread.sleep(200);
			}
		}
		catch (Exception e) 
	    {
            System.err.println ("CORBA error - " + e);
        }
    	
	
        return ;
 }
 
 
 ////////////////////////////////////////////////////////////////////

	
			
}