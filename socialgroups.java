//package social_network_system;
//import social_network_system.social_networkPackage.*;
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

   
public  class socialgroups extends JFrame implements ActionListener {  
    Random seed = new Random();  
    JScrollPane scrollPane;  
    int count = 1;  
	
	private JLabel lblusername,lblwelcome,lbltxtname,lblgroupname,lblgroupass;
    private JTextField ta; // for instruction
	private JTextField tname,tgroupname; // to collect user name
   //  private JTextField tusername; // to collect username
	private JTextField tnwuser; // to collect user name
    private JPasswordField tpassword; // to collect the password
	private JPasswordField tnwpass; // to collect the password
	private JPasswordField tgroupass; //to collect the group password
    private JButton login,clear,join,creatgroup; // control buttons
    private String loginFile;
    private Properties prop; 
	//String groupname;
	MouseEvent me = null;
	DefaultMutableTreeNode root =  new DefaultMutableTreeNode("Social Network "); 
    JTree tree = new JTree(root);
	public String inputusername= "m";
	public String groupname = "m";
	String Ousername = "m";
	String password;
	String s;
	String result = "";
	ORB orb;
	
	creategroup createform ;
	
	public socialgroups(String s ,  String username, String password  , ORB orb )
	{
	   //this.orb = orb;
       try
		{
		orb = ORB.init(new String[]{"-ORBInitialPort", "5100", "-ORBInitialHost", "localhost"}, null);
       org.omg.CORBA.Object object = orb.resolve_initial_references("NameService");
       NamingContextExt namingContext = NamingContextExtHelper.narrow(object);
	  // this.orb = orb;
	   this.s = s;
	   //  SplitTree.main(String[]);
		JFrame f = new JFrame(); 
        JPanel framePanel = new JPanel();
	    //inputusername= username.getText();
		inputusername= username;
		this.password= password;
		//String groupname = "m";
	    //groupname = "m";
		//this.groupname = groupname;
		//String Ousername = "m";
		int nodecount = 0;
		//////////////////////connection to Database to get user group /////////////////
		
			
			
		  
	 
		DefaultMutableTreeNode node =  new DefaultMutableTreeNode(" Network Groups" );  
        root.add(node);  
		
			   String names[] = s.split(",");
			   //String namee 
			   for(String name : names){
			   node.add(new DefaultMutableTreeNode(name));
			   }
			   
		
		
		JScrollPane scroll = new JScrollPane(tree);
        scroll.setOpaque(false);
        framePanel.add(scroll, BorderLayout.CENTER);
		//String inputusername=a.getname();
	//	JOptionPane.showMessageDialog(this,a.tusername.getText(),null,JOptionPane.
        //                ERROR_MESSAGE);
		//
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //f.setContentPane(new SplitTree().getContent());  
         f.setSize(500,400);  
        f.setLocation(200,200);  
        f.setVisible(true); 
		//String inputusername= a.getText();
		//System.out.println(a.tusername.getText());
		lblusername=new JLabel("Username:",JLabel.RIGHT);
		lbltxtname=new JLabel(inputusername,JLabel.RIGHT);
        lblwelcome=new JLabel("Welcome to Social Network",JLabel.RIGHT);
		lblgroupname=new JLabel("Groupname:",JLabel.RIGHT);
	    lblgroupass=new JLabel("GroupPassword:",JLabel.RIGHT);
		lblwelcome.setBounds(40,50,150,20);
		lblusername.setBounds(0,70,150,20);
		lbltxtname.setBounds(50,200,150,20);
        Panel panel1 = new Panel();
		Panel panel2 = new Panel();
		panel1.add(lblwelcome);
		panel1.add(lblusername);
		panel1.add(lbltxtname);
		panel1.add(lblusername);
		framePanel.add(lblgroupname);
		//panel1.add(lblgroupname);
		//panel1.add(lblgroupass);
		//panel1.add(tgroupname);
		//panel1.add(tgroupass);
		//JButton showtree;
		
		
		tgroupname=new JTextField(15);
	    tgroupname.setForeground(Color.black);
        tgroupname.setBackground(Color.white);
        tgroupname.setFont(new Font("Verdana",Font.PLAIN,14));
	 
		
       tgroupass=new JPasswordField(15);
	   tgroupass.setForeground(Color.black);
       tgroupass.setBackground(Color.white);
       tgroupass.setFont(new Font("Verdana",Font.PLAIN,14));
	   tgroupass.setEchoChar('*'); // masking character for the password field
	   
	   
	   lblgroupname.setBounds(5,80,150,20);
	
	   lblgroupass.setBounds(5,110,150,20);

	   tgroupname.setBounds(165,80,150,20);
  
	   tgroupass.setBounds(165,110,150,20);
		creatgroup=new JButton("Create New Group");
	    creatgroup.addActionListener(this);
		creatgroup.setBounds(200,60,80,20);
		panel1.add(creatgroup);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, scroll);
		splitPane.setDividerLocation(180); 
        f.getContentPane().add(splitPane);
		
		
		//////////////////////////double click //////////////////
		tree.addMouseListener(new MouseAdapter()
		{
           public void mouseClicked(MouseEvent me ) {
           doMouseClicked(me);
           }
        });
	 }
	 catch (Exception e) 
	    {
           System.err.println ("CORBA error in social groups  - " + e);
        }
	}
	  
		//MouseEvent me = null;
/////////////////////////////////////////////////////		
		void doMouseClicked(MouseEvent me ) {
        TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
	    //String reciever = tp.getLastPathComponent().toString();
        if (tp != null)
		{
		DefaultMutableTreeNode node =(DefaultMutableTreeNode)tp.getLastPathComponent( );
        if (node.isLeaf())
        {
	     if (me.getClickCount() == 1) { System.out.println(tp.getLastPathComponent());}
		 else if (me.getClickCount() == 2) {
		    String reciever = tp.getLastPathComponent().toString();
            System.out.println("Double" +reciever);
			JOptionPane.showMessageDialog(this, inputusername);
			//social_network_system.message nextform = new social_network_system.message(inputusername,reciever,groupname , orb);
			//test.message nextform = new test.message(inputusername,reciever,groupname , String message); 
		   // System.out.println("Double" );
         } 
	    }
	  }
	 // }
	
  }
 //////////////////////////////// CREATE GROUP /////////////////////////////// 
public void creategroup()
{
  String result ;
   createform = new creategroup (inputusername , orb);
  createform .setVisible(true);
 
  //social_network_system.creatform nextform = new social_network_system.creatform(inputusername,password, orb);
}
////////////////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent e) {  
       // scrollPane.getViewport().setView(getTree()); 
        java.lang.Object source=e.getSource();
                if(source==creatgroup) creategroup();
               		
    }  
   
    private JSplitPane getContent() {  
        JSplitPane splitPane =  
            new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  
                           getLeftComponent(),
                           getRightComponent());  
		/*SplitPane splitPane =  
            new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  
                           getLeftComponent(),  
                           getRightComponent()); 	*/			   
        splitPane.setDividerLocation(180);  
        return splitPane;  
    }  
   
    private JPanel getLeftComponent() {  
	    
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
   
    private JScrollPane getRightComponent(){  
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
   
    public static void main(String[] args)
	{  
      

    }  
}  