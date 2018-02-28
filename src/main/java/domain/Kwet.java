package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 19-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: domain
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Kwet.FindAll", query = "SELECT k FROM Kwet k"),
        @NamedQuery(name = "Kwet.GetById", query = "SELECT k FROM Kwet k WHERE k.id = :id"),
        @NamedQuery(name = "Kwet.GetByText", query = "SELECT k FROM Kwet k WHERE k.text = :text"),
        @NamedQuery(name = "Kwet.GetByOwner", query = "SELECT k FROM Kwet k WHERE k.owner.id = :id"),
        @NamedQuery(name = "Kwet.GetByOwnerAndText", query = "SELECT k FROM Kwet k WHERE k.owner.id = :id AND k.text = :text")
})
@Table(name = "Kwet")
public class Kwet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private Date creationDate;

    @ManyToOne(optional = false)
    private Account owner;
    @ElementCollection
    private List<String> hashtags;
    @OneToMany
    private List<Account> mentions;
    @OneToMany
    private List<Account> likes;

    public Kwet() { }

    public Kwet(String text, Account owner) {
        this.text = text;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Account> getMentions() {
        return mentions;
    }

    public void setMentions(List<Account> mentions) {
        this.mentions = mentions;
    }

    public List<Account> getLikes() {
        return likes;
    }

    public void setLikes(List<Account> likes) {
        this.likes = likes;
    }
}
