package demo.whimsy.action;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.whimsy.service.RegisterMgr;

/**
 * Created by whimsy on 1/30/15.
 */

@Component
@Path("/user")
public class RegisterAction {

    @Autowired
    RegisterMgr registerMgr;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllUser() {
        return Response.status(Response.Status.OK).entity(registerMgr.list()).build();
    }
}
