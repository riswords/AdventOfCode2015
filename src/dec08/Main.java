package dec08;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    // TODO: Day 8 not working. Seems to be reading in \ and \\ as \ :(

    public static void main(String[] args) {
        Stream<ListItem> items = Arrays.stream(args).map(ListItem::new);
        IntStream stream = items.mapToInt(listItem -> listItem.getCodeLength() - listItem.getInMemoryLength());
        System.out.println(stream.sum());
    }
}
