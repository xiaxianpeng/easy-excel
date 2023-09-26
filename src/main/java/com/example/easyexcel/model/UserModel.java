package com.example.easyexcel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    @ExcelProperty("ID")
    private Integer id;
    @ExcelProperty("NAME")
    private String name;
    @ExcelProperty("SEX")
    private Integer sex;
    @ExcelProperty("BIRTHDAY")
    private Date birthday;

    public static List<UserModel> generateUserList(Integer count) {
        List<UserModel> users = new ArrayList<UserModel>();
        for (int i = 0; i < count; i++) {
            users.add(generateUser());
        }
        return users;
    }

    public static UserModel generateUser() {
        UserModel user = new UserModel();
        Random rand = new Random();
        // Generate random IDs in the range 0-100000
        user.setId(rand.nextInt(100001));
        // Generate a random string of length 10 for the name
        user.setName(generateRandomString(10));
        // Generate random age in the range 0-200
        user.setSex(rand.nextInt(201));
        // Set today's date
        user.setBirthday(generateRandomDateAfter2000());
        return user;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return result.toString();
    }

    private static Date generateRandomDateAfter2000() {
        GregorianCalendar gc = new GregorianCalendar();
        Random rand = new Random();
        //for years 2000 to 2021
        int year = 2000 + rand.nextInt(22);
        gc.set(GregorianCalendar.YEAR, year);
        //1-365
        int dayOfYear = rand.nextInt(gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR)) + 1;
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }
}
