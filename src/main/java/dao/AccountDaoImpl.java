package dao;

import domain.Account;
import exceptions.AccountNotFoundException;
import exceptions.EmailAllreadyRegisteredException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.ResponseMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

@ApplicationScoped
@Named
public class AccountDaoImpl implements IAccountDao {


    @PersistenceContext(unitName = "KwetterGlassfish")
    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public void createAccount(Account u) throws EmailAllreadyRegisteredException {
        if(this.findAccountByEmail(u.getEmail()) == null) {
            em.persist(u);
        } else {
            throw new EmailAllreadyRegisteredException(ResponseMessage.EMAIL_ALLREADY_REGISTERED);
        }
    }

    public void editAccount(Account u) throws AccountNotFoundException {
        if(this.findAccountByEmail(u.getEmail()) != null) {
            em.merge(u);
        } else {
            throw new AccountNotFoundException(ResponseMessage.ACCOUNT_NOT_FOUND_WITH_PROVIDED_EMAIL);
        }
    }

    public void removeAccount(Account u) {
        em.remove(u);
    }

    public List<Account> getAllAccounts() {
        return (List<Account>) em.createNamedQuery("Account.findAll").getResultList();
    }

    public Account findAccountByEmail(String email) {
        Query q = em.createNamedQuery("Account.findByEmail");
        q.setParameter("email", email);

        try {
            return (Account) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Account findAccountByFullName(String fullName) {
        Query q = em.createNamedQuery("Account.getAccountByFullName");
        q.setParameter("fullName", fullName);

        try {
            return (Account) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Account findAccountByID(long id) {
        Query q = em.createNamedQuery("Account.findByID");
        q.setParameter("id", id);

        try {
            return (Account) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Account> getAllFollowing(Account u) {
        Account account = this.findAccountByEmail(u.getEmail());
        return account.getFollowing();
    }

    public int getFollowingCount(Account u) {
        Account account = this.findAccountByEmail(u.getEmail());
        return account.getFollowing().size();
    }

    public List<Account> getAllFollowers(Account u) {
        Account account = this.findAccountByEmail(u.getEmail());
        return account.getFollowers();
    }

    public int getFollowersCount(Account u){
        Account account = this.findAccountByEmail(u.getEmail());
        return account.getFollowers().size();
    }

    public List<Account> findAccountByPartOfEmail(String partOfEmail) {
        Query q = em.createNamedQuery("Account.getAccountByPartOfEmail");
        q.setParameter("partOfEmail", "%" + partOfEmail + "%");

        try {
            return (List<Account>) q.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Account> findAccountByPartOfFullName(String partOfFullName) {
        Query q = em.createNamedQuery("Account.getAccountByPartOfFullName");
        q.setParameter("partOfFullName", partOfFullName);

        try {
            return (List<Account>) q.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public boolean login(String Accountname, String password) {
        throw new NotImplementedException();
    }
}
