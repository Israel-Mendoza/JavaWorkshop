package dev.artisra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Workshop {

    public static List<List<Integer>> getPairs(List<Integer> numbers, int target) {
        if (numbers == null) {
            throw new IllegalArgumentException("The numbers list must not be null");
        }

        if (target <= 0) {
            throw new IllegalArgumentException("The target must be greater than zero");
        }

        List<List<Integer>> result = new ArrayList<>();

        Set<Integer> visitedNumbers = new HashSet<>();

        for (int num : numbers) {
            int difference = target - num;
            if (visitedNumbers.contains(difference)) {
                List<Integer> pair = new ArrayList<>();
                pair.add(num);
                pair.add(difference);
                result.add(pair);
                visitedNumbers.remove(difference);
            } else {
                visitedNumbers.add(num);
            }
        }
        return result;
    }
}
