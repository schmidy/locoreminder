package com.tpm.georeminder.domain;

import com.tpm.georeminder.domain.Account;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-26T16:39:22")
@StaticMetamodel(Reminder.class)
public class Reminder_ { 

    public static volatile SingularAttribute<Reminder, String> Type;
    public static volatile SingularAttribute<Reminder, String> Description;
    public static volatile SingularAttribute<Reminder, Integer> reminderId;
    public static volatile SingularAttribute<Reminder, Account> account;

}