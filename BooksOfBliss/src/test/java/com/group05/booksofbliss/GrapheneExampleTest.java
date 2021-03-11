package com.group05.booksofbliss;

import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
public class GrapheneExampleTest {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    @Drone
    private WebDriver browser;
    @ArquillianResource
    private URL deploymentUrl;

//    @FindBy(id = "login:loginUsername")
//    private WebElement username;
//
//    @FindBy(id = "login:loginPassword")
//    private WebElement password;
//
//    @FindBy(id = "login:login")
//    private WebElement loginButton;
    @Test
    @RunAsClient
    public void example() {
//        browser.get(deploymentUrl.toExternalForm() + "login");
//
//        username.sendKeys("test123");
//        password.sendKeys("password123");
//
//        guardHttp(loginButton).click();

        browser.get("http://www.google.com");

        String pageTitle = browser.getTitle();
        Assert.assertEquals(pageTitle, "Google");
    }
}
