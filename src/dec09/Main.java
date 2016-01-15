package dec09;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<String, HashMap<String, Integer>> map = parseArguments(args);
        MapAnalyzer analyzer = new MapAnalyzer(map);
        System.out.println("Shortest path: " + analyzer.getShortestTraversal());
        System.out.println("Longest path: " + analyzer.getLongestTraversal());
    }

    public static HashMap<String, HashMap<String, Integer>> parseArguments(String[] args) {
        HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();
        Scanner scanner = new Scanner(String.join(" ", args));
        while(scanner.hasNext()) {
            String startCity = scanner.next();
            scanner.next(); // parse "to"
            String endCity = scanner.next();
            scanner.next(); // parse "="
            int distance = scanner.nextInt();
            HashMap<String, Integer> startMap = map.getOrDefault(startCity, new HashMap<String, Integer>());
            startMap.put(endCity, distance);
            map.put(startCity, startMap);

            HashMap<String, Integer> endMap = map.getOrDefault(endCity, new HashMap<String, Integer>());
            endMap.put(startCity, distance);
            map.put(endCity, endMap);
        }
        scanner.close();
        return map;
    }
}
