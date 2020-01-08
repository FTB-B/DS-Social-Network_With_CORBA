//package social_network_system;
//import social_network_system.social_networkPackage.*;
import social_network_system.*;
import java.awt.*;  
import java.awt.event.*;  
import java.util.*;  
import javax.swing.*;  
import javax.swing.tree.*;
//import test.*;
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

   
public  class Groupform extends JFrame implements ActionListener 
{  
    Server server;   
    Coordinator coordinator;
    CoordinatorSystem coordinatorSystem;
    Thread thread1;
    Thread thread2;
	
	public static ArrayList<UniMessage> unimsgs = new ArrayList<UniMessage>();
    public static GroupMessage groupmsg , usermsg;
	//Srever server1;
	SocialNetworkClient socialnetworkclient;
	Random seed = new Random();  
    JScrollPane scrollPane;  
    int count = 1;  
	
	private JLabel lblusername,lblwelcome,lbltxtname;
    private JTextField ta; // for instruction
	private JTextField tname; // to collect user name
   //  private JTextField tusername; // to collect username
	private JTextField tnwuser; // to collect user name
    private JPasswordField tpassword; // to collect the password
	private JPasswordField tnwpass; // to collect the password
	private JPasswordField tgroupass; //to collect the group password
    private JButton login,clear,join,Groupchat , signout,leavegroup; // control buttons
    private String loginFile;
    private Properties prop; 
	//String groupname;
	MouseEvent me = null;
	DefaultMutableTreeNode root =  new DefaultMutableTreeNode("Social Network "); 
    JTree tree = new JTree(root);
	public String inputusername= "m";
	public String groupname = "m";
	String groupass = "";
	String Ousername = "m";
	String s;
	String result = "";
	String[] bindusers;
	ORB orb;
	
	public Groupform(String s , String groupname , String username , String groupass )
	{
	
        this.groupass = groupass;
		groupmsg = new GroupMessage("", "");
		//usermsg = new GroupMessage("", "");
	    try
		{
		    orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);
            org.omg.CORBA.Object object = orb.resolve_initial_references("NameService");
            NamingContextExt namingContext = NamingContextExtHelper.narrow(object);
			
	        //this.orb = orb;
	        this.s = s;
	        //  SplitTree.main(String[]);
		    JFrame f = new JFrame(); 
            JPanel framePanel = new JPanel();
	        //inputusername= username.getText();
		    inputusername= username;
		    //String password= inputpassword;
		    //String groupname = "m";
	        //groupname = "m";
		    this.groupname = groupname;
		    //String Ousername = "m";
		    int nodecount = 0;
		/////////////////////////////////// /////////////////
		
	       
		    DefaultMutableTreeNode node =  new DefaultMutableTreeNode("Group " + groupname);  
            root.add(node);  
		
			String[] names = s.split(":");
			String[] names1 = names[0].split(",");
			String[] names2 = names[1].split(",");
			//String names[] = s.split(",");
			//String namee 
			for(String name : names1)
			{
			    node.add(new DefaultMutableTreeNode(name));
			}
			   
		
		  	JScrollPane scroll = new JScrollPane(tree);
            scroll.setOpaque(false);
            framePanel.add(scroll, BorderLayout.CENTER);
	
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            //f.setContentPane(new SplitTree().getContent());  
            f.setSize(500,400);  
            f.setLocation(200,200);  
            f.setVisible(true); 
		    lblusername=new JLabel("Username:",JLabel.RIGHT);
		    lbltxtname=new JLabel(inputusername,JLabel.RIGHT);
            lblwelcome=new JLabel("Welcome to Social Network",JLabel.RIGHT);
		    lblwelcome.setBounds(40,50,150,20);
		    lblusername.setBounds(0,70,150,20);
		    lbltxtname.setBounds(50,200,150,20);
            Panel panel1 = new Panel();
		    Panel panel2 = new Panel();
		    panel1.add(lblwelcome);
		    panel1.add(lblusername);
		    panel1.add(lbltxtname);
		    //JButton showtree;
		    Groupchat=new JButton("Send Message to Group ");
	        Groupchat.addActionListener(this);
		    Groupchat.setBounds(200,60,80,20);
		    panel1.add(Groupchat);
		    signout=new JButton("Sign Out");
	        signout.addActionListener(this);
		    signout.setBounds(200,80,80,20);
			leavegroup=new JButton("Leave the group");
	        leavegroup.addActionListener(this);
		    leavegroup.setBounds(500,500,80,20);
			panel1.add(leavegroup);
			panel1.add(signout);
		    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, scroll);
		    splitPane.setDividerLocation(180); 
            f.getContentPane().add(splitPane);
		
		
		//////////////////////////double click //////////////////
		    tree.addMouseListener(new MouseAdapter()
		    {
                public void mouseClicked(MouseEvent me ) 
				{
                    doMouseClicked(me);
                }
            });
	    }
	
	   catch (Exception e) 
	    {
            System.err.println ("CORBA error - " + e);
        }
    }	  
		//MouseEvent me = null;
/////////////////////////////////////////////////////		
	void doMouseClicked(MouseEvent me ) 
	{
        TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
	    //String reciever = tp.getLastPathComponent().toString();
        if (tp != null)
		{
	     	DefaultMutableTreeNode node =(DefaultMutableTreeNode)tp.getLastPathComponent( );
            if (node.isLeaf())
            {
	            if (me.getClickCount() == 1) { System.out.println(tp.getLastPathComponent());}
		        else if (me.getClickCount() == 2) 
				{
		            
					String reciever = tp.getLastPathComponent().toString();
					String binduser = "";
					String bindresult = "";
					
                    System.out.println("Double" +reciever);
			        //JOptionPane.showMessageDialog(this, inputusername);
			        UniMessage unimsg = new UniMessage(inputusername, reciever, groupname);
					
					String bresult = unimsg.isonline(reciever, groupname);
					//System.out.println( "the result of checking online " + bresult);
					if(bresult.equals("online"))
					{
					  JFrame f = new JFrame();
					  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                      f.setLocation(100,300);
					  JOptionPane.showMessageDialog(f, "The selected user in online and you can talk online  with him/her");
					}
					else
					{
					  JFrame f = new JFrame();
					  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                      f.setLocation(100,300);
					  JOptionPane.showMessageDialog(f, "The selected user in offline and you can not talk  online with him/her");
					}
                    unimsgs.add(unimsg);
                    unimsg.setVisible(true);
					//}
					//unimsgs.add(unimsg);
			
                } 
	        }
	    }
	 	
    }


	
	
	
////////////////////////////////////////////////////////////////////////////////////////

    void groupchat()
    {
  
        groupmsg = new GroupMessage(inputusername, groupname);
        groupmsg.setVisible(true); 
    }	
//////////////////////////////////////////////////////////////////////////////
    void signout()
    {
        
		System.out.println("enter group form sign out"); //server1.stop();
        //socialnetworkclient.signout();
		//socialnetworkclient.server.stop();
		try
        {
            org.omg.CORBA.Object object2 =   orb.resolve_initial_references("NameService");
            NamingContextExt namingContext = NamingContextExtHelper.narrow(object2);
            NameComponent path[] = namingContext.to_name(groupname + "-" + inputusername);
            namingContext.unbind(path);
			//Groupform.this.setVisible(false);
		Groupform.this.dispose();
		//.close();
        }
        catch(Exception e)
        {
		 System.err.println ("CORBA error - " + e);

        }
		
		//socialnetworkclient.setVisible(true);
		//System.exit(0);
		
  
    }
///////////////////////////////////////////////////////////////////////////////
void leavegroup()
{
   String cname = "";
   String rst = "";
   
   try
		    {
			
			    ORB orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);

	            
                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); //jadid
		        
                NamingContextExt ncRef =  NamingContextExtHelper.narrow(objRef); //jadid
		                 	       
		        String name = groupname + "-coordinator";
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
				    coordinatorSystem = new CoordinatorSystem(inputusername , groupname, groupass);
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
			    cname = coordinator.getcoordinator(inputusername,groupname, groupass);
				JFrame f = new JFrame();
			    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                f.setLocation(100,300);
				JOptionPane.showMessageDialog(f, "The group coordinator username is " + cname + "and you your leave request sent to him/her");
				rst = coordinator.leave(inputusername,groupname, groupass);
				if(rst.equals("ok"))
				{
				   JOptionPane.showMessageDialog(f, "You deleted from the group ");
				   try
                    {
                        org.omg.CORBA.Object object2 =   orb.resolve_initial_references("NameService");
                        NamingContextExt namingContext = NamingContextExtHelper.narrow(object2);
                        NameComponent path[] = namingContext.to_name(groupname + "-" + inputusername);
                        namingContext.unbind(path);
			            //Groupform.this.setVisible(false);
		                Groupform.this.dispose();
		               //.close();
                    }
                    catch(Exception e)
                    {
		                System.err.println ("CORBA error - " + e);

                    }
					System.exit(0);
				}
			}
			catch (Exception e) 
	        {
                System.err.println ("CORBA error - " + e);
            }
            System.out.println("cname is " + cname);
			System.out.println("leave rst is  " + rst);
}	
	
/////////////////////////////////////////////////////////////////////////////////////	
   
    public void actionPerformed(ActionEvent e) 
	{  
       // scrollPane.getViewport().setView(getTree()); 
        java.lang.Object source=e.getSource();
        if(source==Groupchat) groupchat();
        else if (source == leavegroup)	leavegroup();
		else if (source == signout)	signout();
    }  
   
    private JSplitPane getContent() 
	{  
        JSplitPane splitPane =  
        new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  getLeftComponent(),   getRightComponent());  
		/*SplitPane splitPane =  
            new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  
                           getLeftComponent(),  
                           getRightComponent()); 	*/			   
        splitPane.setDividerLocation(180);  
        return splitPane;  
    }  
   
    private JPanel getLeftComponent() 
	{  
	    
		lblusername=new JLabel("Username:",JLabel.RIGHT);
        lblwelcome=new JLabel("Welcome to Social Network",JLabel.RIGHT);
	
	    //lblusername.setBounds(40,150,150,20);
	    lblwelcome.setBounds(40,50,150,20);
		
        // JButton button = new JButton("change tree");  
        // button.addActionListener(this);  
        JPanel panel = new JPanel();  
        panel.add(lblwelcome);  
        return panel;  
    }  
   
    private JScrollPane getRightComponent() {  
        scrollPane = new JScrollPane(getTree());  
		scrollPane = new JScrollPane();  
        return scrollPane;  
    }  
   
    private JTree getTree() {  
        DefaultMutableTreeNode root =  
            new DefaultMutableTreeNode("Social Network " + count++);  
		int nodes = 1 + seed.nextInt(4);  
        for(int j = 0; j < nodes; j++) {  
            DefaultMutableTreeNode node =  
                new DefaultMutableTreeNode("Group " + (j+1));  
            root.insert(node, j);  
            int subNodes = 1 + seed.nextInt(4);  
            for(int k = 0; k < subNodes; k++) {  
                node.insert(new DefaultMutableTreeNode("User " +  
                                               (j+1) + (k+1)), k);  
            }  
        } 
        JTree tree = new JTree(new DefaultTreeModel(root));  
        expandTree(tree);  
        return tree;  
    }  
   
    private void expandTree(JTree tree) {  
        DefaultMutableTreeNode root =  
            (DefaultMutableTreeNode)tree.getModel().getRoot();  
        Enumeration e = root.breadthFirstEnumeration();  
        while(e.hasMoreElements()) {  
            DefaultMutableTreeNode node =  
                (DefaultMutableTreeNode)e.nextElement();  
            if(node.isLeaf()) break;  
            int row = tree.getRowForPath(new TreePath(node.getPath()));  
            tree.expandRow(row);  
        }  
    }  
   
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