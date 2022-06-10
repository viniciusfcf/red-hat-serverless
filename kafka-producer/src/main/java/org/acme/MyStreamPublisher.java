package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/")
public class MyStreamPublisher {

    int cnt = 0;

    @Inject
    @Channel("mystream")
    Emitter<String> emitter;

    @GET
    public String hello() {
        return "I am ready, use /1, /10, /100 or /1000";
    }

    @GET
    @Path("/1")
    public String send1() {
        send(1);
        return "Sent 1";

    }

    @GET
    @Path("/10")
    public String send10() {
        send(10);
        return "Sent 10";
    }

    @GET
    @Path("/100")
    public String send100() {
        send(100);
        return "Sent 100";
    }

    @GET
    @Path("/1000")
    public String send1000() {
        send(1000);
        return "Sent 1000";
    }

    private void send(int num) {
        for (int i = 0; i < num; i++) {
            int j = cnt++;
            emitter.send("{\"message\":\"spamming-" + j + "\", \"value\":\"" + (j % 2) + "\"}");
        }
    }

}