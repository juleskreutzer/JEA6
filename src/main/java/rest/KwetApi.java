package rest;

import domain.Kwet;
import exceptions.KwetNotFoundException;
import service.KwetService;
import util.ResponseMessage;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 26-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: rest
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Stateless
@ApplicationPath("/kwets")
public class KwetApi {

    @Inject
    private KwetService service;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/all")
    public Collection<Kwet> getAllKwets() {
        Collection<Kwet> result = service.getAllKwets();

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/id/{id}")
    public Kwet getById(@PathParam("id") long id) {
        if(id < 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }

        Kwet result = service.findById(id);

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/text/{text}")
    public Collection<Kwet> getByText(@PathParam("text") String text) {
        if(text == null || text.trim().length() == 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }

        Collection<Kwet> result = service.findByText(text);

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/owner/{id}/all")
    public Collection<Kwet> getAllKwetsFromOwner(@PathParam("id") long id) {
        if(id < 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }

        Collection<Kwet> result = service.findByOwnerId(id);

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/owner/{id}/text/{text}")
    public Collection<Kwet> getAllKwetsFromOwnerByText(@PathParam("id") long id, @PathParam("text") String text) {
        if(id < 0 || text.trim().length() == 0 || text == null) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }

        Collection<Kwet> tempResult = service.findByOwnerId(id);

        if(tempResult == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        } else {
            Collection<Kwet> result = new ArrayList<Kwet>();

            for(Kwet k : tempResult) {
                if(k.getText().toLowerCase().equals(text)) {
                    result.add(k);
                }
            }

            if(result.size() < 1) {
                throw new WebApplicationException(Response.Status.NO_CONTENT);
            } else {
                return result;
            }
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Path("/create")
    public Kwet createKwet(Kwet kwet) {
        if(kwet.getOwner() == null || kwet.getText() == null || kwet.getText().trim().length() == 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }

        service.create(kwet);

        ArrayList<Kwet> tempResult = (ArrayList<Kwet>) service.findByOwnerId(kwet.getOwner().getId());

        if(tempResult != null) {
            for(Kwet k : tempResult) {
                if(k.getText().equals(kwet.getText())) {
                    return k;
                }
            }
        }

        throw new WebApplicationException(ResponseMessage.UNABLE_TO_CREATE_KWET, Response.Status.INTERNAL_SERVER_ERROR);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Path("/update")
    public void updateKwet(Kwet kwet) {
        if(kwet == null) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }

        try {
            service.update(kwet);
        } catch (KwetNotFoundException knfe) {
            throw new WebApplicationException(knfe.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
