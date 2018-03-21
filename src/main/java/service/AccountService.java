package service;

import dao.AccountDaoImpl;
import domain.Account;
import exceptions.AccountNotFoundException;
import exceptions.EmailAllreadyRegisteredException;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
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
@Dependent
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

    public boolean createAccount(Account account) throws EmailAllreadyRegisteredException {
        boolean emailAllreadyRegistered = false;

        for(Account a : accountDao.getAllAccounts()) {
            if(account.getEmail().toLowerCase().equals(a.getEmail().toLowerCase())) {
                emailAllreadyRegistered = true;
            }
        }

        if(!emailAllreadyRegistered) {
            accountDao.createAccount(account);
            return true;
        }

        return false;
    }

    public void updateAccount(Account account) throws AccountNotFoundException {
        boolean userDoesExist = false;

        for(Account a : accountDao.getAllAccounts()) {
            if(account.getEmail().toLowerCase().equals(a.getEmail().toLowerCase())) {
                userDoesExist = true;
            }
        }

        if(userDoesExist) accountDao.editAccount(account);
    }

    public void deleteAccount(long id) {
        Account account = this.findById(id);

        accountDao.removeAccount(account);
    }
}
