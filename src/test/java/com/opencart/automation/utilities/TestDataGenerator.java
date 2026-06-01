package com.opencart.automation.utilities;

import com.github.javafaker.Faker;
import com.opencart.automation.models.User;

import java.util.Random;

public class TestDataGenerator {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
//
    public static String generateRandomEmail() {
        return "testuser" + System.currentTimeMillis() + "@gmail.com";
    }
//
//    public static String generatePhoneNumber() {
//        return "09" + String.format("%08d", random.nextInt(100000000));
//    }
//
//    public static String generateRandomPassword() {
//        return "Aa123456";   //mặc định
//    }

    // Generate user for regisration with default password
    public static User generateUserWithDefaultPwd() {
        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail("testuser" + System.currentTimeMillis() + "@gmail.com");
        user.setTelephone(faker.phoneNumber().cellPhone().replaceAll("[^0-9]", ""));
        String defaultPassword = "Aa123456";
        user.setPassword(defaultPassword);
        user.setConfirmPassword(defaultPassword);
        return user;
    }
    public static User generateUserWithDuplicateEmail() {
        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        //đang fix cứng email để test case RG_04, sau này có thể sửa thành tham số để linh hoạt hơn
        user.setEmail("anhnt@gmail.com");
        user.setTelephone(faker.phoneNumber().cellPhone().replaceAll("[^0-9]", ""));
        String defaultPassword = "Aa123456";
        user.setPassword(defaultPassword);
        user.setConfirmPassword(defaultPassword);
        return user;
    }

    public static User generateUserWithMismatchPwd() {
        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail("testuser" + System.currentTimeMillis() + "@gmail.com");
        user.setTelephone(faker.phoneNumber().cellPhone().replaceAll("[^0-9]", ""));
        String defaultPassword = "Aa123456";
        user.setPassword(defaultPassword);
        user.setConfirmPassword(defaultPassword + "Mismatch");
        return user;
    }

    // Data search keyword
    private static final String[] EXACT_KEYWORDS = {
            "MacBook Air",
            "Nikon D300",
            "Canon EOS 5D"
    };
    private static final String[] INVALID_KEYWORDS = {
            "abcde465",
            "test test126",
            "1627777xyz"
    };

    private static final String[] INSENSITIVE_KEYWORDS = {
            "MACBook AIR",
            "NIKON D3",
            "canon eos 5d"
    };
    private static final String[] BASIC_PRODUCTS = {
            "MacBook",
            "iPhone",
//            "Apple Cinema 30\"",
//            "Canon EOS 5D"
    };
    // Get random data
    public static String getRandomExactKeyword() {
        return EXACT_KEYWORDS[random.nextInt(EXACT_KEYWORDS.length)];
    }

    public static String getRandomInvalidKeyword() {
        return INVALID_KEYWORDS[random.nextInt(INVALID_KEYWORDS.length)];
    }

    public static String getRandomInsensitiveKeyword() {
        return INSENSITIVE_KEYWORDS[random.nextInt(INSENSITIVE_KEYWORDS.length)];
    }

    public static String getRandomBasicProducts() {
        return BASIC_PRODUCTS[random.nextInt(BASIC_PRODUCTS.length)];
    }

}
