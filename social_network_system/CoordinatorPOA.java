package social_network_system;


/**
* social_network_system/CoordinatorPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from coordinator.idl
* Tuesday, April 16, 2013 12:38:51 AM IRDT
*/

public abstract class CoordinatorPOA extends org.omg.PortableServer.Servant
 implements social_network_system.CoordinatorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("login", new java.lang.Integer (0));
    _methods.put ("join", new java.lang.Integer (1));
    _methods.put ("answer", new java.lang.Integer (2));
    _methods.put ("getcoordinator", new java.lang.Integer (3));
    _methods.put ("leave", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // social_network_system/Coordinator/login
       {
         String username = in.read_string ();
         String groupname = in.read_string ();
         String groupass = in.read_string ();
         String $result = null;
         $result = this.login (username, groupname, groupass);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // social_network_system/Coordinator/join
       {
         String name = in.read_string ();
         String username = in.read_string ();
         String groupname = in.read_string ();
         String $result = null;
         $result = this.join (name, username, groupname);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // social_network_system/Coordinator/answer
       {
         String username = in.read_string ();
         String newusername = in.read_string ();
         String groupname = in.read_string ();
         String answer = in.read_string ();
         String $result = null;
         $result = this.answer (username, newusername, groupname, answer);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // social_network_system/Coordinator/getcoordinator
       {
         String username = in.read_string ();
         String groupname = in.read_string ();
         String grouppass = in.read_string ();
         String $result = null;
         $result = this.getcoordinator (username, groupname, grouppass);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // social_network_system/Coordinator/leave
       {
         String username = in.read_string ();
         String groupname = in.read_string ();
         String grouppass = in.read_string ();
         String $result = null;
         $result = this.leave (username, groupname, grouppass);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:social_network_system/Coordinator:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Coordinator _this() 
  {
    return CoordinatorHelper.narrow(
    super._this_object());
  }

  public Coordinator _this(org.omg.CORBA.ORB orb) 
  {
    return CoordinatorHelper.narrow(
    super._this_object(orb));
  }


} // class CoordinatorPOA
