/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import social_network_system.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

/**
 *
 * 
 */
public class Social_NetworkSystem implements Runnable {

    Social_Network social_network;
    String user;
    String username;
	//String user;
    ORB orb;

    public Social_NetworkSystem( String user , String username)
    {
        this.user = user;
		this.username = username;
        //this.groupPassword = groupPass;
    }

    public void run()
    {
         try
        {
            orb = ORB.init(new String[]{"-ORBInitialPort", "5100", "-ORBInitialHost", "localhost"}, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            Social_NetworkImpl social_networkImpl = new Social_NetworkImpl(user , username);
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(social_networkImpl);
            social_network = Social_NetworkHelper.narrow(ref);
            org.omg.CORBA.Object object = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef2 = NamingContextExtHelper.narrow(object);
            String name = "Social_Network";
            NameComponent path[] = ncRef2.to_name( name );
            ncRef2.rebind(path, social_network);
            orb.run();
        }
        catch (Exception e)
        {

        }
    }

    public void Stop()
    {
        try
        {
            org.omg.CORBA.Object object = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef2 = NamingContextExtHelper.narrow(object);
            String name = "Social_Network";
            NameComponent path[] = ncRef2.to_name( name );
            ncRef2.unbind(path);
        }
        catch(Exception e)
        {

        }
        orb.shutdown(true);
    }
}
