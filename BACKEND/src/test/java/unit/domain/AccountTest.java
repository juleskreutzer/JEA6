package unit.domain;

import domain.Account;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ROLE;

import java.util.ArrayList;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 28-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: domain
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


public class AccountTest {
    private static Account account;
    private static String defaultEmail = "jules@nujules.nl";
    private static String defaultPassword = "kreutzer";
    private static String defaultUsername = "juleskreutzer";
    private static String defaultFullName = "Jules Kreutzer";
    private static String defaultLocation = "Herten";
    private static String defaultWeb = "https://nujules.nl";
    private static String defaultBio = "This is the default biography";
    private static String defaultProfileImage = "test.jpg";
    private static ROLE defaultRole = ROLE.USER;

    private static Account follower1 = new Account("test", "test");
    private static Account follower2 = new Account("bla", "bla");
    private static Account following1 = new Account("sdf", "sdf");
    private static Account following2 = new Account("qwe", "qwe");

    private static Account fullAccount;

    @BeforeClass
    public static void setup() {
        account = new Account(defaultEmail, defaultPassword);
        account.setUsername(defaultUsername);
        account.setFullName(defaultFullName);
        account.setLocation(defaultLocation);
        account.setWeb(defaultWeb);
        account.setBio(defaultBio);
        account.setProfileImage(defaultProfileImage);
        account.setRole(defaultRole);

        ArrayList<Account> followers = new ArrayList<Account>();
        followers.add(follower1);
        followers.add(follower2);

        account.setFollowers(followers);

        ArrayList<Account> following = new ArrayList<Account>();
        following.add(following1);
        following.add(following2);

        account.setFollowing(following);

        fullAccount = new Account(defaultEmail, defaultPassword);
        fullAccount.setId(1);
    }

    @Test
    public void updateAccount() {
        Account a = new Account("test", "test");
        a.updateAccount(defaultBio, defaultFullName, defaultLocation, defaultProfileImage, defaultWeb);

        Assert.assertEquals(a.getBio(), defaultBio);
        Assert.assertEquals(a.getFullName(), defaultFullName);
        Assert.assertEquals(a.getLocation(), defaultLocation);
        Assert.assertEquals(a.getProfileImage(), defaultProfileImage);
        Assert.assertEquals(a.getWeb(), defaultWeb);
    }

    @Test
    public void getEmail() {
        Assert.assertEquals(account.getEmail(), defaultEmail);
    }

    @Test
    public void setEmail() {
        String newEmail = "juleskreutzer@me.com";

        Account a = new Account(defaultEmail, defaultPassword);
        a.setEmail(newEmail);

        Assert.assertEquals(a.getEmail(), newEmail);
    }

    @Test
    public void getUsername() {
        Assert.assertEquals(account.getUsername(), defaultUsername);
    }

    @Test
    public void setUsername() {
        String newUsername = "jules";

        Account a = new Account(defaultEmail, defaultPassword);
        a.setUsername(defaultUsername);

        Assert.assertEquals(a.getUsername(), defaultUsername);

        a.setUsername(newUsername);

        Assert.assertEquals(a.getUsername(), newUsername);
    }

    @Test
    public void getFullName() {
        Assert.assertEquals(account.getFullName(), defaultFullName);
    }

    @Test
    public void setFullName() {
        String newFullName = "Not Jules Kreutzer";

        Account a = new Account(defaultEmail, defaultPassword);
        a.setFullName(defaultFullName);

        Assert.assertEquals(a.getFullName(), defaultFullName);

        a.setFullName(newFullName);

        Assert.assertEquals(a.getFullName(), newFullName);
    }

    @Test
    public void getPassword() {
        Assert.assertEquals(account.getPassword(), defaultPassword);
    }

    @Test
    public void setPassword() {
        String newPassword = "password";

        Account a = new Account(defaultEmail, defaultPassword);

        Assert.assertEquals(a.getPassword(), defaultPassword);

        a.setPassword(newPassword);

        Assert.assertEquals(a.getPassword(), newPassword);
    }

    @Test
    public void getLocation() {
        Assert.assertEquals(account.getLocation(), defaultLocation);
    }

    @Test
    public void setLocation() {
        String newLocation = "Roermond";

        Account a = new Account(defaultEmail, defaultPassword);
        a.setLocation(defaultLocation);
        Assert.assertEquals(a.getLocation(), defaultLocation);

        a.setLocation(newLocation);

        Assert.assertEquals(a.getLocation(), newLocation);
    }

    @Test
    public void getWeb() {
        Assert.assertEquals(account.getWeb(), defaultWeb);
    }

    @Test
    public void setWeb() {
        String newWeb = "https://nu.nl";

        Account a = new Account(defaultEmail, defaultPassword);
        a.setWeb(defaultWeb);

        Assert.assertEquals(a.getWeb(), defaultWeb);

        a.setWeb(newWeb);

        Assert.assertEquals(a.getWeb(), newWeb);
    }

    @Test
    public void getBio() {
        Assert.assertEquals(account.getBio(), defaultBio);
    }

    @Test
    public void setBio() {
        String newBio = "Not a biography";

        Account a = new Account(defaultEmail, defaultPassword);
        a.setBio(defaultBio);

        Assert.assertEquals(a.getBio(), defaultBio);

        a.setBio(newBio);

        Assert.assertEquals(a.getBio(), newBio);
    }

    @Test
    public void getFollowers() {
        ArrayList<Account> followers = new ArrayList<Account>();
        followers.add(follower1);
        followers.add(follower2);

        Assert.assertEquals(account.getFollowers(), followers);
    }

    @Test
    public void addFollower() {
        Account follower3 = new Account("test", "test");

        Account a = new Account("test", "test");
        ArrayList<Account> followers = new ArrayList<Account>();
        followers.add(follower1);
        followers.add(follower2);

        a.setFollowers(followers);

        Assert.assertEquals(a.getFollowers(), followers);

        a.addFollower(follower3);
        followers.add(follower3);

        Assert.assertEquals(a.getFollowers(), followers);
    }

    @Test
    public void setFollowers() {
        Account follower3 = new Account("sdkjfh", "sdkjfh");

        ArrayList<Account> followers = new ArrayList<Account>();
        followers.add(follower1);
        followers.add(follower2);
        followers.add(follower3);

        Account a = new Account("test", "test");
        a.setFollowers(followers);

        Assert.assertEquals(a.getFollowers(), followers);
    }

    @Test
    public void getFollowing() {
        ArrayList<Account> following = new ArrayList<Account>();
        following.add(following1);
        following.add(following2);

        Assert.assertEquals(account.getFollowing(), following);
    }

    @Test
    public void addFollowing() {
        Account following3 = new Account("test", "test");

        Account a = new Account("test", "test");
        ArrayList<Account> following = new ArrayList<Account>();
        following.add(following1);
        following.add(following2);

        a.setFollowing(following);

        Assert.assertEquals(a.getFollowing(), following);

        a.addFollowing(following3);
        following.add(following3);

        Assert.assertEquals(a.getFollowing(), following);
    }

    @Test
    public void setFollowing() {
        Account following3 = new Account("sdkjfh", "sdkjfh");

        ArrayList<Account> following = new ArrayList<Account>();
        following.add(following1);
        following.add(following2);
        following.add(following3);

        Account a = new Account("test", "test");
        a.setFollowers(following);

        Assert.assertEquals(a.getFollowers(), following);
    }

    @Test
    public void getProfileImage() {
        Assert.assertEquals(account.getProfileImage(), defaultProfileImage);

    }

    @Test
    public void setProfileImage() {
        String newProfileImage = "profileImage.jpg";

        Account a = new Account("test", "test");
        a.setProfileImage(defaultProfileImage);

        Assert.assertEquals(a.getProfileImage(), defaultProfileImage);

        a.setProfileImage(newProfileImage);

        Assert.assertEquals(a.getProfileImage(), newProfileImage);
    }

    @Test
    public void getRole() {
        Assert.assertEquals(account.getRole(), defaultRole);
    }

    @Test
    public void setRole() {
        ROLE newRole = ROLE.ADMINSITRATOR;

        Account a = new Account("test", "test");
        a.setRole(defaultRole);

        Assert.assertEquals(a.getRole(), defaultRole);

        a.setRole(newRole);

        Assert.assertEquals(a.getRole(), newRole);
    }

    @Test
    public void getId() {
        Assert.assertEquals(fullAccount.getId(), 1);
    }

    @Test
    public void setId() {
        long newId = 2;

        Account account = fullAccount;
        Assert.assertEquals(account.getId(), 1);

        account.setId(newId);

        Assert.assertEquals(account.getId(), newId);
    }
}
