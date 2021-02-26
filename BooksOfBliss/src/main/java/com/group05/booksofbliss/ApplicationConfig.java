package com.group05.booksofbliss;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

//@BasicAuthenticationMechanismDefinition(realmName = "defaultRealm")
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login",
                errorPage = "/login-error"
        )
)
@ApplicationScoped
public class ApplicationConfig {
}
