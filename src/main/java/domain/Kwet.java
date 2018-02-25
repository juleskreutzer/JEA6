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
        @NamedQuery(name = "Kwet.GetByText", query = "SELECT k FROM Kwet k WHERE k.text = :text")
})
@Table(name = "Kwet")
public class Kwet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String text;
    private Date creationDate;

    @ManyToOne(optional = false)
    private User owner;
    @ElementCollection
    private List<String> hashtags;
    @OneToMany
    private List<User> mentions;
    @OneToMany
    private List<User> likes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public Kwet() { }
}
