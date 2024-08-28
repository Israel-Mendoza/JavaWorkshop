package dev.artisra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;

class WorkshopTest {

    @Test
    public void throws_IllegalArgumentException_when_passed_list_is_null() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var result = Workshop.getPairs(null, 0);
        });
    }

    @Test
    public void throws_IllegalArgumentException_when_passed_target_is_negative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var result = Workshop.getPairs(List.of(1, 2, 3), -5);
        });
    }

    @Test
    public void throws_IllegalArgumentException_when_passed_target_is_zero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var result = Workshop.getPairs(List.of(1, 2, 3), 0);
        });
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void returns_a_non_null_list(List<Integer> input, int target, int expected) {
        List<List<Integer>> actualResult = Workshop.getPairs(input, target);
        Assertions.assertNotNull(actualResult);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void returns_n_number_of_pairs(List<Integer> input, int target, int numOfExpectedPairs) {
        List<List<Integer>> actualResult = Workshop.getPairs(input, target);
        Assertions.assertEquals(numOfExpectedPairs, actualResult.size());
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void finds_pairs_or_empty(List<Integer> input, int target, int expected) {
        List<List<Integer>> actualResult = Workshop.getPairs(input, target);
        for (List<Integer> pair : actualResult) {
            if (!pair.isEmpty()) Assertions.assertEquals(pair.size(), 2);
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void finds_non_duplicate_pairs(List<Integer> input, int target, int expected) {
        List<List<Integer>> actualResult = Workshop.getPairs(input, target);
        for (List<Integer> pair : actualResult) {
            Assertions.assertNotEquals(pair.get(0), pair.get(1));
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void result_pairs_sum_equals_target_number(List<Integer> input, int target, int expected) {
        List<List<Integer>> actualResult = Workshop.getPairs(input, target);
        for (List<Integer> pair : actualResult) {
            var actualSum = pair.stream().mapToInt(Integer::intValue).sum();
            Assertions.assertEquals(target, actualSum);
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void integers_in_result_pairs_are_strictly_different(List<Integer> input, int target, int expected) {
        List<List<Integer>> actualResult = Workshop.getPairs(input, target);
        int numberOfNumbers = actualResult.size() * 2;
        var uniqueNumbersSize = actualResult.stream().flatMap(List::stream).collect(Collectors.toSet()).size();
        Assertions.assertEquals(numberOfNumbers, uniqueNumbersSize);
    }

    private static List<Arguments> dataProvider() {
        return List.of(
                Arguments.of(List.of(), 2, 0),
                Arguments.of(List.of(1, 3, 9, 19, 5, 46, 4, 2, 7), 6, 2),
                Arguments.of(List.of(1, 0, 3, 10, 5, 12, 2, 4, 7), 7, 3),
                Arguments.of(List.of(0, 3, 9, 19, 5, 46, 4, 2, 1), 5, 3),
                Arguments.of(List.of(1, 3, 9, 8, 5, 6, 4, 2, 7, 10), 9, 4),
                Arguments.of(List.of(1, 3, 9, 19, 5, 46, 4, 2, 7), 7, 2)
        );
    }
}