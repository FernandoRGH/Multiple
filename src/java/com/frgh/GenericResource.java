/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frgh;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Fernando
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.frgh.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String about() {
        return "corriendo";
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("reachable")
    public String reach(@FormParam("ip") String ip, @FormParam("puerto") Integer puerto) {
        
        try {
            if (puerto != null) {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(ip, puerto), 5000);
                return "ok";
            } else {
                InetAddress i = InetAddress.getByName(ip);
                boolean r = i.isReachable(5000);
                return String.valueOf(r);
            }             
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
