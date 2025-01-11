package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateFakeData {
    static Faker faker = new Faker();

    public static String fakeFirstName() {
        return faker.name().firstName();
    }

    public static String fakeLastName() {
        return faker.name().lastName();
    }

    public static String fakeEmail() {
        return fakeFirstName() + randomNumber(100, 999) + "@test.tech";
    }

    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + min));
    }

    public static String generatePassword(int minLength, int maxLength,
                                          boolean specialChar, boolean smallLetter, boolean capitalLetter) {
        if (minLength > maxLength) {
            throw new IllegalArgumentException("Minimum length cannot be greater than maximum length.");
        }

        String specialCharacters = "!@#$%^&*()-_+=<>?";
        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        StringBuilder characterPool = new StringBuilder();
        SecureRandom random = new SecureRandom();
        List<Character> password = new ArrayList<>();

        // Add mandatory character types
        if (specialChar) {
            characterPool.append(specialCharacters);
            password.add(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
        }
        if (smallLetter) {
            characterPool.append(smallLetters);
            password.add(smallLetters.charAt(random.nextInt(smallLetters.length())));
        }
        if (capitalLetter) {
            characterPool.append(capitalLetters);
            password.add(capitalLetters.charAt(random.nextInt(capitalLetters.length())));
        }

        // Numbers are added by default
        characterPool.append(numbers);
        password.add(numbers.charAt(random.nextInt(numbers.length())));

        // Ensure the password meets the minimum length requirement
        int length = random.nextInt((maxLength - minLength) + 1) + minLength;
        while (password.size() < length) {
            password.add(characterPool.charAt(random.nextInt(characterPool.length())));
        }

        // Shuffle to randomize order
        Collections.shuffle(password, random);

        // Convert the list to a string
        StringBuilder passwordString = new StringBuilder();
        for (char ch : password) {
            passwordString.append(ch);
        }

        return passwordString.toString();
    }
}
