
import social_network_system.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;


public class CoordinatorSystem implements Runnable {

    Coordinator coordinator;
    String group;
    String groupPassword;
	String user;
    ORB orb;

    public CoordinatorSystem( String user ,String group, String groupPass)
    {
        this.user = user;
		this.group = group;
        this.groupPassword = groupPass;
    }

    public void run()
    {
         try
        {
            orb = ORB.init(new String[]{"-ORBInitialPort", "2200", "-ORBInitialHost", "localhost"}, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            CoordinatorImpl coordinatorImpl = new CoordinatorImpl(user , group, groupPassword);
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(coordinatorImpl);
            coordinator = CoordinatorHelper.narrow(ref);
            org.omg.CORBA.Object object = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef2 = NamingContextExtHelper.narrow(object);
            String name = group + "-coordinator";
            NameComponent path[] = ncRef2.to_name( name );
            ncRef2.rebind(path, coordinator);
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
            String name = group + "-coordinator";
            NameComponent path[] = ncRef2.to_name( name );
            ncRef2.unbind(path);
        }
        catch(Exception e)
        {

        }
        orb.shutdown(true);
    }
}
