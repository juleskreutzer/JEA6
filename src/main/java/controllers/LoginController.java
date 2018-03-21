package controllers;

import dao.AccountDaoImpl;
import domain.Account;
import domain.Group;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 21-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: controllers
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Named(value = "LoginController")
@RequestScoped
public class LoginController {

    @Inject
    private AccountDaoImpl accountDao;

    private String title = "Kwetter Admin - Login";
    private String username;
    private String password;

    private Account account;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            System.out.println(username);
            Account a = accountDao.findAccountByUsername(username);

            if(a == null) {
                System.out.println("No account found");
            } else {
                if(a.getGroups().size() < 1) {
                    System.out.println("No groups for user");
                } else {
                    for(Group group : a.getGroups()) {
                        System.out.println("This users belongs to: " + group.getGroupName());
                    }
                }
            }

            request.login(username, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
            return "/admin/auth/login.xhtml";
        }
        Principal principal = request.getUserPrincipal();
        this.account = accountDao.findAccountByUsername(principal.getName());
        System.out.println("Auth done for user: " + principal.getName());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("User", account);
        if (request.isUserInRole("admin_role")) {
            return "/admin";
        } else {
            return "/auth/login.xhtml";
        }

    }
}
