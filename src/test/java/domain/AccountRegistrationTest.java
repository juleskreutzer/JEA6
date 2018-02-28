package domain;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 28-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: domain
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class AccountRegistrationTest {

    private static AccountRegistration account;
    private static String defaultEmail = "jules@nujules.nl";
    private static String defaultPassword = "kreutzer";

    @BeforeClass
    public static void setUp() {
        account = new AccountRegistration();
        account.setEmail(defaultEmail);
        account.setPassword(defaultPassword);
    }

    @Test
    public void getEmail() {
        Assert.assertEquals(account.getEmail(), defaultEmail);
    }

    @Test
    public void setEmail() {
        String newEmail = "juleskreutzer@me.com";

        AccountRegistration a = new AccountRegistration();
        a.setEmail(defaultEmail);

        Assert.assertEquals(a.getEmail(), defaultEmail);

        a.setEmail(newEmail);

        Assert.assertEquals(a.getEmail(), newEmail);
    }

    @Test
    public void getPassword() {
        Assert.assertEquals(account.getPassword(), defaultPassword);
    }

    @Test
    public void setPassword() {
        String newPassword = "password";

        AccountRegistration a = new AccountRegistration();
        a.setPassword(defaultPassword);

        Assert.assertEquals(a.getPassword(), defaultPassword);

        a.setPassword(newPassword);

        Assert.assertEquals(a.getPassword(), newPassword);
    }

    @Test
    public void TestPopulatedConstructor() {
        AccountRegistration a = new AccountRegistration(defaultEmail, defaultPassword);

        Assert.assertEquals(a.getEmail(), defaultEmail);
        Assert.assertEquals(a.getPassword(), defaultPassword);
    }
}
