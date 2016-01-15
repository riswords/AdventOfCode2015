package dec02;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Present> presents = new ArrayList<Present>();
        for(String dimensions : args) {
            presents.add(new Present(dimensions));
        }

        int totalWrappingPaper = 0;
        int totalRibbon = 0;
        for(Present present : presents) {
            totalWrappingPaper += present.calculateTotalWrappingPaper();
            totalRibbon += present.calculateTotalRibbon();
        }
        System.out.println("Total Wrapping Paper: " + totalWrappingPaper);
        System.out.println("Total Ribbon: " + totalRibbon);
    }

}
