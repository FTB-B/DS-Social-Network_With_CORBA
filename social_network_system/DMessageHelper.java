package social_network_system;


/**
* social_network_system/DMessageHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DMessage.idl
* Sunday, April 7, 2013 10:29:37 PM IRDT
*/

abstract public class DMessageHelper
{
  private static String  _id = "IDL:social_network_system/DMessage:1.0";

  public static void insert (org.omg.CORBA.Any a, social_network_system.DMessage that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static social_network_system.DMessage extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (social_network_system.DMessageHelper.id (), "DMessage");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static social_network_system.DMessage read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_DMessageStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, social_network_system.DMessage value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static social_network_system.DMessage narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof social_network_system.DMessage)
      return (social_network_system.DMessage)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      social_network_system._DMessageStub stub = new social_network_system._DMessageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static social_network_system.DMessage unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof social_network_system.DMessage)
      return (social_network_system.DMessage)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      social_network_system._DMessageStub stub = new social_network_system._DMessageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
