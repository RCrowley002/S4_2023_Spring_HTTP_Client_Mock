package com.keyin.http.cli;

import com.keyin.domain.Airport;
import com.keyin.http.client.RESTClient;

import java.util.List;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    public String generateAirportReport() {
        List<Airport> airports = getRestClient().getAllAirports();

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" - ");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public static void main(String[] args) {
        // Check if the URL is provided as a command-line argument
        String url = "http://localhost:8080/airports";
        if (args.length > 0) {
            url = args[0];
        }


        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        // Set the URL for the RESTClient dynamically
        RESTClient restClient = new RESTClient();
        restClient.setUrl(url);
        cliApp.setRestClient(restClient);


        cliApp.generateAirportReport();
    }
}
