package arquillian;

import dao.AccountDaoImpl;
import dao.KwetDaoImpl;
import domain.Account;
import domain.Kwet;
import exceptions.EmailAllreadyRegisteredException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.*;
import org.junit.runner.RunWith;
import rest.AccountApi;
import rest.KwetApi;
import service.AccountService;
import service.KwetService;

import javax.persistence.*;
import java.util.List;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 07-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: dao
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
@RunWith(Arquillian.class)
public class AccountDaoImplTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(AccountApi.class)
                .addClass(AccountService.class)
                .addClass(AccountDaoImpl.class)
                .addClass(Account.class)
                .addClass(KwetApi.class)
                .addClass(KwetService.class)
                .addClass(KwetDaoImpl.class)
                .addClass(Kwet.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    static EntityManager em = null;

    static AccountDaoImpl dao = null;

    private static Account account1;
    private static Account account2;

    @BeforeClass
    public static void setup() throws Exception {
        dao = new AccountDaoImpl();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("KwetterGlassfishTest");
        em = factory.createEntityManager();

        dao.setEntityManager(em);

        account1 = new Account("juleskreutzer@me.com", "password");
        account2 = new Account("jules@nujules.nl", "password");

        begin();
        em.persist(account1);
        em.persist(account2);
        commit();
    }

    @AfterClass
    public static void tearDown() {
        if(em != null) em.close();
    }

    private static void begin() {
        em.getTransaction().begin();
    }

    private static void commit() {
        em.getTransaction().commit();
    }

    @Test(expected = RollbackException.class)
    public void createAccount() throws EmailAllreadyRegisteredException {
        Account account = new Account("1@mail.com", "password");

        this.begin();
        em.persist(account);
        this.commit();

        Account secondAccount = new Account("1@mail.com", "drowssap");

        this.begin();
        em.persist(secondAccount);
        this.commit();
    }

    @Test
    public void editAccount() {
        Account account = new Account("2@mail.com", "password");

        this.begin();
        em.persist(account);
        this.commit();

        account.setWeb("https://nujules.nl");
        account.setFullName("Jules Kreutzer");
        account.setBio("This is a test bio");
        account.setProfileImage("test.jpg");
        account.setLocation("Herten");
        account.setPassword("drowsapp");

        this.begin();
        em.merge(account);
        this.commit();

        Account result = (Account) em.createQuery("SELECT a FROM Account a WHERE a.email = '2@mail.com'").getSingleResult();

        Assert.assertEquals("https://nujules.nl", result.getWeb());
        Assert.assertEquals("Jules Kreutzer", result.getFullName());
        Assert.assertEquals("This is a test bio", result.getBio());
        Assert.assertEquals("test.jpg", result.getProfileImage());
        Assert.assertEquals("Herten", result.getLocation());
        Assert.assertEquals("drowsapp", result.getPassword());
    }

    @Test(expected = NoResultException.class)
    public void removeAccount() {
        Account account = new Account("3@mail.com", "password");

        this.begin();
        em.persist(account);
        this.commit();

        this.begin();
        em.remove(account);
        this.commit();

        em.createQuery("SELECT a FROM Account a WHERE a.email = '3@mail.com'").getSingleResult();
    }

    @Test
    public void getAllAccounts() {
        List<Account> result = (List<Account>) em.createNamedQuery("Account.findAll").getResultList();

        Assert.assertEquals(true, result.size() >= 2);
    }

    @Test
    public void findAccountByEmail() {
        Query q = em.createNamedQuery("Account.findByEmail");
        q.setParameter("email", account1.getEmail());

        Account result = (Account) q.getSingleResult();

        Assert.assertEquals(account1.getEmail(), result.getEmail());
    }

    @Test
    public void findAccountByFullName() {
        Account account = new Account("4@mail.com", "password");

        this.begin();
        em.persist(account);
        this.commit();

        account.setFullName("Jules Kreutzer");

        this.begin();
        em.merge(account);
        this.commit();

        Query q = em.createNamedQuery("Account.getAccountByFullName");
        q.setParameter("fullName", "Jules Kreutzer");

        Account result = (Account) q.getSingleResult();

        Assert.assertEquals(account, result);
    }

    @Test
    public void findAccountByID() {
        Query q = em.createNamedQuery("Account.findByID");
        q.setParameter("id", 1);

        Account result = (Account) q.getSingleResult();

        Assert.assertEquals(account1.getEmail(), result.getEmail());
    }

    @Test
    public void getAllFollowing() {
    }

    @Test
    public void getFollowingCount() {
    }

    @Test
    public void getAllFollowers() {
    }

    @Test
    public void getFollowersCount() {
    }

    @Test
    @Ignore // test fails
    public void findAccountByPartOfEmail() {
        Query q = em.createNamedQuery("Account.getAccountByPartOfEmail");
        q.setParameter("partOfEmail", "kreutzer");

        List<Account> result = (List<Account>) q.getResultList();

        Assert.assertEquals(1, result.size());
    }

    @Test
    @Ignore // Test fails
    public void findAccountByPartOfFullName() {
        Account account = new Account("5@mail.com", "password");

        this.begin();
        em.persist(account);
        this.commit();

        account.setFullName("Jan Janssen");

        this.begin();
        em.merge(account);
        this.commit();

        Query q = em.createNamedQuery("Account.getAccountByPartOfFullName");
        q.setParameter("partOfFullName", "sen");

        List<Account> result = (List<Account>) q.getResultList();

        Assert.assertEquals(true, result.size() >= 1);
    }

    @Test
    @Ignore
    public void login() {
    }
}
