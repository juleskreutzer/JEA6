package arquillian;

import dao.KwetDaoImpl;
import domain.Account;
import domain.Kwet;
import exceptions.KwetNotFoundException;
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
import service.KwetService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 02-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: service
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
@RunWith(Arquillian.class)
public class KwetServiceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(KwetService.class)
                .addClass(KwetDaoImpl.class)
                .addClass(Kwet.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Mock
    KwetDaoImpl kwetDaoMock;

    @InjectMocks
    KwetService kwetService;

    private static Account owner;

    private static List<Kwet> kwetList;

    private static Kwet kwet1;
    private static Kwet kwet2;

    @Before
    public void setupBeforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setup() {

        kwetList = new ArrayList<Kwet>();
        kwet1 = new Kwet("Test text", owner);
        kwet2 = new Kwet("Another test text", owner);

        kwetList.add(kwet1);
        kwetList.add(kwet2);

    }

    @Test
    public void getAllKwets() {
        when(kwetService.getAllKwets()).thenReturn(kwetList);

        ArrayList<Kwet> result = (ArrayList<Kwet>) kwetService.getAllKwets();

        Assert.assertEquals(result, kwetList);
    }

    @Test
    public void findById() {
        when(kwetService.findById(1)).thenReturn(kwet1);
        when(kwetService.findById(3)).thenReturn(null);

        Kwet result1 = kwetService.findById(1);
        Kwet result2 = kwetService.findById(3);

        Assert.assertEquals(result1, kwet1);
        Assert.assertEquals(result2, null);
    }

    @Test
    public void findByText() {
        when(kwetService.findByText("test")).thenReturn(kwetList);
        when(kwetService.findByText("unknown")).thenReturn(null);

        ArrayList<Kwet> result1 = (ArrayList<Kwet>) kwetService.findByText("test");
        ArrayList<Kwet> result2 = (ArrayList<Kwet>) kwetService.findByText("unknown");

        Assert.assertEquals(result1, kwetList);
        Assert.assertEquals(result2, null);
    }

    @Test
    public void findByOwnerId() {
        when(kwetService.findByOwnerId(1)).thenReturn(kwetList);
        when(kwetService.findByOwnerId(2)).thenReturn(null);

        ArrayList<Kwet> result1 = (ArrayList<Kwet>) kwetService.findByOwnerId(1);
        ArrayList<Kwet> result2 = (ArrayList<Kwet>) kwetService.findByOwnerId(2);

        Assert.assertEquals(result1, kwetList);
        Assert.assertEquals(result2, null);
    }

    @Test
    public void create() {
        Kwet kwet = new Kwet("This is some text", owner);

        for(int i = 0; i < 10; i++) {
            kwetService.create(kwet);
        }
    }

    @Test(expected = KwetNotFoundException.class)
    public void update() throws KwetNotFoundException {
        Kwet kwet = new Kwet("This is some text", owner);

        doThrow(KwetNotFoundException.class)
        .when(kwetDaoMock).edit(kwet);

        kwetService.update(kwet);
    }
}
