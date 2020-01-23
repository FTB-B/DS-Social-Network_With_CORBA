# DS-Social-Network_With_CORBA
# Social Network with CORBA

The users in the created social network can make groups and add people to the group. The person who creates the group is called 'Coordinator' which is responsible to authenticate the users. The code is implemented in 'java' and 'jdbc apache' is used for the database. CORBA which is a middleware is used for communication between clients and server and also sending and recieving messages between the users. 


# Usage:
   - `javac` is used for compiling the code.
   - For using the code the CORBA middleware should be run with a port number. (the same port number is being used in the code) :
    `tnameserv -ORBInitialPort 2200`
  - Running the client side:
  `java SocialNetworkClient`
    with this the users can enter their informations
