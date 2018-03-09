package domain;

import domain.Account;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.0.v20170811-rNA", date="2018-03-09T14:44:52")
@StaticMetamodel(Kwet.class)
public class Kwet_ { 

    public static volatile SingularAttribute<Kwet, Account> owner;
    public static volatile ListAttribute<Kwet, String> hashtags;
    public static volatile ListAttribute<Kwet, Account> mentions;
    public static volatile SingularAttribute<Kwet, Long> id;
    public static volatile SingularAttribute<Kwet, String> text;
    public static volatile SingularAttribute<Kwet, Date> creationDate;
    public static volatile ListAttribute<Kwet, Account> likes;

}