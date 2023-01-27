import java.util.*;
import java.util.stream.Collectors;

public class RomanConvertor {

    public RomanConvertor() {
    }

    public static void main(String args[]) {
        System.out.print(toRoman(92));
    }


    static Map<Integer, String> mapRoman = Map.ofEntries(
            Map.entry(1, "I"),
            Map.entry(4, "IV"),
            Map.entry(5, "V"),
            Map.entry(9, "IX"),
            Map.entry(10, "X"),
            Map.entry(40, "XL"),
            Map.entry(50, "L"),
            Map.entry(90, "XC"),
            Map.entry(100, "C"),
            Map.entry(400, "CD"),
            Map.entry(500, "D"),
            Map.entry(900, "CM"),
            Map.entry(1000, "M"));

    public static String toRoman(int n) {

        List<Integer> list = new ArrayList<>(mapRoman.keySet())
                .stream()
                .sorted()
                .collect(Collectors.toList());

        Collections.reverse(list);

        StringBuilder result = new StringBuilder();
        for (Integer integer : list) {

            int numberOfTimeIntFit = n / integer;

            if (numberOfTimeIntFit > 0) {
                result.append(mapRoman.get(integer).repeat(numberOfTimeIntFit));
                n = n % integer;
            }
        }


        return result.toString();
    }

    public static int fromRoman(String romanNumeral) {

        int result = 0;

        List<Integer> arrayOfNumberFromRoman = Arrays.stream(romanNumeral.split(""))
                .map(x -> {
                            for (Map.Entry<Integer, String> entry : mapRoman.entrySet()) {
                                if (Objects.equals(x, entry.getValue())) {
                                    return entry.getKey();
                                }
                            }

                            return null;
                        }
                ).collect(Collectors.toList());

        for (int i = arrayOfNumberFromRoman.size() - 1; i >= 0; i--) {
            if ((i - 1) >= 0 && arrayOfNumberFromRoman.get(i - 1) < arrayOfNumberFromRoman.get(i)) {
                result += arrayOfNumberFromRoman.get(i) - arrayOfNumberFromRoman.get(i - 1);
                i--;
            } else {
                result += arrayOfNumberFromRoman.get(i);
            }
        }
        return result;
    }


}
