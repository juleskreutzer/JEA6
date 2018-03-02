package domain;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

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

public class KwetTest {

    private static Kwet kwet;
    private static Account owner;
    private static String defaultText = "This is a test text";
    private static Date defaultCreationDate = new Date();
    private static ArrayList<String> hashTags;

    private static String hashtag1 = "test 1";
    private static String hashtag2 = "test 2";

    private static Account mention1;
    private static Account mention2;

    private static Account like1;
    private static Account like2;

    @BeforeClass
    public static void setUp() {
        owner = new Account("jules@nujules.nl", "kreutzer");
        kwet = new Kwet(defaultText, owner);
        kwet.setId(1);
        kwet.setCreationDate(defaultCreationDate);

        hashTags = new ArrayList<String>();
        hashTags.add(hashtag1);
        hashTags.add(hashtag2);

        kwet.setHashtags(hashTags);

        mention1 = new Account("test", "test");
        mention2 = new Account("mail", "password");

        ArrayList<Account> mentions = new ArrayList<Account>();
        mentions.add(mention1);
        mentions.add(mention2);

        kwet.setMentions(mentions);

        like1 = new Account("test", "test");
        like2 = new Account("mail", "password");

        ArrayList<Account> likes = new ArrayList<Account>();
        likes.add(like1);
        likes.add(like2);

        kwet.setLikes(likes);
    }

    @Test
    public void getText() {
        Assert.assertEquals(kwet.getText(), defaultText);
    }

    @Test
    public void setText() {
        String newText = "This is a new text";

        Kwet k = new Kwet(defaultText, owner);
        Assert.assertEquals(k.getText(), defaultText);

        k.setText(newText);

        Assert.assertEquals(k.getText(), newText);
    }

    @Test
    public void getCreationDate() {
        Assert.assertEquals(kwet.getCreationDate(), defaultCreationDate);
    }

    @Test
    public void setCreationDate() {
        Date newDate = new Date();
        newDate.setTime(1000);

        Kwet k = new Kwet(defaultText, owner);
        k.setCreationDate(defaultCreationDate);

        Assert.assertEquals(k.getCreationDate(), defaultCreationDate);

        k.setCreationDate(newDate);

        Assert.assertEquals(k.getCreationDate(), newDate);
    }

    @Test
    public void getOwner() {
        Assert.assertEquals(kwet.getOwner(), owner);
    }

    @Test
    public void setOwner() {
        Account account = new Account("juleskreutzer@me.com", "password");

        Kwet k = new Kwet(defaultText, owner);
        Assert.assertEquals(k.getOwner(), owner);

        k.setOwner(account);

        Assert.assertEquals(k.getOwner(), account);
    }

    @Test
    public void getHashtags() {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add(hashtag1);
        tags.add(hashtag2);

        Assert.assertEquals(kwet.getHashtags(), tags);
    }

    @Test
    public void setHashtags() {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("test 1");
        tags.add("test 2");

        Kwet k = new Kwet(defaultText, owner);
        k.setHashtags(tags);

        Assert.assertEquals(k.getHashtags(), tags);

        tags.add("test 3");
        k.setHashtags(tags);

        Assert.assertEquals(k.getHashtags(), tags);
    }

    @Test
    public void getMentions() {
        ArrayList<Account> mentions = new ArrayList<Account>();
        mentions.add(mention1);
        mentions.add(mention2);

        Assert.assertEquals(kwet.getMentions(), mentions);
    }

    @Test
    public void setMentions() {
        ArrayList<Account> mentions = new ArrayList<Account>();
        mentions.add(mention1);
        mentions.add(mention2);

        Account mention3 = new Account("asd", "asd");

        mentions.add(mention3);

        Kwet k = new Kwet(defaultText, owner);
        k.setMentions(mentions);

        Assert.assertEquals(k.getMentions(), mentions);
    }

    @Test
    public void getLikes() {
        ArrayList<Account> likes = new ArrayList<Account>();
        likes.add(like1);
        likes.add(like2);

        Assert.assertEquals(kwet.getLikes(), likes);
    }

    @Test
    public void setLikes() {
        ArrayList<Account> likes = new ArrayList<Account>();
        likes.add(like1);
        likes.add(like2);

        Account like3 = new Account("asd", "asd");

        likes.add(like3);

        Kwet k = new Kwet(defaultText, owner);
        k.setLikes(likes);

        Assert.assertEquals(k.getLikes(), likes);
    }

    @Test
    public void getId() {
        Assert.assertEquals(kwet.getId(), 1);
    }

    @Test
    public void setId() {
        long newId = 2;

        Kwet k = kwet;

        Assert.assertEquals(k.getId(), 1);

        k.setId(newId);

        Assert.assertEquals(k.getId(), newId);
    }
}
