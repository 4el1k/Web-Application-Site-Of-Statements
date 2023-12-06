package ru.itis.util.convertor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonConvertor {
    public static String toJson(List<String> arr) {
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (int i = 0; i < arr.size() - 1; i++) {
            result.append(arr.get(i) + "_");
        }
        result.append(arr.get(arr.size() - 1));
        result.append("}");

        return result.toString();
    }

    public static List<String> fromJson(String json) {
        ArrayList<String> result = (ArrayList<String>) Arrays.stream(json.substring(1, json.length()-1)
                .split("_")).collect(Collectors.toList());

        return result;
    }
}
