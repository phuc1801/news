package com.vnnet.newscommon.utils;


import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.Objects;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isBlank(String value) {
        return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
    }

    public static String random(int length) {
//		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        String NumericString = "0123456789";
        int n = length;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (NumericString.length() * Math.random());
            sb.append(NumericString.charAt(index));
        }
        return sb.toString();
    }

    public static String getErrorMessage(String msg) {
        if(msg != null && msg.contains("com.microsoft.sqlserver.jdbc.SQLServerException:"))
            return msg.substring(msg.lastIndexOf("com.microsoft.sqlserver.jdbc.SQLServerException:") + 48).trim();

        return msg;
    }

    public static String convertToMoney(Object m) {
        DecimalFormat dFormat = new DecimalFormat();
        return dFormat.format(m);
    }

    private static String covertToString(String value) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("");
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Co loi khong the loai bo dau: " + value;
        }
    }

    public static String removeWhiteSpace(String value) {
        try {
            return Objects.requireNonNull(covertToString(value)).replaceAll("\\s+","").toLowerCase();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Co loi khong the loai bo dau cach: " + value;
        }
    }

}
