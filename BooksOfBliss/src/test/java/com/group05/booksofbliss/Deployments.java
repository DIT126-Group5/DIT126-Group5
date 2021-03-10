package com.group05.booksofbliss;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class Deployments {

    public static WebArchive defaultArchive() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
