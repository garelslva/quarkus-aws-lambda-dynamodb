package org.acme.dynamodb;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/solicitation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    SyncService service;

    @GET
    public List<Solicitation> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{cpf}")
    public Solicitation getSingle(@PathParam("cpf") String cpf) {
        return service.get(cpf);
    }

    @POST
    @Path("insert")
    public List<Solicitation> add(Solicitation solicitation) {
        service.add(solicitation);
        return getAll();
    }
}