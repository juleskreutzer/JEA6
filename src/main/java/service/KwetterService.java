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
public class KwetterService {

    @Inject
    AccountDaoImpl accountDao;


    public void createAccount(Account user) {
        boolean emailAllreadyRegistered = false;

        for(Account u : accountDao.getAllAccounts()) {
            if(user.getEmail().toLowerCase().equals(u.getEmail().toLowerCase())) {
                emailAllreadyRegistered = true;
            }
        }

        if(!emailAllreadyRegistered) accountDao.createAccount(user);
    }

    public Collection<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public Account findByEmail(String email) {
        return accountDao.findAccountByEmail(email);
    }
}
