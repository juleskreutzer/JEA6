package controllers;

import service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 19-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: controllers
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Named(value = "UserController")
@RequestScoped
public class UserController implements Serializable {

    @Inject
    private AccountService accountService;

    private int userCount;

    private String pageTitle = "Kwetter Admin - Users";

    public int getUserCount() {
        this.userCount = accountService.getAllAccounts().size();
        return this.userCount;
    }

    public void setUserCount(int userCount) {
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
