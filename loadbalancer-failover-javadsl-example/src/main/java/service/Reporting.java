/**
 * @author Apache Software Foundation (ASF)
 * @author Pedro Martins
 * @version 1.1.1
 * 
 * Class used as a way to update Reports. It is used by all the MINA servers to
 * update the Report, adding the localhost IP to the body of the message.
 * 
 * @see		{@link MyApp_B}
 * @see		{@link MyApp_C}
 * @see		{@link MyApp_D}
 * @see		{@link Report}
 */
package service;

import org.apache.camel.Body;
import org.apache.camel.Header;

import model.Report;



public class Reporting {
    
    public Report updateReport(@Body Report report, @Header("minaServer") String name) {
    	
    	if(report.getReply() != null)
    		report.setReply(report.getReply() + name);
    	else
    		report.setReply("Report passed by MINA servers running on: " + name + " and ");

    	// send the report updated
        return report;
    }
}