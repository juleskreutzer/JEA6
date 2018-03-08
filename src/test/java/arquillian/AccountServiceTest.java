package arquillian;

import dao.AccountDaoImpl;
import domain.Account;
import exceptions.AccountNotFoundException;
import exceptions.EmailAllreadyRegisteredException;
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
import service.AccountService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 01-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: service
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
@RunWith(Arquillian.class)
public class AccountServiceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(AccountService.class)
                .addClass(AccountDaoImpl.class)
                .addClass(Account.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Mock
    AccountDaoImpl accountDaoMock;

    @InjectMocks
    AccountService accountService;

    private static List<Account> accountList;

    private static Account account1;
    private static Account account2;

    @Before
    public void setupBeforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setup() {

        accountList = new ArrayList<Account>();

        account1 = new Account("mail1@example.com", "password");
        account1.setFullName("Jules");
        account2 = new Account("mail2@example.com", "password");

        accountList.add(account1);
        accountList.add(account2);
    }

    @Test(expected = EmailAllreadyRegisteredException.class)
    public void createDuplicateAccount() throws EmailAllreadyRegisteredException {
        doThrow(EmailAllreadyRegisteredException.class)
                .when(accountDaoMock).createAccount(account1);

        accountService.createAccount(account1);
    }

    @Test
    public void createAccount() throws Exception {
        Boolean result = accountService.createAccount(new Account("juleskreutzer@me.com", "password"));

        Assert.assertEquals(result, true);
    }

    @Test
    public void getAllAccounts() {
        when(accountService.getAllAccounts()).thenReturn(accountList);

        ArrayList<Account> result = (ArrayList<Account>) accountService.getAllAccounts();

        Assert.assertEquals(result, accountList);
    }

    @Test
    public void findByEmail() {
        when(accountService.findByEmail(account1.getEmail())).thenReturn(account1);
        when(accountService.findByEmail("unknown@test.com")).thenReturn(null);

        Account result1 = accountService.findByEmail(account1.getEmail());
        Account result2 = accountService.findByEmail("unknown@test.com");

        Assert.assertEquals(result1, account1);
        Assert.assertEquals(result2, null);
    }

    @Test
    public void findById() {
        when(accountService.findById(1)).thenReturn(account1);
        when(accountService.findById(3)).thenReturn(null);

        Account result1 = accountService.findById(1);
        Account result2 = accountService.findById(3);

        Assert.assertEquals(result1, account1);
        Assert.assertEquals(result2, null);
    }

    @Test
    public void findByFullName() {
        when(accountService.findByFullName("Jules")).thenReturn(account1);
        when(accountService.findByFullName("Kreutzer")).thenReturn(null);

        Account result1 = accountService.findByFullName(account1.getFullName());
        Account result2 = accountService.findByFullName("Kreutzer");

        Assert.assertEquals(result1, account1);
        Assert.assertEquals(result2, null);
    }

    @Test
    public void updateAccount() throws AccountNotFoundException {
        account1.setWeb("https://nujules.nl");

        accountService.updateAccount(account1);

//        Account unknownAccount = new Account("asdasdasd@mail.com", "password");
//
//        doThrow(AccountNotFoundException.class)
//                .when(accountDaoMock)
//                .editAccount(unknownAccount);
//
//        accountService.updateAccount(unknownAccount);

    }
}
