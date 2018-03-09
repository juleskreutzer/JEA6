package domain;

import domain.Account;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import util.ROLE;

@Generated(value="EclipseLink-2.7.0.v20170811-rNA", date="2018-03-09T14:44:52")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile ListAttribute<Account, Account> followers;
    public static volatile SingularAttribute<Account, ROLE> role;
    public static volatile SingularAttribute<Account, String> web;
    public static volatile ListAttribute<Account, Account> following;
    public static volatile SingularAttribute<Account, String> fullName;
    public static volatile SingularAttribute<Account, String> bio;
    public static volatile SingularAttribute<Account, String> location;
    public static volatile SingularAttribute<Account, Long> id;
    public static volatile SingularAttribute<Account, String> profileImage;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile SingularAttribute<Account, String> username;

}