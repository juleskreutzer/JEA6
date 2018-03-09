package arquillian;

import dao.AccountDaoImpl;
import domain.Account;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rest.AccountApi;
import service.AccountService;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 05-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: rest
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
@RunWith(Arquillian.class)
public class AccountApiTestWithouthRestAssured {

    /**
     * FOR SOME METHODS IN THIS CLASS, AbstractMethodError IS EXPECTED AS THE RESULT FROM THE TEST.
     * THIS IS BECAUSE javax.ws.rs.core.Response.getStatusInfo() IS A ABSTRACT METHOD AND A NEW
     * WebApplicationException IS RETURNED WHEN CALLING THE TEST METHODS
     */



    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(AccountApi.class)
                .addClass(AccountService.class)
                .addClass(AccountDaoImpl.class)
                .addClass(Account.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Mock
    AccountService accountService;

    @InjectMocks
    AccountApi api;

    private static Account account1;
    private static Account account2;

    private static List<Account> accountList;

    @BeforeClass
    public static void setup() {
        account1 = new Account("jules@nujules.nl", "password");
        account2 = new Account("juleskreutzer@me.com", "password");

        accountList = new ArrayList<Account>();
        accountList.add(account1);
        accountList.add(account2);
    }

    @Before
    public void setupBeforeMethods() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllAccounts() {
        when(api.getAllAccounts()).thenReturn(accountList);

        ArrayList<Account> result = (ArrayList<Account>) api.getAllAccounts();

        Assert.assertEquals(result, accountList);
    }

    @Test(expected = AbstractMethodError.class)
    public void findAccountByEmail() {
        when(api.findAccountByEmail("jules@nujules.nl")).thenReturn(account1);
//        doThrow(WebApplicationException.class)
//                .when(accountService).findByEmail(null);
        when(accountService.findByEmail("unknown@mail.com")).thenReturn(null);
        doThrow(WebApplicationException.class)
                .when(api).findAccountByEmail("unknown@mail.com");

        Account result = api.findAccountByEmail("jules@nujules.nl");

        Assert.assertEquals(result, account1);

        try {
            api.findAccountByEmail("unknown@mail.com");
        } catch(WebApplicationException wae) {
//             Intentionally left blank
        }

//        api.findAccountByEmail(null);
    }

    @Test(expected = AbstractMethodError.class)
    public void findAccountById() {
        when(api.findAccountById(1)).thenReturn(account1);
        when(api.findAccountById(1000000)).thenThrow(AbstractMethodError.class);
        when(api.findAccountById(0)).thenThrow(AbstractMethodError.class);
        when(api.findAccountById(-1)).thenThrow(AbstractMethodError.class);

        Account result = api.findAccountById(1);

        Assert.assertEquals(result, account1);

        try {
            api.findAccountById(1000000);
        } catch(AbstractMethodError wae) {
            //Intentionally left blank
        }

        try {
            api.findAccountById(0);
        } catch(AbstractMethodError wae) {
            //Intentionally left blank
        }

        api.findAccountById(-1);
    }

    @Test(expected = AbstractMethodError.class)
    public void findAccountByFullName() {
        when(api.findAccountByFullName("Jules Kreutzer")).thenReturn(account2);
        when(api.findAccountByFullName("unknown name")).thenThrow(AbstractMethodError.class);
        when(api.findAccountByFullName(null)).thenThrow(AbstractMethodError.class);

        Account result = api.findAccountByFullName("Jules Kreutzer");

        Assert.assertEquals(result, account2);

        try {
            api.findAccountByFullName("unknown name");
        } catch(AbstractMethodError wae) {
            //Intentionally left blank
        }

        api.findAccountByFullName(null);
    }

    @Test
    public void createAccount() {
    }

    @Test
    public void updateAccount() {
    }

    @Test
    public void getFollowers() {
    }

    @Test
    public void getFollowing() {
    }
}
