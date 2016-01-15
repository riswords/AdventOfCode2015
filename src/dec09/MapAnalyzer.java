package dec09;

import java.util.ArrayList;
import java.util.HashMap;

public class MapAnalyzer {
    private HashMap<String, HashMap<String, Integer>> map;

    public MapAnalyzer(HashMap<String, HashMap<String, Integer>> map) {
        this.map = map;
    }

    public int getShortestTraversal() {
        ArrayList<String> unvisited = new ArrayList<String>(map.keySet());
        int min = 0;
        for(int i = 0; i < unvisited.size(); ++i) {
            String start = unvisited.remove(i);
            int nextMin = traverseMin(unvisited, start);
            unvisited.add(i, start);
            if(nextMin < min || min == 0)
                min = nextMin;
        }
        return min;
    }

    public int getLongestTraversal() {
        ArrayList<String> unvisited = new ArrayList<String>(map.keySet());
        int max = 0;
        for(int i = 0; i < unvisited.size(); ++i) {
            String start = unvisited.remove(i);
            int nextMax = traverseMax(unvisited, start);
            unvisited.add(i, start);
            if(nextMax > max || max == 0)
                max = nextMax;
        }
        return max;
    }

    public int traverseMin(ArrayList<String> unvisited, String currentLocation) {
        if(unvisited.isEmpty())
            return 0;
        int minDistance = 0;
        for(int i = 0; i < unvisited.size(); ++i) {
            String nextLocation = unvisited.remove(i);
            int distanceToNextLoc = map.get(currentLocation).get(nextLocation);
            int nextDistance = distanceToNextLoc + traverseMin(unvisited, nextLocation);
            unvisited.add(i, nextLocation);
            if(minDistance == 0 || nextDistance < minDistance)
                minDistance = nextDistance;
        }
        return minDistance;
    }

    public int traverseMax(ArrayList<String> unvisited, String currentLocation) {
        if(unvisited.isEmpty())
            return 0;
        int maxDistance = 0;
        for(int i = 0; i < unvisited.size(); ++i) {
            String nextLocation = unvisited.remove(i);
            int distanceToNextLoc = map.get(currentLocation).get(nextLocation);
            int nextDistance = distanceToNextLoc + traverseMax(unvisited, nextLocation);
            unvisited.add(i, nextLocation);
            if(maxDistance == 0 || nextDistance > maxDistance)
                maxDistance = nextDistance;
        }
        return maxDistance;
    }
}
