package rest;

import domain.Account;
import service.KwetterService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
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
@Path("/Accounts")
public class AccountApi {

    @Inject
    private KwetterService service;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/all")
    public Collection<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Path("/create")
    public String createAccount(@FormParam("email") String email, @FormParam("password") String password) {
        Account Account = new Account(email, password);
        service.createAccount(Account);

        Account addedAccount = service.findByEmail(email);
        if(addedAccount != null) {
            return addedAccount.getEmail() + " Account created";
        }

        return "Could not create Account";
    }
}
