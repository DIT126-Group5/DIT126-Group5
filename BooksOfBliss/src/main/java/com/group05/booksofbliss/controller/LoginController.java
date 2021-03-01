package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.view.LoginBackingBean;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;

@FacesConfig
@Data
@Named("loginController")
@RequestScoped
public class LoginController {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private FacesContext facesContext;

    @Inject
    LoginBackingBean loginBackingBean;

    public String login() throws IOException {
        Credential credential = new UsernamePasswordCredential(loginBackingBean.getUsername(), loginBackingBean.getPassword());
        AuthenticationStatus authenticationStatus = securityContext.authenticate(
                getHttpRequestFromFacesContext(),
                getHttpResponseFromFacesContext(),
                AuthenticationParameters.withParams()
                        .credential(credential));

        if (authenticationStatus.equals(SUCCESS)) {
            return "browse";
        } else if (authenticationStatus.equals(SEND_CONTINUE)) {
            facesContext.responseComplete();
        } else if (authenticationStatus.equals(SEND_FAILURE)) {
            facesContext.addMessage(null,
                    new FacesMessage(SEVERITY_ERROR, "Authentication failed", null));
        }
        return null;
    }

    private HttpServletRequest getHttpRequestFromFacesContext() {
        return (HttpServletRequest) facesContext
                .getExternalContext()
                .getRequest();
    }

    private HttpServletResponse getHttpResponseFromFacesContext() {
        return (HttpServletResponse) facesContext
                .getExternalContext()
                .getResponse();
    }

}
