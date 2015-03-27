package com.tpm.georeminder.domain;

import com.tpm.georeminder.domain.Account;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-26T16:39:22")
@StaticMetamodel(Authentication.class)
public class Authentication_ { 

    public static volatile SingularAttribute<Authentication, Integer> authenticationId;
    public static volatile SingularAttribute<Authentication, Account> account;
    public static volatile SingularAttribute<Authentication, String> Password;

}