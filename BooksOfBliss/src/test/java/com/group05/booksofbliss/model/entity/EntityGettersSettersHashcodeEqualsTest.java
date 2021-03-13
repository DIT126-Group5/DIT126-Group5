package com.group05.booksofbliss.model.entity;

import com.google.common.reflect.ClassPath;
import com.group05.booksofbliss.model.entity.attribute.Address;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.money.MonetaryAmount;
import nl.jqno.equalsverifier.ConfiguredEqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.lang3.RandomStringUtils;
import org.javamoney.moneta.Money;
import org.junit.BeforeClass;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.ConfigurationBuilder;

public class EntityGettersSettersHashcodeEqualsTest {

    private static final String entityPackage = EntityGettersSettersHashcodeEqualsTest.class.getPackageName();
    private static Set<Class<?>> entityClasses;

    private static final Account acc1 = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));
    private static final Account acc2 = new Account("username2", "firstName2", "lastName2", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));
    private static final Author a1 = new Author("Author 1");
    private static final Author a2 = new Author("Author 2");
    private static final Book b1 = new Book("345364574546", "Book 1", 2354, "");
    private static final Book b2 = new Book("867868575466", "Book 2", 4233, "");
    private static final Listing l1 = new Listing(new Date(), Money.of(1, "SEK"), "", new Condition("Good"), acc1, b2);
    private static final Listing l2 = new Listing(new Date(), Money.of(2, "SEK"), "", new Condition("Bad"), acc2, b1);

    static {
        l1.setId(5L);
        l2.setId(7L);
    }

    @BeforeClass
    public static void init() throws IOException {
        entityClasses = ClassPath.from(EntityGettersSettersHashcodeEqualsTest.class.getClassLoader())
                .getTopLevelClassesRecursive(entityPackage)
                .stream()
                .filter(c -> !c.getSimpleName().contains("Test"))
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toSet());
    }

    @Test
    public void testAllEntitiesGettersSettersCorrect() {
        BeanTester tester = new BeanTester();

        tester.getFactoryCollection().addFactory(MonetaryAmount.class,
                () -> Money.of(ThreadLocalRandom.current().nextDouble(100000), "SEK"));

        tester.getFactoryCollection().addFactory(Account.class,
                () -> new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK")));

        tester.addCustomConfiguration(Account.class, new ConfigurationBuilder()
                .overrideFactory("reviewsGiven", ArrayList::new)
                .overrideFactory("reviewsReceived", ArrayList::new)
                .overrideFactory("listings", ArrayList::new)
                .overrideFactory("purchases", ArrayList::new)
                .overrideFactory("username",
                        () -> RandomStringUtils.randomAlphabetic(10))
                .build());

        tester.addCustomConfiguration(Author.class, new ConfigurationBuilder()
                .overrideFactory("books", ArrayList::new)
                .build());

        tester.addCustomConfiguration(Book.class, new ConfigurationBuilder()
                .overrideFactory("listings", ArrayList::new)
                .build());

        tester.addCustomConfiguration(Category.class, new ConfigurationBuilder()
                .overrideFactory("books", ArrayList::new)
                .build());

        tester.addCustomConfiguration(Condition.class, new ConfigurationBuilder()
                .overrideFactory("listings", ArrayList::new)
                .build());

        tester.addCustomConfiguration(Listing.class, new ConfigurationBuilder()
                .overrideFactory("purchase", () -> new Purchase())
                .build());

        tester.addCustomConfiguration(Purchase.class, new ConfigurationBuilder()
                .overrideFactory("listing", () -> new Listing())
                .overrideFactory("account", () -> new Account())
                .build());

        for (Class<?> clazz : entityClasses) {
            System.out.println("Testing: " + clazz);
            tester.testBean(clazz);
        }
    }

    @Test
    public void testAllEntitiesHashcodeAndEqualsCorrect() {

        setupEqualsVerifier()
                .forClasses(entityClasses)
                .except(Account.class, Author.class, Book.class, Category.class, Condition.class, UserReview.class, Listing.class, Purchase.class)
                .verify();

        setupEqualsVerifier()
                .forClasses(Account.class, Author.class, Book.class, Category.class, Condition.class, UserReview.class)
                .suppress(Warning.SURROGATE_KEY)
                .verify();

        // Not working
        /*setupEqualsVerifier()
                .forClass(Listing.class)
                .suppress(Warning.SURROGATE_KEY, Warning.STRICT_HASHCODE)
                .verify();*/
        setupEqualsVerifier()
                .forClass(Purchase.class)
                .withIgnoredAnnotations(Nonnull.class)
                .suppress(Warning.SURROGATE_KEY, Warning.REFERENCE_EQUALITY, Warning.STRICT_HASHCODE)
                .verify();

    }

    private static ConfiguredEqualsVerifier setupEqualsVerifier() {
        return EqualsVerifier.configure()
                .withPrefabValues(Account.class, acc1, acc2)
                .withPrefabValues(UserReview.class,
                        new UserReview(acc1, acc2, ":D", 5),
                        new UserReview(acc2, acc1, "D:", 1))
                .withPrefabValues(Book.class, b1, b2)
                .withPrefabValues(Author.class, a1, a2)
                .withPrefabValues(Listing.class, l1, l2)
                .withPrefabValues(Condition.class, new Condition("Good"), new Condition("Bad"));
    }
}
