/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import social_network_system.*;
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
import java.awt.*;  
import java.awt.event.*;  
import java.util.*;  
import javax.swing.*;  
import javax.swing.tree.*;
import java.io.*;
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

/**
 *
 * 
 */
public class Social_NetworkImpl extends Social_NetworkPOA {
    String group;
    String groupPassword;
	String user;
	String username;
	String result = "";
	String gsearch = "";
	String usearch = "";
    public Social_NetworkImpl(String user ,String username)
    {        
        this.user = user;
		this.username = username;
       // this.groupPassword = groupPass;
    }
/////////////////////////////////////////////////////////////////////////
   public String sregister(String user , String username  )
	{
       String result = "" ;
	   String inputname=user;
	   //String inputpassword=new String(tnwpass.getPassword());
	   //String groupname = "";
	   String inputusername= username;
	   System.out.println(inputname);
	   System.out.println(inputusername);
	   //System.out.println(inputpassword);
	
	
	   System.out.println("enter registration");
	   Connection conn = null;
       String driver;
	   String groupname = "nothing";
	   String password = "nothing";
	   ResultSet res = null;;
       //String protocol;
       driver = "org.apache.derby.jdbc.ClientDriver";
	   PreparedStatement ps = null;
	
	   try 
	    {
		 //System.out.println(string driver);
          Class.forName(driver);
		 //Class.forName(driver);
         //System.out.println("driver created");
        } 
	   catch (ClassNotFoundException e1)
	    {
          e1.printStackTrace();
        }
	   //return (name + username + password + groupname);
	   //return ("new drive created");
	   try
	    {
	      // protocol = "jdbc:derby:";
	      conn = DriverManager.getConnection("jdbc:derby:C:/Apache/db-derby-10.9.1.0-bin/bin/socialnet/DB/socialnetworkdb;", "","");
		  System.out.println("data base connection established");
	    
		}
		catch(SQLException ex)
		{
	      System.err.println("can not connect to database server");
	     //return("registration error");
		}
		StringBuilder query = new StringBuilder() ;
		StringBuilder users = new StringBuilder() ;
		StringBuilder groups = new StringBuilder() ;
		String p = "";
		String r = "";
		try
		{
		    ps = conn.prepareStatement("insert into member values(?,?,?,?)");
            ps.setString(1, inputname);
            ps.setString(2, inputusername);
            ps.setString(3, password);
			ps.setString(4, groupname);
            
			
            int rst = ps.executeUpdate();

            if(rst>0)
            {
                System.out.println("Data Inserted");
				String q = "SELECT * FROM groups";
				groups.append("ok").append(",");
				try
				{
				  Statement s = conn.createStatement();
                  res = s.executeQuery(q);
				  while(res.next())
				  {
				    groups.append(res.getString("name")).append(",");
				  }
				}
				catch(Exception e)
                {
                 e.printStackTrace();
				 System.out.println("wrong");
			     return("wrong");
				 
		   
		        }
				p = groups.toString();
				System.out.println(p);
				return(p);
            }
            else
            {
                System.out.println("Something happened");
				System.out.println("nok");
				return("nok");
				
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
			System.out.println("wrong");
			return("wrong");
			
		   
		}
		finally 
		{
            try { res.close();  if (res.isClosed()) System.out.println("splittree res closed."); }catch (Exception e) { /* ignored */ }
            try { ps.close();  if (ps.isClosed()) System.out.println("split tree ps closed."); } catch (Exception e) { /* ignored */ }
            try { conn.close(); if (conn.isClosed()) System.out.println("split tree conn closed.");} catch (Exception e) { /* ignored */ }		  
		}
			//String r;
		
		
	
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
public String Gcreate(String username , String password , String groupname)
{
      // String username ;
	   this.username = username;
	   String gpassword = password;
	   String gname = groupname ;
	   
	  
	   
	   
	   int count = 0;
	   String result = "";
	   Connection conn = null;
       String driver = "org.apache.derby.jdbc.ClientDriver";
       ResultSet res = null;
	   ResultSet res2 = null;
	   PreparedStatement ps = null;
	   PreparedStatement ps2 = null;
	   int rst = 0;
	   int rst2 = 0;
	   int rslt = 1;
	   
	   try 
		{
          Class.forName(driver);
        } 
	    catch (ClassNotFoundException e1)
		{
         e1.printStackTrace();
		 System.out.println("not create new class");
        }
	    try
		{
	    // protocol = "jdbc:derby:";
	     conn = DriverManager.getConnection("jdbc:derby:C:/Apache/db-derby-10.9.1.0-bin/bin/socialnet/DB/socialnetworkdb;", "","");
		 System.out.println("data base connection established");
		 System.out.println(conn);
		             
		//return("registration complete");
		}
		catch(SQLException ex)
		{
	     System.err.println("can not connect to database server");
		 System.err.println("can not connect to database server");
		 System.out.println(conn);
	     //return("registration error");
		}
		StringBuilder query1 = new StringBuilder() ;
		StringBuilder query2 = new StringBuilder() ;
		StringBuilder query5 = new StringBuilder() ;
		query1.append("SELECT * FROM groups");
		 String q1 = query1.toString();
		 query2.append("create table  ").append(gname).append(" (username VARCHAR(20))");
		 String q2;
		 q2 = query2.toString();
		 query5.append("insert into groups values(?,?,?)");
		 String q5 = query5.toString();
		 
		 try
			{
				System.out.println(q1);
				Statement s1 = conn.createStatement(); 
				res = s1.executeQuery(q1);
				while(res.next())
				   {
				     
			          //count ++;
					 if((groupname.equals(res.getString("name"))))
                    {
                      System.out.println("group already  exist");
					  gsearch = "ok";
					  break;
					  
				      
                    }
				   }
				if(!(gsearch.equals("ok")))
                   {
                     System.out.println("group not found");
					 try
					 {
					  System.out.println(q2);
					  Statement s2 = conn.createStatement(); 
				      rslt = s2.executeUpdate(q2);
					  if(rslt == 0)
					  {
					     System.out.println("new group create");
						 try
						 {
						  System.out.println(q5);
						  //Statement s5 = conn.createStatement(); 
				          //res = s5.executeQuery(q5);
						  ps2 = conn.prepareStatement(q5);
                          ps2.setString(1, gname);
						  ps2.setString(2, username);
						  ps2.setString(3, password);
						  
						  rst2 = ps2.executeUpdate();
						  
						 
						 if(rst2>0)
						 {
						    result = "ok";
							System.out.println("insert new group to groups table");
							return (result);
						 }
						 }
						 catch(Exception e)
				         {
				         e.printStackTrace();
						 result = "query 5 wrong dont insert into groups";
						 return (result);
			            // return("query 5 wrong dont insert into groups");
				         }
					  }
					 }
					 catch(Exception e)
				     {
				      e.printStackTrace();
					  result = "query 2 wrong new table not create";
			          //return("query 2 wrong new table not create");
					  return result;
				     }
			        } //end of if (!(gsearch.equa
			}	
			catch(Exception e)
			{
				e.printStackTrace();
				result = "wrong";
				return (result);
			    //return("wrong");
					
					//return false;
			}
			finally 
			{
               
			 try { res.close();  if (res.isClosed()) System.out.println("log res closed."); } catch (Exception e) { /* ignored */ }
             try { ps.close();  if (ps.isClosed())  System.out.println("log ps closed."); } catch (Exception e) { /* ignored */ }
             try { conn.close(); if (conn.isClosed()) System.out.println(" log Connection closed."); } catch (Exception e) { /* ignored */ }		  
		    }
  
  
  return(result);
}
}

