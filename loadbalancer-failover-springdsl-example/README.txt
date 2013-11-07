Load balancing with MINA Example
================================

This example shows how you can easily use the Camel-MINA component to design a solution
allowing for a fault tolerant solution that redirects requests when a server is 
down.
These servers are simple TCP/IP servers created by the Apache MINA framework and run in
separate JVMs. 

In this example, the load balancer client will generate a report every 10 seconds
and send that report to the MINA server running on localhost:9991. This server
then forwards the report to the MINA server running on localhost:9993, which then 
returns the report to the client so it can print it on the console. Each MINA 
server will change the body of the message so you can see the routes that the 
report had to use. If for some reason (lets say you pressed CTRL+C), the MINA
server running on localhost:9991 is dead, then the loadbalancer will automatically
start using the MINA server running on localhost:9992. Once the this MINA server
receives the report, it will send it back to the MINA server running on localhost:9993 
like nothing has ever happened. If localhost:9991 gets back up again, then the 
loadbalancer will start using it again. 

The load balancer will always attempt to use localhost:9991 before trying to use
localhost:9992 no matter what.

Running the example
===================

To compile and install the project in your maven repo, execute the following 
command on the root of the project

mvn clean install 

To run the example, then execute the following command in the respective folder:

>mina1
mvn exec:java -Pmina1

>mina2
mvn exec:java -Pmina2 

>mina3
mvn exec:java -Pmina3

>loadbalancing
mvn exec:java -Ploadbalancer


If you hit any problems please let us know on the Camel Forums
  http://camel.apache.org/discussion-forums.html


------------------------
Pedro Martins !