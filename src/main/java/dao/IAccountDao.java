package dao;

import domain.Account;

import java.util.List;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 23-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: dao
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public interface IAccountDao {

    // Interface describing methods that can be called on Account instance

    /**
     * Create a new Account
     * @param u
     */
    void createAccount(Account u);

    /**
     * Edit an existing Account
     * @param u
     */
    void editAccount(Account u);

    /**
     * Remove an existing Account
     * @param u
     */
    void removeAccount(Account u);

    /**
     * Get all Accounts
     * @return returns a list of Accounts
     */
    List<Account> getAllAccounts();

    /**
     * Find a Account based on his exact Accountname
     * @param email
     * @return returns the Account, if found
     */
    Account findAccountByEmail(String email);

    /**
     * Find a Account based on his full name
     * @param fullName
     * @return
     */
    Account findAccountByFullName(String fullName);

    /**
     * Find a Account by ID
     * @param id
     * @return returns the Account, if found
     */
    Account findAccountByID(long id);

    /**
     * Get a list of all Accounts that the current Account is following
     * @param u
     * @return
     */
    List<Account> getAllFollowing(Account u);

    /**
     * Get the amount of Accounts that the current Account is following
     * @param u
     * @return
     */
    int getFollowingCount(Account u);

    /**
     * Get a list of all Accounts who are following the current Account
     * @param u
     * @return
     */
    List<Account> getAllFollowers(Account u);

    /**
     * Get the amount of Accounts that are following the current Account
     * @param u
     * @return
     */
    int getFollowersCount(Account u);

    /**
     * Find a Account based on a part of the Accountname
     * @param partOfEmail
     * @return
     */
    List<Account> findAccountByPartOfEmail(String partOfEmail);

    /**
     * Find a Account based on a part of the full name
     * @param partOfFullName
     * @return
     */
    List<Account> findAccountByPartOfFullName(String partOfFullName);

    /**
     * Login
     * @param Accountname
     * @param password
     * @return returns true is login is success, false if not
     */
    boolean login(String Accountname, String password);
}
