/**
 * @author Pedro Martins
 * @version 1.1.1
 * 
 * MINA server 3. This MINA server will receive an updated report from either 
 * MINA server 1 or MINA server 2, depending on who is running and receiving 
 * reports from the load balancer currently.
 * 
 * @see	{@link MyApp_B}
 * @see	{@link MyApp_D}
 * @see	{@link Report}
 * 
 * @see	<a href="http://camel.apache.org/mina2.html">MINA 2</a>
 * @see	<a href="http://camel.apache.org/running-camel-standalone-and-have-it-keep-running.html">Java Main in Camel</a>
 */
package servers;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class MyApp_C {
	private Main main;

	public static void main(String... args) throws Exception {
		MyApp_C loadbalancer = new MyApp_C();
		loadbalancer.boot();
	}

	public void boot() throws Exception {
		main = new Main();
		main.enableHangupSupport();

		main.addRouteBuilder(
				new RouteBuilder(){
					@Override
					public void configure() throws Exception {
						from("mina:tcp://localhost:9993")
						.setHeader("minaServer", constant("localhost:9993"))
						.beanRef("service.Reporting", "updateReport")
						.log("${body}");
					}
				}
		);

		System.out.println("Starting Camel MyApp_C. Use ctrl + c to terminate the JVM.\n");
		main.run();
	}
}

