package com.company;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String multilineString = "                 Hello! \n \n young \n man hi.          ";
        // New implementations in String module
        System.out.println(multilineString);

        List<String> lines = multilineString.lines()
                .filter(line ->  line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());

        System.out.println("Strip statement: " + lines);

        List<String> linesStripTrailing = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::stripTrailing)
                .collect(Collectors.toList());
        System.out.println("Strip trailing: " + linesStripTrailing);

        List<String> linesStripLeading = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::stripLeading)
                .collect(Collectors.toList());
        System.out.println("Strip leading: " + linesStripLeading);

        // [323] Using VAR: including in lambda expressions;
        var result = lines.stream()
                .map((var s) -> s.toLowerCase())
                .collect(Collectors.toList());

        System.out.println(result);

        // [321] new HTTP client
        HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(new URI("https://postman-echo.com/get"))
                .GET().build();

        // Collections to array easier
        // Collections.toX
        List<String> namesList = Arrays.asList("Daniel", "", " ", "Augusto");
        String[] sampleArray = namesList.toArray(String[]::new);
        System.out.println(sampleArray);

        // Using predicate
        List withoutBlanks = namesList.stream()
                .filter(Predicate.not(String::isBlank)
                        .and(Predicate.not(String::isEmpty)))
                .collect(Collectors.toList());
        System.out.println(withoutBlanks);

        // Streams

        Optional<String> stream = namesList.stream().filter(element -> {
            System.out.println("filter() was called");
            return Predicate.not(String::isEmpty).test(element);
        }).map(element -> {
            System.out.println("map() was called");
            return element.toUpperCase();
        }).findFirst();


    }
}
