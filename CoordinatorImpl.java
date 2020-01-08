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
public class CoordinatorImpl extends CoordinatorPOA {
    String group;
    String groupPassword;
	String user;
	String result = "";
	String gsearch = "";
	String usearch = "";
      public CoordinatorImpl(String user ,String group, String groupPass)
    {        
        this.user = user;
		this.group = group;
        this.groupPassword = groupPass;
    }
////////////////////////////////////  login /////////////////////////////////////////////
    public String login (String username , String groupname, String groupass)
	{
        int count = 0;
	    String result = "";
	    Connection conn = null;
        String driver = "org.apache.derby.jdbc.ClientDriver";
	    String cname = "";
	    String csearch = "";
	    String newuser = "";
	    String checks = "";
	    String finresult = "";
	    String newusers = "";
	    String answer1 = "";
	    String answer = "";
	    String newanswer = "";
	    String n = "";
	    boolean finanswer = true ;
        ResultSet res1 = null;
	    ResultSet res2 = null;
		ResultSet res4 = null;
		ResultSet res6 = null;
		ResultSet res7 = null;
	    ResultSet res8 = null;
	    ResultSet rs9 = null;
	    ResultSet rs10 = null;
	    ResultSet res11 = null;
	    ResultSet rs12 = null;
	    ResultSet rs13 = null;
	    ResultSet rs14 = null;
	    PreparedStatement ps = null;
	    PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
	    PreparedStatement ps8 = null;
	    PreparedStatement ps9 = null;
	    PreparedStatement ps10 = null;
	    PreparedStatement ps11 = null;
	    PreparedStatement ps12 = null;
	    PreparedStatement ps13 = null;
	    PreparedStatement ps14 = null;
	    int rst = 0;
	    int rst2 = 0;
		int rst3 = 0;
	    int rst8 = 0;
	    int rst9 = 0;
	    int rst10 = 0;
	    int rst12 = 0;
	    int rst13 = 0;
	    int rst14 = 0;
	    int rslt = 1;
		int rslt15 = 1;
		if(this.group.equals(groupname) && this.groupPassword.equals(groupass))
		{
           
			result = "ok";
		}
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
		             
		    
		}
		catch(SQLException ex)
		{
	        System.err.println("can not connect to database server");
		    System.err.println("can not connect to database server");
		    System.out.println(conn);
	       
		}
		StringBuilder query1 = new StringBuilder() ;
		StringBuilder query2 = new StringBuilder() ;
		StringBuilder query3 = new StringBuilder() ;
		StringBuilder query4 = new StringBuilder() ;
		StringBuilder query5 = new StringBuilder() ;
		StringBuilder query6 = new StringBuilder() ;
		StringBuilder query7 = new StringBuilder() ;
		StringBuilder query8 = new StringBuilder() ;
		StringBuilder query9 = new StringBuilder() ;
		StringBuilder query10 = new StringBuilder() ;
		StringBuilder query11 = new StringBuilder() ;
		StringBuilder query12 = new StringBuilder() ;
		StringBuilder query13 = new StringBuilder() ;
		StringBuilder query14 = new StringBuilder() ;
		StringBuilder query15 = new StringBuilder() ;
		StringBuilder finalresult = new StringBuilder() ;
		StringBuilder nusers = new StringBuilder() ;
		StringBuilder users = new StringBuilder() ;
		StringBuilder rusers = new StringBuilder() ;
		String q2 ;
		String r;
		String p;
		query1.append("SELECT * FROM groups");
		String q1 = query1.toString();
		query2.append("create table  ").append(groupname).append(" (username VARCHAR(20))");
		q2 = query2.toString();
		//query3.append("insert into ").append(groupname).append(" values(").append("'").append(username).append("'").append(")");
		query3.append("insert into ").append(groupname).append("  values(?)");
		String q3 = query3.toString();
		query4.append("SELECT * FROM ").append(groupname);
		// query5.append("INSERT INTO groups VALUES(").append(groupname).append (",").append(username).append(")");
		query5.append("insert into groups values(?,?,?)");
		String q5 = query5.toString();
		query6.append("select * from ").append(groupname);
		String q6 = query6.toString();
		String q4 = query4.toString();
		query7.append("select * from request where gname = '").append(groupname).append("'");
		String q7 = query7.toString();
		String q9;
		String q10;
		String q11;
		String q12;
		String q13;
		String q14;
		String q15;
		String q8;
		 
		//System.out.println(q1);
		try
		{
			//select * from groups
			System.out.println(q1);
			Statement s1 = conn.createStatement(); 
			res1 = s1.executeQuery(q1);
			while(res1.next())
			{
				     
			    //count ++;
				if((groupname.equals(res1.getString("name"))))
                {
                    System.out.println("group already  exist");
					cname = (res1.getString("coordinatorname"));
					if(username.equals(cname)) csearch = "ok";
					query8.append("select * from ").append(groupname).append(".").append(cname);
					gsearch = "ok";
					break;
					  
				      
                }
			}
			if(!(gsearch.equals("ok")))
            {
                System.out.println("group not found");
				try
				{
					//create new  group table
					System.out.println(q2);
					Statement s2 = conn.createStatement(); 
				    rslt = s2.executeUpdate(q2);
					if(rslt == 0)
					{
					    System.out.println("new group create");
						try
						{
						    //insert into groups
						    System.out.println(q5);
						    //Statement s5 = conn.createStatement(); 
				            //res = s5.executeQuery(q5);
						    ps2 = conn.prepareStatement(q5);
                            ps2.setString(1, groupname);
						    ps2.setString(2, username);
						    ps2.setString(3,groupass);
						  
						    rst2 = ps2.executeUpdate();
						 
						    if(rst2>0)
						    {
						        System.out.println("insert new group to groups table");
						    }
						}
						catch(Exception e)
				        {
				            e.printStackTrace();
			                return("query 5 wrong dont insert into groups");
				        }
						try
						{
						    query15.append("create table ").append(groupname).append(".").append(username).append("(euser VARCHAR(20) ,newuser VARCHAR(20) ,answer VARCHAR(20)");
						    q15 = query15.toString();
						    System.out.println(q15);
					        Statement s15 = conn.createStatement(); 
				            rslt15 = s15.executeUpdate(q15);
						    if(rslt15 == 0)
					        {
					            System.out.println("new group for answer request create");
								return ("new group ");
							}	
						}
						catch(Exception e)
				        {
				            e.printStackTrace();
			                return("query 15 wrong dont table gname.cname create ");
				        }
					}
				}
				catch(Exception e)
				{
				    e.printStackTrace();
			        return("query 2 wrong new table not create");
				}
			} //end of if (!(gsearch.equals("ok")))
						
               
			try
		    {
				//select * from group table
			    Statement s6 = conn.createStatement();
				res6 = s6.executeQuery(q6);
			    while(res6.next())
				{
				     
			        //count ++;
					if((username.equals(res6.getString("username"))))
                    {
                        System.out.println("user already  exist in this group");
					    usearch = "ok";
					    break;		      
                    }
				}
				if(usearch.equals("ok"))
				{
				    //return("user already exist");
					try
					{
					    //select * from request where gname = groupname
						System.out.println(q7);
						Statement s7 = conn.createStatement();
						res7 = s7.executeQuery(q7);
				        while(res7.next())
				        {
				     
			                //count ++;
					        if(((res7.getString("answer")).equals("?")))
                            {
                                newuser = res7.getString("newuser");
							    System.out.println(newuser);
							    //select * from gname.cname where newuser = ? AND euser = ?
							    query8.append(" where newuser = ? AND euser = ?");
							    q8 = query8.toString();
							    try
							    {
							        //select * from group.cname where newuser = newuser and euser = username
								    System.out.println(q8);
				                 
								    //Statement s8 = conn.createStatement(); 
				                    //res = s8.executeQuery(q8);
								 
								    ps8 = conn.prepareStatement(q8);
                                    ps8.setString(1, newuser);
								    ps8.setString(2, username);
								    res8 = ps8.executeQuery();
						         
                                    if(!(res8.next())) 
								    {
								   
								        System.out.println("res8.next false");
								        nusers.append(newuser).append(",");
								   
								    }
								    else 
								    {
								        nusers.append("").append(",");
								        System.out.println("enter else user already answer to this new user");
								  
								
								    }
							    }
							   
							    catch(Exception e)
				                {
				                    e.printStackTrace();
			                        return("query 8 wrong check gname.cname table for user answer ");
				                }
							   
					       
				      
                            }
							/*else
							{
							    nusers.append("").append(",");
							}*/
				        }
						
					}
					catch(Exception e)
				    {
				        e.printStackTrace();
			            return("query 7 wrong check request table ");
				    }
					System.out.println(username);
					System.out.println(cname);
					  
					if(username.equals(cname))
					{
					    System.out.println("username == cname");
						//check konad ke aya hame javab dadeand
						query9.append("select count(*) from ").append(groupname).append(".").append(cname).append(" where newuser = ?");
		                query10.append("select count(*) from ").append(groupname);
						q9 = query9.toString();
						q10 = query10.toString();
						try
						{
						    System.out.println(q9);
						    System.out.println(newuser);
						    ps9 = conn.prepareStatement(q9);
                            ps9.setString(1, newuser);
						    rs9 = ps9.executeQuery();
						    rs9.next();
						    rst9 = rs9.getInt(1);
						} 
						catch(Exception e)
				        {
				            e.printStackTrace();
			                return("query 9 wrong count(*) from groupname.cname table where newuser = newuser");
				        }
						try
						{
						    System.out.println(q10);
						    ps10 = conn.prepareStatement(q10);
                            //ps9.setString(1, newuser);
						    rs10 = ps10.executeQuery(); 
						    rs10.next();
						    rst10 = rs10.getInt(1);
						}
						catch(Exception e)
						{
						    e.printStackTrace();
			                return("query 10 wrong count(*) from groupname table ");
						}
						System.out.println(rst9);
						System.out.println(rst10);
						if(rst9 == rst10)
						{
						    System.out.println("rst9 == rst10");
						    query11.append("select * from ").append(groupname).append(".").append(cname).append(" where newuser = ?");
						    q11 = query11.toString();
						    try
						    {
                                System.out.println(q11);						    
							    //select * from gname.cname where newuser 
							    ps11 = conn.prepareStatement(q11);
                                ps11.setString(1, newuser);
						        res11 = ps11.executeQuery();
							    while(res11.next())
							    {
							        answer = res11.getString("answer");
								    System.out.println(answer);
								    if(answer.equals("ok"))
								    {
								        System.out.println("answer = ok ");
								        finanswer = (finanswer && true);
								        System.out.println(finanswer);
								    }
								    else 
								    {
								        finanswer = (finanswer && false) ;
								        answer1 = "nok";
								        System.out.println(finanswer);
								        break;
								    }
							    }
							    if(finanswer)
							    {
							        System.out.println(finanswer);
								    answer1 = "ok";
							    }
                                else
								{
								    answer1 = "nok";
								}	
								System.out.println(answer1);
								query12.append("update request set answer = ? where newuser = ? AND gname = ?");
								q12 = query12.toString();
								try
								{
								    System.out.println(answer1);
								    //update request table 
								    System.out.println(q12);
						            ps12 = conn.prepareStatement(q12);
                                    ps12.setString(1, answer1);
						            ps12.setString(2, newuser); 
						            ps12.setString(3, groupname);
								    rst12 = ps12.executeUpdate();
								    if(rst12 > 0)
								    {
								        System.out.println("update request table  is done");
									    //return(result + answer1 +"done");
									    /*query14.append("delete from ").append(groupname).append(".").append(cname).append("where newuser = ?");
									    q14 = query14.toString();
									    try
									    {
									        System.out.println(q14);
						                    ps14 = conn.prepareStatement(q14);
                                            ps14.setString(1, newuser); 
						                    rst14 = ps14.executeUpdate();
									        if(rst14 == rst10)
									        {
									            System.out.println("delete all othe users answer to membership of a new user");
									        }
									  
									    }
									    catch(Exception e)
									    {
									        e.printStackTrace();
			                                return("query 14 error delete user answers from gname.cname table");
									    }*/
									
								    }
								    if(answer1.equals("ok"))
								    {
								        //add new user to group table
									    try
						                {
						                    System.out.println(q3);
											System.out.println(newuser);
						                    //Statement s3 = conn.createStatement(); 
				                            //res = s3.executeQuery(q3);
						                    ps3 = conn.prepareStatement(q3);
                                            ps3.setString(1, newuser);
						                    rst3 = ps3.executeUpdate();
						                    if(rst3 >0)
						                    {
						                        System.out.println("insert new user  into group table");
						                    } 
						       
						                }
				                        catch(Exception e)
				                        {
				                            e.printStackTrace();
			                                return("query 3 wrong does not insert new user into group table");
				                        }
								    }
								 
								   
								}
								catch(Exception e)
				                {
				                    e.printStackTrace();
			                        return("query 12 update request table with answer");
				                }
							 
						    }
						   catch(Exception e)
				            {
				                e.printStackTrace();
			                    return("query 11 select * from gname.cname table");
				            }
						  
						   
						}
						  
				    }
						
						 
						 
			    }
					  
					  
					
			    else
				{
					//return("new user");
					System.out.println("new user");
					n = "new user";
					query13.append("select * from request where gname = ? AND newuser = ?");
					//query13.append("select * from request where gname = '").append(groupname).append("'");
					//query13.append("select * from request");
					q13 = query13.toString();
					try
					{
					    System.out.println(q13);
						// System.out.println(groupname);
						//System.out.println(username);
						ps13 = conn.prepareStatement(q13);
                        ps13.setString(1, groupname);
						ps13.setString(2, username); 
						//Statement s13 = conn.createStatement();
					    rs13 = ps13.executeQuery();
						//String nuser = rs13.getString("newuser");
						//System.out.println(nuser);
						
						//rs13 = ps13.executeQuery();
						if(!(rs13.next()))
						{
						    System.out.println("new user without answer and join request");
							return ("new user");
						}
						while(rs13.next())
						{
						    
							System.out.println(rs13.getString("newuser"));
							System.out.println(rs13.getString("gname"));
							if((username.equals(rs13.getString("newuser"))) && (groupname.equals(rs13.getString("gname"))))
						    {
						  
						        newanswer = rs13.getString("answer");
						    	 
							    if(newanswer.equals("ok"))
						        {
						            try
						            {
						                System.out.println(q3);
						                //Statement s3 = conn.createStatement(); 
				                        //res = s3.executeQuery(q3);
						                ps = conn.prepareStatement(q3);
                                        ps.setString(1, username);
						                rst = ps.executeUpdate();
						                if(rst>0)
						                {
						                    System.out.println("insert new user  into group table");
						                }  
						       
						            }
				                    catch(Exception e)
				                    {
				                        e.printStackTrace();
			                            return("query 3 wrong does not insert new user into group table");
				                    }
							        // hazf kardane javabha ye userha az gname.cname table
							       /*try
							        {
							  
							        }*/
							
						        }
						
						        else 
						        {
						            return("don't accept membership");
						        }
					        }
					    }
					}
					catch(Exception e)
					{
					    e.printStackTrace();
			            return("query 13 wrong does not select * from requset where gname = groupname and newuser = newuser");
					}
					   
			    }
		    }
			catch(Exception e)
		    {
				e.printStackTrace();
			    return("query 6 wrong check the existence of user");
		    }
				
				  
		    try
            {
			    //select * from group table
			    System.out.println(q4);
			    Statement s4 = conn.createStatement(); 
				res4 = s4.executeQuery(q4);
			    while(res4.next())
			    {
					users.append(res4.getString("username")).append(",");
				    //rusers.append(result).append(",");
				}
					rusers.append(result).append(",");
					p = users.toString();
					rusers.append(p);
					newusers = nusers.toString();
					System.out.println(newusers);
					if(newusers.equals(""))
					{
					   nusers.append("").append(",");
					   newusers = nusers.toString();
					}
					  
					r = rusers.toString();
					finalresult.append(r).append(":").append(newusers);
					finresult = finalresult.toString();
					System.out.println(finresult);
					return(finresult);
            }
		    catch(Exception e)
		    {
				e.printStackTrace();
			    return("query 4 wrong select users from group table");
		    }
                    					
        }					
				   
				  
				
	    catch(Exception e)
		{
		    e.printStackTrace();
			return("wrong");
					
			//return false;
	    }
		finally 
		{
               
			try { res4.close();  if (res4.isClosed()) System.out.println("log res closed."); } catch (Exception e) { /* ignored */ }
            try { ps.close();  if (ps.isClosed())  System.out.println("log ps closed."); } catch (Exception e) { /* ignored */ }
            try { conn.close(); if (conn.isClosed()) System.out.println(" log Connection closed."); } catch (Exception e) { /* ignored */ }		  
		}
        //return ("nok");
    }
	
///////////////////////////////// JOIN //////////////////////////////////////////////////////////
public String join(String name , String username , String groupname  )
{ 
  
        int count = 0;
	    String result = "";
	    String cname = "";
	    Connection conn = null;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        ResultSet res = null;
	    ResultSet res2 = null;
	    PreparedStatement ps = null;
	    PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
	    int rst = 0;
	    int rst2 = 0;
		int rst3 = 0;
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
		             
		}
		catch(SQLException ex)
		{
	        System.err.println("can not connect to database server");
		    System.err.println("can not connect to database server");
		    System.out.println(conn);
	     
		}
		
		StringBuilder query1 = new StringBuilder() ;
		StringBuilder query2 = new StringBuilder() ;
		StringBuilder query3 = new StringBuilder() ;
		StringBuilder query4 = new StringBuilder() ;
		StringBuilder query5 = new StringBuilder() ;
		StringBuilder query6 = new StringBuilder() ;
		StringBuilder users = new StringBuilder() ;
		StringBuilder rusers = new StringBuilder() ;
		String q2 ;
		String r;
		String p;
		query1.append("SELECT * FROM groups");
		String q1 = query1.toString();
		query2.append("create table  ").append(groupname).append(" (username VARCHAR(20))");
		q2 = query2.toString();
		query3.append("insert into request ").append(" values(? ,? , ?)");
		String q3 = query3.toString();
		query4.append("SELECT * FROM ").append(groupname);
		query5.append("insert into groups values(?,?)");
		String q5 = query5.toString();
		query6.append("select * from ").append(groupname);
		String q6 = query6.toString();
		String q4 = query4.toString();
		//System.out.println(q1);
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
					cname = (res.getString("coordinatorname"));
					break;
					  
				      
                }
		    }
				
			try
			{
				//insert into request values(?,?,?)
				System.out.println(q3);
				//Statement s3 = conn.createStatement(); 
				//res = s3.executeQuery(q3);
				ps3 = conn.prepareStatement(q3);
                ps3.setString(1, groupname);
				ps3.setString(2, username); 
				ps3.setString(3, "?");
			    rst3 = ps3.executeUpdate();
				if(rst3 > 0)
				{
				    System.out.println("insert new requset for new user   into request  table ,  send request to all group users");
					result = "ok";
					return (result);
				} 
						  
			}
		    catch(Exception e)
		    {
				e.printStackTrace();
			    return("query 3 wrong does not insert new request  into request  table");
		    }
					                  	
				  
				
				
                    					
        }					
				   
				  
				
	    catch(Exception e)
		{
		    e.printStackTrace();
			return("wrong");
					
			//return false;
		}
		finally 
		{
               
			try { res.close();  if (res.isClosed()) System.out.println("log res closed."); } catch (Exception e) { /* ignored */ }
            try { ps.close();  if (ps.isClosed())  System.out.println("log ps closed."); } catch (Exception e) { /* ignored */ }
            try { conn.close(); if (conn.isClosed()) System.out.println(" log Connection closed."); } catch (Exception e) { /* ignored */ }		  
		}
        return (result);
}	

//////////////////////////////////////// ANSWER //////////////////////////////////////////////////////
public String  answer(String username , String newusername , String groupname , String answer)
    {
        int count = 0;
	   String result = "";
	   String cname = "";
	   String answer1 = "";
	   boolean  finanswer = true;
	   Connection conn = null;
       String driver = "org.apache.derby.jdbc.ClientDriver";
       ResultSet res = null;
	   ResultSet res2 = null;
	   ResultSet res3 = null;
	   ResultSet rs9 = null;
	   ResultSet rs10 = null;
	   ResultSet res11 = null;
	   PreparedStatement ps = null;
	   PreparedStatement ps2 = null;
	   PreparedStatement ps3 = null;
	   PreparedStatement ps4 = null;
	   PreparedStatement ps5 = null;
	   PreparedStatement ps6 = null;
	   PreparedStatement ps9 = null;
	   PreparedStatement ps10 = null;
	   PreparedStatement ps11 = null;
	   PreparedStatement ps12 = null;
	   int rst = 0;
	   int rst2 = 0;
	   int rst3 = 0;
	   int rst4 = 0;
	   int rst9 = 0;
	   int rst10 = 0;
	   int rst12 = 0;
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
		 StringBuilder query3 = new StringBuilder() ;
		 StringBuilder query4 = new StringBuilder() ;
		 StringBuilder query5 = new StringBuilder() ;
		 StringBuilder query6 = new StringBuilder() ;
		 StringBuilder query9 = new StringBuilder() ;
		 StringBuilder query10 = new StringBuilder() ;
		 StringBuilder query11 = new StringBuilder() ;
		 StringBuilder query12 = new StringBuilder() ;
		 StringBuilder users = new StringBuilder() ;
		 StringBuilder rusers = new StringBuilder() ;
		 String q2 ;
		 String r;
		 String p;
		 query1.append("SELECT coordinatorname  FROM groups where name = ? ");
		 String q1 = query1.toString();
		 query2.append("select count(*) from ").append(groupname);
		 q2 = query2.toString();
		 //query3.append("select count(*) from ").append(groupname).append(".").append(cname).append("where newuser = ?");
		 query3.append("insert into ").append(groupname).append(" values(?)");
		 String q3 = query3.toString();
		 //query4.append("select * from ").append(groupname).append(".").append(cname).append("where newuser = ?");
	   	 
		// query6.append("update request set answer = " ).append(answer1).append("where newuser = ").append(newusername).append("AND gname = ").append(groupname);
		 query6.append("update request set answer = ? where newuser = ? AND gname = ?");
		 String q6 = query6.toString();
		 String q4 = query4.toString();
		 String q9;
		 String q10;
		 String q11;
		 String q12;
		 
		 System.out.println(answer);
		 try
			{
				System.out.println(q1);
				ps2 = conn.prepareStatement(q1);
                ps2.setString(1, groupname);
				res = ps2.executeQuery();
				while(res.next())
				    {
				       cname = res.getString("coordinatorname");
					   
                       System.out.println(cname);
			
				    }
				System.out.println(cname);
				query5.append("insert into  ").append(groupname).append(".").append(cname).append(" values (?, ? ,?)");
		        String q5 = query5.toString();
			    try
				{
                    //insert  user answer into gname.cname  						 
					System.out.println(q5);
					ps = conn.prepareStatement(q5);
                    ps.setString(1, username);
				    ps.setString(2, newusername); 
					ps.setString(3, answer);
					rst = ps.executeUpdate();
					if(rst>0)
					{
						System.out.println("insert new answer  for new group answer table ");
						result = "ok";
						//return (result);
					} 
					if(res.next())
					{
						System.out.println("insert new answer  for new user   into gname.cname  table ");
						//return (result);
					}
				}
				catch(Exception e)
				{
				    e.printStackTrace();
			        return("query 5 wrong does not insert new answer  into gname.cname  table");
				}
						
				if(username.equals(cname))
			    {
					System.out.println("username == cname");
					//check konad ke aya hame javab dadeand
					query9.append("select count(*) from ").append(groupname).append(".").append(cname).append(" where newuser = ?");
		            query10.append("select count(*) from ").append(groupname);
					q9 = query9.toString();
					q10 = query10.toString();
					try
					{
						System.out.println(q9);
						System.out.println(newusername);
						ps9 = conn.prepareStatement(q9);
                        ps9.setString(1, newusername);
						rs9 = ps9.executeQuery();
						rs9.next();
						rst9 = rs9.getInt(1);
					} 
					catch(Exception e)
				    {
				        e.printStackTrace();
			            return("query 9 wrong count(*) from groupname.cname table where newuser = newuser");
				    }
					try
					{
						System.out.println(q10);
						ps10 = conn.prepareStatement(q10);
                        //ps9.setString(1, newuser);
						rs10 = ps10.executeQuery(); 
						rs10.next();
						rst10 = rs10.getInt(1);
					}
					catch(Exception e)
					{
						e.printStackTrace();
			            return("query 10 wrong count(*) from groupname table ");
					}
					System.out.println(rst9);
					System.out.println(rst10);
					if(rst9 == rst10)
					{
						System.out.println("rst9 == rst10");
						query11.append("select * from ").append(groupname).append(".").append(cname).append(" where newuser = ?");
						q11 = query11.toString();
						try
						{
                            System.out.println(q11);						    
							//select * from gname.cname where newuser 
							ps11 = conn.prepareStatement(q11);
                            ps11.setString(1, newusername);
						    res11 = ps11.executeQuery();
							while(res11.next())
							{
							    answer = res11.getString("answer");
								System.out.println(answer);
								if(answer.equals("ok"))
								{
								    System.out.println("answer = ok ");
								    finanswer = (finanswer && true);
								    System.out.println(finanswer);
								}
								else 
								{
								    finanswer = (finanswer && false) ;
								    answer1 = "nok";
								    System.out.println(finanswer);
								    break;
								}
							}
							if(finanswer)
							{
							    System.out.println(finanswer);
								answer1 = "ok";
							}
                            else
							{   answer1 = "nok";
							}
							System.out.println(answer1);
							query12.append("update request set answer = ? where newuser = ? AND gname = ?");
						    q12 = query12.toString();
							try
							{
								System.out.println(answer1);
								//update request table 
								System.out.println(q12);
						        ps12 = conn.prepareStatement(q12);
                                ps12.setString(1, answer1);
						        ps12.setString(2, newusername); 
						        ps12.setString(3, groupname);
								rst12 = ps12.executeUpdate();
								if(rst12 > 0)
								{
								    System.out.println("update request table  is done");
									//return(result + answer1 +"done");
									/*query14.append("delete from ").append(groupname).append(".").append(cname).append("where newuser = ?");
									q14 = query14.toString();
									try
									{
									    System.out.println(q14);
						                ps14 = conn.prepareStatement(q14);
                                        ps14.setString(1, newuser); 
						                rst14 = ps14.executeUpdate();
									    if(rst14 == rst10)
									    {
									        System.out.println("delete all othe users answer to membership of a new user");
									    }
									  
									}
									catch(Exception e)
									{
									  e.printStackTrace();
			                         return("query 14 error delete user answers from gname.cname table");
									}*/
									
								}
								if(answer1.equals("ok"))
								{
								    //add new user to group table
									try
						            {
						                System.out.println(q3);
										System.out.println(newusername);
						                //Statement s3 = conn.createStatement(); 
				                        //res = s3.executeQuery(q3);
						                ps3 = conn.prepareStatement(q3);
                                        ps3.setString(1, newusername);
						                rst3 = ps.executeUpdate();
						                if(rst3>0)
						                {
						                    System.out.println("insert new user  into group table");
						                } 
						      
						            }
				                    catch(Exception e)
				                    {
				                        e.printStackTrace();
			                            return("query 3 wrong does not insert new user into group table");
				                    }
								}
								 
								   
							}
							catch(Exception e)
				            {
				                e.printStackTrace();
			                    return("query 12 update request table with answer");
				            }
							 
						}
						catch(Exception e)
				        {
				            e.printStackTrace();
			                return("query 11 select * from gname.cname table");
				        }
						  
						   
					}
						  
				}
						
								
                    					
            }					
				   
			catch(Exception e)
			{
				e.printStackTrace();
			    return("wrong");
					
				//return false;
			}
			finally 
			{
               
			    try { res.close();  if (res.isClosed()) System.out.println("log res closed."); } catch (Exception e) { /* ignored */ }
                try { ps.close();  if (ps.isClosed())  System.out.println("log ps closed."); } catch (Exception e) { /* ignored */ }
                try { conn.close(); if (conn.isClosed()) System.out.println(" log Connection closed."); } catch (Exception e) { /* ignored */ }		  
		    }
			return(result+answer1);
    }
///////////////////////////////////GETCOORDINATOR///////////////////////////////////////////////////
public String getcoordinator(String username , String groupname , String grouppass)
{
   int count = 0;
	    String result = "";
	    Connection conn = null;
        String driver = "org.apache.derby.jdbc.ClientDriver";
	    String cname = "";
	    String csearch = "";
		String gsearch = "";
	    String newuser = "";
	    String checks = "";
	    String finresult = "";
	    String newusers = "";
	    String answer1 = "";
	    String answer = "";
	    String newanswer = "";
	    String n = "";
	    boolean finanswer = true ;
        ResultSet res1 = null;
	    ResultSet res2 = null;
		ResultSet res4 = null;
		ResultSet res6 = null;
		ResultSet res7 = null;
	    ResultSet res8 = null;
	    ResultSet rs9 = null;
	    ResultSet rs10 = null;
	    ResultSet res11 = null;
	    ResultSet rs12 = null;
	    ResultSet rs13 = null;
	    ResultSet rs14 = null;
	    PreparedStatement ps = null;
	    PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
	    PreparedStatement ps8 = null;
	    PreparedStatement ps9 = null;
	    PreparedStatement ps10 = null;
	    PreparedStatement ps11 = null;
	    PreparedStatement ps12 = null;
	    PreparedStatement ps13 = null;
	    PreparedStatement ps14 = null;
	    int rst = 0;
	    int rst2 = 0;
		int rst3 = 0;
	    int rst8 = 0;
	    int rst9 = 0;
	    int rst10 = 0;
	    int rst12 = 0;
	    int rst13 = 0;
	    int rst14 = 0;
	    int rslt = 1;
		int rslt15 = 1;
		if(this.group.equals(groupname) && this.groupPassword.equals(grouppass))
		{
           
			result = "ok";
		}
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
		             
		    
		}
		catch(SQLException ex)
		{
	        System.err.println("can not connect to database server");
		    System.err.println("can not connect to database server");
		    System.out.println(conn);
	       
		}
		StringBuilder query1 = new StringBuilder() ;
		query1.append("SELECT * FROM groups");
		
		String q1 = query1.toString();
		
		try
		{
			//select * from groups
			System.out.println(q1);
			Statement s1 = conn.createStatement(); 
			res1 = s1.executeQuery(q1);
			while(res1.next())
			{
				     
			    //count ++;
				if((groupname.equals(res1.getString("name"))))
                {
                    System.out.println("group already  exist");
					cname = (res1.getString("coordinatorname"));
					//if(username.equals(cname)) csearch = "ok";
					//query8.append("select * from ").append(groupname).append(".").append(cname);
					gsearch = "ok";
					break;
					  
				      
                }
			}
			if((gsearch.equals("ok")))
			{
			 return (cname);
			}
			else return("nothing");
		}	
			catch(Exception e)
			{
				e.printStackTrace();
			    return("wrong");
					
				//return false;
			}
			finally 
			{
               
			    try { res1.close();  if (res1.isClosed()) System.out.println("log res closed."); } catch (Exception e) { /* ignored */ }
                try { ps.close();  if (ps.isClosed())  System.out.println("log ps closed."); } catch (Exception e) { /* ignored */ }
                try { conn.close(); if (conn.isClosed()) System.out.println(" log Connection closed."); } catch (Exception e) { /* ignored */ }		  
		    }
				
}
//////////////////////////// LEAVE ///////////////////////////////////////////////////
public String leave (String username , String groupname , String grouppass)
{ 
        int count = 0;
	    String result = "";
	    Connection conn = null;
        String driver = "org.apache.derby.jdbc.ClientDriver";
	    String cname = "";
	    String csearch = "";
	    String newuser = "";
	    String checks = "";
	    String finresult = "";
	    String newusers = "";
	    String answer1 = "";
	    String answer = "";
	    String newanswer = "";
	    String n = "";
	    boolean finanswer = true ;
        ResultSet res1 = null;
	    ResultSet res2 = null;
		ResultSet res4 = null;
		ResultSet res6 = null;
		ResultSet res7 = null;
	    ResultSet res8 = null;
	    ResultSet rs9 = null;
	    ResultSet rs10 = null;
	    ResultSet res11 = null;
	    ResultSet rs12 = null;
	    ResultSet rs13 = null;
	    ResultSet rs14 = null;
	    PreparedStatement ps = null;
	    PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
	    PreparedStatement ps8 = null;
	    PreparedStatement ps9 = null;
	    PreparedStatement ps10 = null;
	    PreparedStatement ps11 = null;
	    PreparedStatement ps12 = null;
	    PreparedStatement ps13 = null;
	    PreparedStatement ps14 = null;
	    int rst = 0;
	    int rst2 = 0;
		int rst3 = 0;
	    int rst8 = 0;
	    int rst9 = 0;
	    int rst10 = 0;
	    int rst12 = 0;
	    int rst13 = 0;
	    int rst14 = 0;
	    int rslt = 1;
		int rslt15 = 1;
		if(this.group.equals(groupname) && this.groupPassword.equals(grouppass))
		{
           
			result = "ok";
		}
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
		             
		    
		}
		catch(SQLException ex)
		{
	        System.err.println("can not connect to database server");
		    System.err.println("can not connect to database server");
		    System.out.println(conn);
	       
		}
		StringBuilder query1 = new StringBuilder() ;
		//query1.append("SELECT * FROM groups");
		query1.append("delete from  ").append(groupname).append(" where username = ?");
		String q1 = query1.toString();
		
		try
		{
			//select * from groups
			System.out.println(q1);
			ps = conn.prepareStatement(q1);
                    ps.setString(1, username);
				   // ps.setString(2, newusername); 
					//ps.setString(3, answer);
					rst = ps.executeUpdate();
					if(rst>0)
					{
						System.out.println("delet user from table ");
						result = "ok";
						//return (result);
					} 
					/*if(res.next())
					{
						System.out.println("insert new answer  for new user   into gname.cname  table ");
						//return (result);
					}*/
		}	
			catch(Exception e)
			{
				e.printStackTrace();
			    return("wrong");
					
				//return false;
			}
			finally 
			{
               
			    try { res1.close();  if (res1.isClosed()) System.out.println("log res closed."); } catch (Exception e) { /* ignored */ }
                try { ps.close();  if (ps.isClosed())  System.out.println("log ps closed."); } catch (Exception e) { /* ignored */ }
                try { conn.close(); if (conn.isClosed()) System.out.println(" log Connection closed."); } catch (Exception e) { /* ignored */ }		  
		    }
		return(result);
		
}
}
