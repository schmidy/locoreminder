package com.tpm.georeminder.domain;

import com.tpm.georeminder.domain.Reminder;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-26T16:39:22")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile ListAttribute<Account, Reminder> Reminders;
    public static volatile SingularAttribute<Account, Integer> account_id;
    public static volatile SingularAttribute<Account, String> UserName;
    public static volatile SingularAttribute<Account, String> Address;
    public static volatile SingularAttribute<Account, String> FullName;
    public static volatile SingularAttribute<Account, String> EmailAddress;

}