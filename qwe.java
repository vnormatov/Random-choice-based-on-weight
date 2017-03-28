import java.util.*;

public class qwe {
    public static void main(String[] args) {
        HashMap<String, Integer> input = new HashMap<>();
        input.put("Ukraine", 9);
        input.put("China", 1);
        for (int i = 0; i < 100; i++) {
        input.put("USA"+i, i);
        }
        System.out.println("-----------------------------v1----------------------");
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            superPuper(input);
        }
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime) * 10e-9);
        System.out.println("-----------------------------v2----------------------");
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            superPuperV2(input);
        }
        stopTime = System.nanoTime();
        System.out.println((stopTime - startTime) * 10e-9);


        System.out.println("-----------------------------v3----------------------");
        double sum = getSum(input);
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            superPuperV3(sum, input);
        }
        stopTime = System.nanoTime();
        System.out.println((stopTime - startTime) * 10e-9);

        System.out.println("-----------------------------v4----------------------");
        startTime = System.nanoTime();
        sum = getSum(input);
        TreeMap<String, Double> map = getMapWithWeight(input, sum);
        for (int i = 0; i < 1000000; i++) {
            search(map);
        }
        stopTime = System.nanoTime();
        System.out.println((stopTime - startTime) * 10e-9);

        System.out.println("-----------------------------v5----------------------");
        sum = getSum(input);
        map = getMapWithWeight(input, sum);
        for (int i = 0; i < 1000000; i++) {
//            binarySearch(map);TODO
        }
        stopTime = System.nanoTime();
        System.out.println((stopTime - startTime) * 10e-9);

    }


    private static String search(TreeMap<String, Double> map) {
        String country = "";
        double random = Math.random();

        for (Map.Entry<String, Double> stringDoubleEntry : map.entrySet()) {
            if (random <= stringDoubleEntry.getValue()) {
                country = stringDoubleEntry.getKey();
                break;
            }
        }

        return country;
    }

    private static TreeMap<String, Double> getMapWithWeight(HashMap<String, Integer> input, double sum) {
        TreeMap<String, Double> map = new TreeMap<>();
        double currentValue = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : input.entrySet()) {
            currentValue += stringIntegerEntry.getValue() / sum;
            map.put(stringIntegerEntry.getKey(), currentValue);
        }

        return map;
    }

    private static String superPuperV3(double sum, HashMap<String, Integer> input) {

        String country = "";
        double random = Math.random();
        double currentValue = 0;
        double previousValue = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : input.entrySet()) {
            currentValue += stringIntegerEntry.getValue() / sum;
            if (previousValue <= random && random <= currentValue) {
                country = stringIntegerEntry.getKey();
                break;
            }
            previousValue = currentValue;
        }
        return country;

    }

    public static String superPuperV2(HashMap<String, Integer> input) {

        double random = Math.random();
        String country = "";
        double amountElementsInMap = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : input.entrySet()) {
            amountElementsInMap += stringIntegerEntry.getValue();
        }

        double currentValue = 0;
        double previousValue = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : input.entrySet()) {
            currentValue += stringIntegerEntry.getValue() / amountElementsInMap;
            if (previousValue <= random && random <= currentValue) {
                country = stringIntegerEntry.getKey();
                break;
            }
            previousValue = currentValue;
        }
        return country;
    }


    public static String superPuper(HashMap<String, Integer> input) {

        double random = Math.random();
        String country = "";
        double coursor = 0;
        int sum = 0;
        for (Integer integer : input.values()) {
            sum += integer;
        }
        for (Map.Entry<String, Integer> stringIntegerEntry : input.entrySet()) {
            coursor += (double) stringIntegerEntry.getValue() / sum;
            if (coursor >= random) {
                country = stringIntegerEntry.getKey();
                break;
            }
        }
        return country;
    }


    public static Double getSum(HashMap<String, Integer> input) {
        int sum = 0;
        for (Integer integer : input.values()) {
            sum += integer;
        }
        return (double) sum;
    }
}
