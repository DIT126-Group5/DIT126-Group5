package com.group05.booksofbliss;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@BasicAuthenticationMechanismDefinition(realmName = "defaultRealm")
//@CustomFormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/login",
//                errorPage = "/login-error"
//        )
//)
@ApplicationScoped
public class ApplicationConfig {
}
