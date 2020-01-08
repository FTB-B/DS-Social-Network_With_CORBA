package social_network_system;


/**
* social_network_system/CoordinatorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from coordinator.idl
* Tuesday, April 16, 2013 12:38:51 AM IRDT
*/

abstract public class CoordinatorHelper
{
  private static String  _id = "IDL:social_network_system/Coordinator:1.0";

  public static void insert (org.omg.CORBA.Any a, social_network_system.Coordinator that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static social_network_system.Coordinator extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (social_network_system.CoordinatorHelper.id (), "Coordinator");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static social_network_system.Coordinator read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CoordinatorStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, social_network_system.Coordinator value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static social_network_system.Coordinator narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof social_network_system.Coordinator)
      return (social_network_system.Coordinator)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      social_network_system._CoordinatorStub stub = new social_network_system._CoordinatorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static social_network_system.Coordinator unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof social_network_system.Coordinator)
      return (social_network_system.Coordinator)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      social_network_system._CoordinatorStub stub = new social_network_system._CoordinatorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
