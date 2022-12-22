package pers.joy.utils;

import java.util.Locale;

public class DatabaseUtils {

    /**
     * auto increment (8 digits)
     */
    public static String generateNo(String currentMaxNo) {
        int nextNo = Integer.parseInt(currentMaxNo)+1;
        return String.format("%08d", nextNo);
    }

    public static String generateUsername(String name) {
        String[] names = name.split(" ");
        StringBuilder username = new StringBuilder();
        for (String n: names) {
            username.append(n.toLowerCase(Locale.ROOT));
        }
        return username.toString();
    }


    /**
     * the last two digits of the year(2) + auto increment(6)
     */
    public static String generateNoWithYear(String year, String currentMaxNo) {
        int nextNo = Integer.parseInt(currentMaxNo)+1;
        assert nextNo<1000000;
        year = year.substring(2, 4);
        return year+String.format("%06d", nextNo);
    }

    /**
     * department no(4) + auto increment(4)
     */
    public static String generateNoWithDept(String departmentNo, String currentMaxNo) {
        int nextNo = Integer.parseInt(currentMaxNo)+1;
        assert nextNo<10000;
        return departmentNo+String.format("%04d", nextNo);
    }
}
