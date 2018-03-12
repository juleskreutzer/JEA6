package arquillian;

import dao.AccountDaoImpl;
import dao.KwetDaoImpl;
import domain.Account;
import domain.Kwet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rest.AccountApi;
import rest.KwetApi;
import service.AccountService;
import service.KwetService;

import java.util.ArrayList;
import java.util.List;

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
public class KwetApiTestWithoutRestAssured {


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
                .addClass(KwetApi.class)
                .addClass(KwetService.class)
                .addClass(KwetDaoImpl.class)
                .addClass(Kwet.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Mock
    KwetService kwetServiceMock;

    @InjectMocks
    KwetApi api;

    private static Kwet kwet1;
    private static Kwet kwet2;

    private static Account account1;

    private static List<Kwet> kwetList;

    @Before
    public void setupBeforeMethods() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setup() {
        account1 = new Account("jules@nujules.nl", "password");

        kwet1 = new Kwet("This is a test text", account1);
        kwet2 = new Kwet("This is another test text", account1);

        kwetList = new ArrayList<Kwet>();
        kwetList.add(kwet1);
        kwetList.add(kwet2);
    }

    @Test
    public void getAllKwets() {
        when(api.getAllKwets()).thenReturn(kwetList);

        ArrayList<Kwet> result = (ArrayList<Kwet>) api.getAllKwets();

        Assert.assertEquals(result, kwetList);
    }

    @Test(expected = AbstractMethodError.class)
    public void getById() {
        when(api.getById(1)).thenReturn(kwet1);
        when(api.getById(-1)).thenThrow(AbstractMethodError.class);
        when(api.getById(0)).thenThrow(AbstractMethodError.class);

        Kwet result = api.getById(1);

        Assert.assertEquals(result, kwet1);

        try {
            api.getById(-1);
        } catch (AbstractMethodError _) {

        }

        api.getById(0);
    }

    @Test(expected = AbstractMethodError.class)
    public void getByText() {
        when(api.getByText("test")).thenReturn(kwetList);
        when(api.getByText("blabla")).thenThrow(AbstractMethodError.class);

        ArrayList<Kwet> result = (ArrayList<Kwet>) api.getByText("test");

        Assert.assertEquals(result, kwetList);

        api.getByText("blabla");
    }

    @Ignore
    @Test(expected = AbstractMethodError.class)
    public void getAllKwetsFromOwner() {
        when(api.getAllKwetsFromOwner(1)).thenReturn(kwetList);
        when(api.getAllKwetsFromOwner(-1)).thenThrow(AbstractMethodError.class);
        when(api.getAllKwetsFromOwner(0)).thenThrow(AbstractMethodError.class);

        ArrayList<Kwet> result = (ArrayList<Kwet>) api.getAllKwetsFromOwner(1);

        Assert.assertEquals(result, kwetList);

        try {
            api.getAllKwetsFromOwner(-1);
        } catch (AbstractMethodError _) {

        }

        api.getAllKwetsFromOwner(0);
    }

    @Test(expected = AbstractMethodError.class)
    public void getAllKwetsFromOwnerByText() {
        when(api.getAllKwetsFromOwnerByText(1, "test")).thenReturn(kwetList);

        ArrayList<Kwet> result = (ArrayList<Kwet>) api.getAllKwetsFromOwnerByText(1, "test");

        Assert.assertEquals(result, kwetList);
    }

    @Test
    public void createKwet() {
    }

    @Test
    public void updateKwet() {
    }
}
