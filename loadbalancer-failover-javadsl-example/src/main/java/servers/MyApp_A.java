/**
 * @author Pedro Martins
 * @version 1.1.1
 * 
 * The load balancer that generates a Report every 10 seconds and then sends it
 * to MINA server 1 or MINA server 2, should MINA server 1 not respond. This 
 * load balancer will always try to contact MINA server 1 first in all cases, 
 * and it will only use MINA server 2 if it receives an exception from MINA 
 * server 1 (thus meaning that it cannot communicate with it).
 * 
 * The load balancer will also receive a reply from the MINA servers and print 
 * that reply to the console (the reply is the Report).
 * 
 * @see	{@link MyApp_B}
 * @see	{@link MyApp_C}
 * @see	{@link Report}
 * 
 * @see <a href="http://camel.apache.org/load-balancer.html">load balancer</a>
 * @see	<a href="http://camel.apache.org/mina2.html">MINA 2</a>
 * @see	<a href="http://camel.apache.org/running-camel-standalone-and-have-it-keep-running.html">Java Main in Camel</a>
 */
package servers;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class MyApp_A {

	private Main main;

	public static void main(String... args) throws Exception {
		MyApp_A loadbalancer = new MyApp_A();
		loadbalancer.boot();
	}

	public void boot() throws Exception {
		main = new Main();
		main.enableHangupSupport();

		main.addRouteBuilder(
				new RouteBuilder(){
					@Override
					public void configure() throws Exception {
						from("timer://org.apache.camel.example.loadbalancer?period=10s")
						.beanRef("service.Generator", "createReport")
						.to("direct:loadbalance");

						from("direct:loadbalance")
							.loadBalance().failover()
								// will send to A first, and if fails then send to B afterwards
								.to("mina:tcp://localhost:9991?sync=true")
								.to("mina:tcp://localhost:9992?sync=true")
							.end()
						.log("${body}");
					}
				}
		);
		
		 System.out.println("Starting Camel MyApp_A. Use ctrl + c to terminate the JVM.\n");
		 main.run();
	}
}

