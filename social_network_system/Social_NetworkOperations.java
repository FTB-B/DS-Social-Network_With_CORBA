package social_network_system;


/**
* social_network_system/Social_NetworkOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Social_Network.idl
* Saturday, March 30, 2013 1:35:55 AM IRDT
*/

public interface Social_NetworkOperations 
{

  //string login(in string username ,in string groupname , in string groupass);
  String sregister (String user, String username);
  String Gcreate (String username, String password, String groupname);
} // interface Social_NetworkOperations
