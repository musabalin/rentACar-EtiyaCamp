package com.etiya.rentACar.core.utilities;

public class ConvertLetter {

    public static String convertLetter(String name) {
        String word = name;
        String response = "";
        char[] oldLetter = new char[]{'İ', 'ı', 'ü', 'Ü', 'ç', 'Ç', 'Ğ', 'ğ', 'Ş', 'ş', 'ö', 'Ö'};
        char[] newLetter = new char[]{'I', 'i', 'u', 'U', 'c', 'C', 'G', 'g', 'S', 's', 'o', 'O',};

        for (int i = 0; i < oldLetter.length; i++) {
            word = word.replace(oldLetter[i], newLetter[i]);
        }
        response = word;
        return response;
    }
}
