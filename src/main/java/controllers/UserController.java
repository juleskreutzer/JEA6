package controllers;

import domain.Account;
import service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
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
    private Account lastCreatedAccount;
    private Account searchResult;

    private String searchFilter;
    private String searchFilterChanged;

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

    public Account getLastCreatedAccount() {
        this.getUserCount();
        return accountService.findById(this.userCount -1);
    }

    public void setLastCreatedAccount(Account lastCreatedAccount) {
    }

    public String getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    public Account getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(Account searchResult) {
        this.searchResult = searchResult;
    }

    public String getSearchFilterChanged() {
        return searchFilterChanged;
    }

    public void setSearchFilterChanged(String searchFilterChanged) {
        this.searchFilterChanged = searchFilterChanged;
    }

    public Account searchByFullname(String partOfFullname) {
        this.searchResult = accountService.findByFullName(partOfFullname);

        return this.searchResult;
    }

    public void searchFilterChanged(ValueChangeEvent vce) {
        this.searchByFullname((String) vce.getNewValue());
    }
}
