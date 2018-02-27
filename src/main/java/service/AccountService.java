package service;

import dao.AccountDaoImpl;
import domain.Account;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 26-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: service
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Stateless
@Named
public class AccountService {

    @Inject
    AccountDaoImpl accountDao;

    public Collection<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public Account findByEmail(String email) {
        return accountDao.findAccountByEmail(email);
    }

    public Account findById(long id) {
        return accountDao.findAccountByID(id);
    }

    public Account findByFullName(String fullName) {
        return accountDao.findAccountByFullName(fullName);
    }

    public void createAccount(Account account) {
        boolean emailAllreadyRegistered = false;

        for(Account a : accountDao.getAllAccounts()) {
            if(account.getEmail().toLowerCase().equals(a.getEmail().toLowerCase())) {
                emailAllreadyRegistered = true;
            }
        }

        if(!emailAllreadyRegistered) accountDao.createAccount(account);
    }

    public void updateAccount(Account account) {
        boolean userDoesExist = false;

        for(Account a : accountDao.getAllAccounts()) {
            if(account.getEmail().toLowerCase().equals(a.getEmail().toLowerCase())) {
                userDoesExist = true;
            }
        }

        if(userDoesExist) accountDao.editAccount(account);
    }
}
