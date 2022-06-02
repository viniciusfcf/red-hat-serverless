package org.acme;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MySinkService {
    
    public void process(String event) {
        System.out.println("EVENT: " + event);

        try { // adding "some processing time"
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
