
import social_network_system.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;


public class Server implements  Runnable {
    
    DMessage dmsg;
    String user;
    String group;
    ORB orb;
    
    public Server(String user, String group)
    {
        super();
        this.user = user;
        this.group = group;
       
    }

    public void run()
    {
        try
        {
          orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);
          POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
          rootpoa.the_POAManager().activate();
          DMessageImpl dmsgImpl = new DMessageImpl(user, group);
          org.omg.CORBA.Object object = rootpoa.servant_to_reference(dmsgImpl);
          dmsg = DMessageHelper.narrow(object);
          org.omg.CORBA.Object object2 = orb.resolve_initial_references("NameService");
          NamingContextExt namingContext = NamingContextExtHelper.narrow(object2);
          NameComponent path[] = namingContext.to_name(group + "-" + user);
          namingContext.rebind(path, dmsg);
          orb.run();

        }
        catch (Exception e)
        {
		  System.err.println ("CORBA error - " + e);
            
        }
    }

    public void stop()
    {

        try
        {
            org.omg.CORBA.Object object2 =   orb.resolve_initial_references("NameService");
            NamingContextExt namingContext = NamingContextExtHelper.narrow(object2);
            NameComponent path[] = namingContext.to_name(group + "-" + user);
            namingContext.unbind(path);
        }
        catch(Exception e)
        {
		 System.err.println ("CORBA error - " + e);

        }
       // orb.shutdown(true);
    }


}
