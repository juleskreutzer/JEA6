package domain;

import util.ROLE;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 19-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: domain
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
@Entity
@Table(name = "SecurityGroup")
public class Group implements Serializable {

    @Id
    private String groupname;

    @ManyToMany
    @JoinTable(name="USER_GROUP",
            joinColumns = @JoinColumn(name = "groupname",
                    referencedColumnName = "groupname"),
            inverseJoinColumns = @JoinColumn(name = "username",
                    referencedColumnName = "username"))
    private List<Account> users;

    public Group() {
    }

    public String getGroupName() {
        return groupname;
    }

    public void setGroupName(ROLE groupName) {
        this.groupname = groupName.name();
    }

    public List<Account> getUsers() {
        return users;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
    }
}
