package com.join.template.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author CAOYOU
 * @Title: RandomUtil
 * @date 2019/8/2210:21
 */
public class RandomUtil {
    static String character = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    static Random random = new Random();

    public static int toInt(int start, int end) {
        return random.nextInt(end - start + 1) + start;
    }

    public static float toFloat() {
        return random.nextFloat();
    }

    public static long toLong() {
        return random.nextLong();
    }

    public static boolean toBoolean() {
        return random.nextBoolean();
    }

    public static byte toByte() {
        int nextInt = random.nextInt(character.length());
        return character.getBytes()[nextInt];
    }

    public static byte toDouble() {
        int nextInt = random.nextInt(character.length());
        return character.getBytes()[nextInt];
    }

    public static String toString(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int nextInt = random.nextInt(character.length());
            stringBuilder.append(character.charAt(nextInt));
        }
        return stringBuilder.toString();
    }


    public static char toCharacter() {
        int nextInt = random.nextInt(character.length());
        return character.charAt(nextInt);
    }

    public static Short toShort(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int nextInt = random.nextInt(character.length());
            stringBuilder.append(character.charAt(nextInt));
        }
        return new Short(stringBuilder.toString());
    }

    public static BigDecimal toBigDecimal() {
        int integer = random.nextInt(999999999);
        int radix = random.nextInt(9999);
        String str = integer + "." + radix;
        return new BigDecimal(str);
    }

    public static BigInteger toBigInteger() {
        int integer = random.nextInt(999999999);
        return new BigInteger(String.valueOf(integer));
    }

    public static Date toTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, random.nextInt(1971 - 2200 + 1) + 1971);
        calendar.set(Calendar.MONTH, random.nextInt(12));
        calendar.set(Calendar.DATE, random.nextInt(31));
        calendar.set(Calendar.HOUR, random.nextInt(24));
        calendar.set(Calendar.MARCH, random.nextInt(60));
        calendar.set(Calendar.SECOND, random.nextInt(60));
        return calendar.getTime();
    }

}
