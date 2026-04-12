package com.opencart.automation.utilities;

import com.github.javafaker.Faker;
import com.opencart.automation.models.User;

import java.util.Random;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

//
//    public static String generateRandomEmail() {
//        return "testuser" + System.currentTimeMillis() + "@gmail.com";
//    }
//
//    public static String generatePhoneNumber() {
//        return "09" + String.format("%08d", random.nextInt(100000000));
//    }
//
//    public static String generateRandomPassword() {
//        return "Aa123456";   //mặc định
//    }
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


}
