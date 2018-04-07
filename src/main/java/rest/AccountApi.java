package rest;

import domain.Account;
import domain.AccountRegistration;
import exceptions.EmailAllreadyRegisteredException;
import service.AccountService;
import util.JWT.JWTRequired;
import util.ResponseMessage;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
@Path("/accounts")
public class AccountApi {

    @Inject
    private AccountService service;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/all")
    @JWTRequired
    public Collection<Account> getAllAccounts() {
        Collection<Account> result = service.getAllAccounts();

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Path("/email/{email}")
    @JWTRequired
    @Produces(APPLICATION_JSON)
    public Account findAccountByEmail(@PathParam("email") String email) {
        if(email == null || email.trim().length() == 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }
        Account result = service.findByEmail(email);

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Path("/id/{id}")
    @JWTRequired
    @Produces(APPLICATION_JSON)
    public Account findAccountById(@PathParam("id") long id) {
        if(id <= 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }
        Account result = service.findById(id);

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Path("/fullname/{fullName}")
    @JWTRequired
    @Produces(APPLICATION_JSON)
    public Account findAccountByFullName(@PathParam("fullName") String fullName) {
        if(fullName == null || fullName.trim().length() == 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }
        Account result = service.findByFullName(fullName);

        if(result == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Account createAccount(AccountRegistration accountRegistration) {
        if(accountRegistration.getEmail() == null || accountRegistration.getEmail().trim().length() == 0 || accountRegistration.getPassword() == null || accountRegistration.getPassword().trim().length() == 0) {
            throw new WebApplicationException(ResponseMessage.PARAM_MISSING, Response.Status.NOT_ACCEPTABLE);
        }

        Account account = new Account(accountRegistration.getEmail(), accountRegistration.getPassword());

        try {
            boolean result = service.createAccount(account);

            if(!result) {
                throw new EmailAllreadyRegisteredException(ResponseMessage.EMAIL_ALLREADY_REGISTERED);
            }
        } catch(EmailAllreadyRegisteredException eae) {
            throw new WebApplicationException(eae.getMessage(), Response.Status.NOT_ACCEPTABLE);
        } catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }

        Account addedAccount = service.findByEmail(accountRegistration.getEmail());
        if(addedAccount != null) {
            return addedAccount;
        }

        throw new WebApplicationException(ResponseMessage.UNABLE_TO_CREATE_ACCOUNT, Response.Status.INTERNAL_SERVER_ERROR);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/login")
    public HashMap<String, Object> login(AccountRegistration accountRegistration) {
        if(accountRegistration.getEmail() == null || accountRegistration.getEmail().trim().length() == 0 ||
                accountRegistration.getPassword() == null || accountRegistration.getPassword().trim().length() == 0) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }

        String email = accountRegistration.getEmail();
        String password = accountRegistration.getPassword();

        boolean result = service.login(email, password);

        if(result) {
            HashMap<String, Object> dataToReturn = new HashMap<>();
            // Login is successful, generate JWT
            String token = service.issueJsonWebToken(email);
            Account account = service.findByEmail(accountRegistration.getEmail());

            dataToReturn.put("token", token);
            dataToReturn.put("account", account);

            return dataToReturn;

        } else {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }


    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @JWTRequired
    @Path("/update/{id}")
    public void updateAccount(@PathParam("id") long id, @FormParam("bio") String bio, @FormParam("fulllName") String fullName, @FormParam("location") String location, @FormParam("profileImage") String profileImage, @FormParam("web") String web) {
        //TODO: Check provided params
        //TODO: Use service to update account?
        Account account = service.findById(id);

        if(account != null) {
            account.updateAccount(bio, fullName, location, profileImage, web);
        }
        //service.updateAccount(account);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @JWTRequired
    @Path("/followers/{id}")
    public List<Account> getFollowers(@PathParam("id") long id) {
        Account account = service.findById(id);
        List<Account> result = account.getFollowers();

        if(result == null || result.size() == 0) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @JWTRequired
    @Path("/following/{id}")
    public List<Account> getFollowing(@PathParam("id") long id) {
        Account account = service.findById(id);
        List<Account> result = account.getFollowing();

        if(result == null || result.size() == 0) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return result;
    }
}
