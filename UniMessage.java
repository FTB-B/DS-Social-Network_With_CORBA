

import social_network_system.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;


public class UniMessage extends javax.swing.JFrame
{
    String user;
    String group;
    String userPeer;
    DMessage dmsg;
	
	////////////////////////////
    private javax.swing.JButton sendbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea showmsg;
    private javax.swing.JTextArea typemsg;



    public UniMessage(String user, String userPeer, String group)
    {
        setLocation(250,330);     
		///////////////////////////////
		
		jScrollPane5 = new javax.swing.JScrollPane();
        typemsg = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        showmsg = new javax.swing.JTextArea();
        sendbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        typemsg.setColumns(20);
        typemsg.setFont(new java.awt.Font("Arial", 0, 12));
        typemsg.setLineWrap(true);
        typemsg.setRows(1);
        typemsg.setName("typeMessage"); 
        jScrollPane5.setViewportView(typemsg);
        typemsg.getAccessibleContext().setAccessibleName("");

        showmsg.setColumns(20);
        showmsg.setEditable(false);
        showmsg.setLineWrap(true);
        showmsg.setRows(8);
        jScrollPane1.setViewportView(showmsg);

        sendbtn.setLabel("Send");
        sendbtn.setMaximumSize(new java.awt.Dimension(73, 25));
        sendbtn.setMinimumSize(new java.awt.Dimension(73, 25));
        sendbtn.setPreferredSize(new java.awt.Dimension(73, 25));
        sendbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
		
		
		
		//////////////////////////////
        this.user = user;
        this.userPeer = userPeer;
        this.group = group;
		////////////////////////////////
		try
        {
            ORB orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            org.omg.CORBA.Object object = ncRef.resolve_str(group + "-" + userPeer);
            if(object != null)
            {
                dmsg = DMessageHelper.narrow(object);
            }
            else
            {
                showmsg.append("\nThe user is not a group member!\n");
            }

        }
        catch(Exception e)
        {
		  
		   System.err.println ("CORBA error - " + e);

        }
       
		////////////////////////////////////////
        this.setTitle(user +" chat with " + userPeer);
    }

    public void GetMessage(String message)
    {
        showmsg.append(message + "\n");
    }

    public String GetPeer()
    {
        return userPeer;
    }

 

    private void sendbtnActionPerformed(java.awt.event.ActionEvent evt) {
        if(!typemsg.getText().equals(""))
        {
            dmsg.GetUniMessage(typemsg.getText(), user);
            showmsg.append(user + ": " + typemsg.getText() + "\n");
        }
        typemsg.setText("");
    }

////////////////////////////////////////////////////
public String isonline(String reciever, String groupname)
{
   String binduser = "";
   String bindresult = "";
   try
    {
        ORB orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);
		org.omg.CORBA.Object object = orb.resolve_initial_references("NameService");
        NamingContextExt namingContext = NamingContextExtHelper.narrow(object);
		BindingListHolder bindingListHolder = new BindingListHolder();
        BindingIteratorHolder bindingIteratorHolder = new BindingIteratorHolder();
        namingContext.list(100, bindingListHolder, bindingIteratorHolder);
        Binding bindings[] = bindingListHolder.value;
		System.out.println(bindings + "\n");
        //org.omg.CORBA.Object object2 = null;
        String temp = "";
        //System.out.println(bindings.length.toString() + "\n");
        int i = 0;
	    for(i = 0; i < bindings.length; i++)
        {
			int lastIx = bindings[i].binding_name.length - 1;
			System.out.println( "Context: " + bindings[i].binding_name[lastIx].id);
			binduser =  bindings[i].binding_name[lastIx].id;
		    binduser = binduser.substring(3);
		    System.out.println( "bind user : " + binduser);
		    //bindusers[i] = bindings[i].binding_name[lastIx].id;
		    if(reciever.equals(binduser))
		    {
				   
				//JOptionPane.showMessageDialog(this, "The selected user in online and you can talk online  with him/her");
				bindresult = "online";
				//return ("online");
				break;
            }
	    }
	    if( i ==  bindings.length )
	    {
			//JOptionPane.showMessageDialog(this, "The selected user in offline and you can not talk  online with him/her");
			bindresult = "offline";
			return("offline");
	    }
		else 
		   return ("online");
    }
	catch( Exception e)
	{
	}
	return (bindresult);
}  

   

}
