package com.group05.booksofbliss;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

//@BasicAuthenticationMechanismDefinition(realmName = "defaultRealm")
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login",
                errorPage = "/login-error"
        )
)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {
}
