package jp.sample.jaxrs.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import jp.sample.jaxrs.domain.Item;

@Path("/api")
public interface HalauController {

    @POST
    @Path("/add")
    @Consumes( MediaType.APPLICATION_JSON )
    public void putRecord(Item item) throws Exception;

    @GET
    @Path("/")
    @Produces({"applicaton/json", "application/xml"})
    public Item[] pullRecords();

    @GET
    @Path("/hello")
    public String sayHello();
}