package controllers;

import domain.Account;
import domain.Group;
import exceptions.EmailAllreadyRegisteredException;
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

    private long id;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String role;
    private String location;
    private String profileImage;
    private String website;

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
        return accountService.findById(this.userCount);
    }

    public void setLastCreatedAccount(Account lastCreatedAccount) {
    }

    public String getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    public String getSearchFilterChanged() {
        return searchFilterChanged;
    }

    public void setSearchFilterChanged(String searchFilterChanged) {
        this.searchFilterChanged = searchFilterChanged;
    }

    public Account searchByFullname(String partOfFullname) {
        this.searchResult = accountService.findByFullName(partOfFullname);

        this.id = this.searchResult.getId();
        this.username = this.searchResult.getUsername();
        this.email = this.searchResult.getEmail();
        this.password = this.searchResult.getPassword();
        this.bio = this.searchResult.getBio();
        this.location = this.searchResult.getLocation();
        this.profileImage = this.searchResult.getProfileImage();
        this.website = this.searchResult.getWeb();

        for(Group group : this.searchResult.getGroups()) {
            this.role += group.getGroupName() + ",";
        }


        return this.searchResult;
    }

    public void searchFilterChanged(ValueChangeEvent vce) {
        this.searchByFullname((String) vce.getNewValue());
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void createUser() throws EmailAllreadyRegisteredException {
        Account account = new Account(this.email, this.password);
        account.setUsername(this.username);
        account.setBio(this.bio);
        account.setLocation(this.location);
        account.setProfileImage(this.profileImage);
        account.setWeb(this.website);

        accountService.createAccount(account);
    }
}
