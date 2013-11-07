/**
 * @author Apache Software Foundation (ASF)
 * @version 1.0.0
 * 
 * Class used as a way to generate Reports. It is used by the LoadBalancer, 
 * in the case, MyApp_A.
 * 
 * @see		{@link MyApp_A}
 * @see		{@link Report}
 */
package service;

import model.Report;

public class Generator  {

    private static int count;

    public Report createReport() throws Exception {
        int counter = ++count;

        // Create a Report object
        Report report = new Report();
        report.setId(counter);
        report.setTitle("Report Title: " + counter);
        report.setContent("This is a dummy report");

        // Add the report to the Body
        return report;
    }
}