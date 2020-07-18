package org.acme.dynamodb.ascync;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;
import org.acme.dynamodb.Solicitation;

@Path("/async-solicitation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsyncResource {

    @Inject
    AsyncService service;

    @GET
    public Uni<List<Solicitation>> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{cpf}")
    public Uni<Solicitation> getSingle(@PathParam("cpf") String cpf) {
        return service.get(cpf);
    }

    @POST
    @Path("insert")
    public Uni<List<Solicitation>> add(Solicitation solicitation) {
        return service.add(solicitation)
                .onItem().ignore().andSwitchTo(this::getAll);
    }
}
