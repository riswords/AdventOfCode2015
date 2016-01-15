package dec05;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Stream<String> stream = Arrays.stream(args);
        // long countNiceWords = stream.filter(StringJudgement::isNice).count();
        long countNiceWords = stream.filter(StringJudgement::isNicer).count();
        System.out.println("Number of nice words: " + countNiceWords);
    }

}
