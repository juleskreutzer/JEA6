package domain;

import util.ROLE;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 19-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: domain
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT u FROM Account u"),
        @NamedQuery(name = "Account.findByID", query = "SELECT u FROM Account u WHERE u.id = :id"),
        @NamedQuery(name = "Account.findByEmail", query = "SELECT u FROM Account u WHERE u.email = :email"),
        @NamedQuery(name = "Account.getAccountByPartOfEmail", query = "SELECT u FROM Account u WHERE u.email LIKE :partOfEmail"),
        @NamedQuery(name = "Account.getAccountByFullName", query = "SELECT u FROM Account u WHERE u.fullName = :fullName"),
        @NamedQuery(name = "Account.getAccountByPartOfFullName", query = "SELECT u FROM Account u WHERE u.fullName LIKE :partOfFullName")
})
@Table(name = "Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String fullName;

    @JsonbTransient
    private  String password;

    private String location;

    private String web;

    private String bio;

    @OneToMany
    @JoinTable(name = "account_followers"
            , joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false))
    private List<Account> followers;
    @OneToMany
    @JoinTable(name = "account_following"
            , joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id", nullable = false))
    private List<Account> following;

    private String profileImage;

    private ROLE role = ROLE.USER;

    public Account() { }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(long id, String bio, String fullName, String location, String profileImage, String web) {
        this.id = id;
        this.bio = bio;
        this.fullName = fullName;
        this.location = location;
        this.profileImage = profileImage;
        this.web = web;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Account> getFollowers() {
        return followers;
    }

    public void addFollower(Account follower) {
        if(follower != null) { this.followers.add(follower); }
    }

    public void setFollowers(List<Account> followers) {
        this.followers = followers;
    }

    public List<Account> getFollowing() {
        return following;
    }

    public void addFollowing(Account follower) {
        if(follower != null) {
            this.following.add(follower);
        }
    }

    public void setFollowing(List<Account> following) {
        this.following = following;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }
}
