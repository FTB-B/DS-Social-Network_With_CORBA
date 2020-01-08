

import social_network_system.*;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.swing.JButton;
import javax.swing.ListModel;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.util.*;


public class GroupMessage extends javax.swing.JFrame {
    String user;
    String group;
    ArrayList<DMessage> dmsgs;
	String[] bindusers;
	
	
	private javax.swing.JButton sendbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea typemsg;
    private javax.swing.JTextArea showmsg;


    
     public GroupMessage(String user, String group)
     {
        
		/////////////////////////////////
		
		setLocation(250,330);
		
		jScrollPane1 = new javax.swing.JScrollPane();
        typemsg = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        showmsg = new javax.swing.JTextArea();
        sendbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        typemsg.setColumns(20);
        typemsg.setEditable(false);
        typemsg.setLineWrap(true);
        typemsg.setRows(8);
        jScrollPane1.setViewportView(typemsg);

        showmsg.setColumns(20);
        showmsg.setLineWrap(true);
        showmsg.setRows(2);
        jScrollPane2.setViewportView(showmsg);

        sendbtn.setText("Send");
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sendbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
		
		
		pack();
		//////////////////////////////////
		
		
        this.user = user;
        this.group = group;
        this.dmsgs = new ArrayList<DMessage>();
        this.setTitle(user + " want to chat with Group");
		
		/////////////////////////////////////
		
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
            org.omg.CORBA.Object object2 = null;
            String temp = "";
			//System.out.println(bindings.length.toString() + "\n");
            for(int i = 0; i < bindings.length; i++)
            {
                object2 = null;
                int lastIx = bindings[i].binding_name.length - 1;
				//System.out.println( "Context: " + bindings[i].binding_name[lastIx].id);
				//bindusers[i] = bindings[i].binding_name[lastIx].id;
                if(bindings[i].binding_name[lastIx].id.contains(group))
                {
                    try
                    {
                        temp = bindings[i].binding_name[lastIx].id.substring(group.length() + 1);
                        object2 = namingContext.resolve_str(group + "-" + temp);
                    }
                    catch(Exception e)
                    {
					
					   System.err.println ("Binding  error - " + e);

                    }
                    if(!(temp.equals("coordinator") || temp.equals(user) || object2 == null))
                        dmsgs.add(DMessageHelper.narrow(object2));
                }
            }
        }
        catch(Exception e)
        {
		  
		   System.err.println ("CORBA error - " + e);

        }
		
		///////////////////////////////////
    }

     public void GetMessage(String message)
    {
        typemsg.append(message + "\n");
    }

     public String GetGroup()
    {
        return group;
    }
     
  




    private void sendbtnActionPerformed(java.awt.event.ActionEvent evt) 
	{
        if(!showmsg.getText().equals(""))
        {
            int n = dmsgs.size();
            int i;
            for(i = 0; i < n; i++)
                dmsgs.get(i).GetGroupMessage(showmsg.getText(), user); 
            typemsg.append(user + ": " + showmsg.getText() + "\n");
        }
        showmsg.setText("");
    }

 

    

}
