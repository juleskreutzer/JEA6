package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        @NamedQuery(name = "Account.getAccountByPartOfFullName", query = "SELECT u FROM Account u WHERE u.fullName LIKE :partOfFullName"),
        @NamedQuery(name = "Account.getAccountByUsername", query = "SELECT u FROM Account u WHERE u.username = :username"),
        @NamedQuery(name = "Account.login", query = "SELECT u FROM Account u WHERE u.email = :email AND u.password = :password")
})
@Table(name = "Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

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
    @JsonIgnore
    private List<Account> followers;

    @OneToMany
    @JoinTable(name = "account_following"
            , joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id", nullable = false))
    @JsonIgnore
    private List<Account> following;

    private String profileImage;

//    private ROLE role = ROLE.USER;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
    private List<Group> groups;

    public Account() { }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void updateAccount(String bio, String fullName, String location, String profileImage, String web) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}

