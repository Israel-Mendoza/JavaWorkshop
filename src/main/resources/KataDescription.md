# Kata Description

**Find all pairs in an unsorted integer collection, which sum is equal to a given number.**

> ### Example:
>
> Collection: [1, 3, 9, 19, 5, 46, 4, 2, 7]
>
> Number to find: 6
>
> Output: [[4,2],[5,1]]

---

## Clarifying questions

1. What is the data type of the inputs? **Any collection of integers and an integer (target)**.
2. What is the expected data type of the output? **Any collection of collections of integers**.
2. Are there positive and negative numbers in the input list? **Only positive numbers**.
2. Are there duplicate numbers in the input list? **No. Every number is different**.

---

## Inputs and output:

**Inputs**:

- List of Integer “numbers” (could also use an array).
- Integer "target".

**Output**:

- List of List of Integer

---

## Resolution Approach:

1. First, we will check if the numbers list is null. If it is, it throws an IllegalArgumentException.
2. Then, we will check if the target value is less than or equal to 0. If it is, it throws an IllegalArgumentException.
3. We will create an empty list called result to store the pairs of numbers that add up to the target value.
4. We will create a Set called visitedNumbers to keep track of the numbers that have already been processed.
5. We will iterate through the numbers list. For each number num:
    1. It calculates the difference between the target value and the current num.
    2. If the visitedNumbers set contains the difference, it means that a pair has been found.
       The method then creates a new list with the num and the difference, adds it to the result list, and removes the
       difference from the visitedNumbers set (we won't need this anymore).
    3. If the visitedNumbers set does not contain the difference, the method adds the current num to the visitedNumbers
       set.
6. Finally, it returns the result list containing all the pairs of numbers that add up to the target value.

### Comments

> This approach is efficient because it uses a Set to keep track of the numbers that have already been processed, which
> allows the method to find pairs in a single pass through the numbers list.
>
> The time complexity of this solution is O(n), where n is the size of the numbers list.
>
> The method also includes some input validation checks to ensure that the numbers list is not null and the target value
> is greater than 0.
> This helps to prevent the method from throwing unexpected exceptions and makes it more robust.

---

### Pseudocode

```pseudocode
FUNCTION getPairs(numbers, target):
    IF numbers is NULL:
        THROW  Exception("Numbers list must not be null")
    IF target is less than or equal to 0:
        THROW Exception("Target must be greater than zero")
    
    SET result = empty list
    SET visitedNumbers = empty set
    
    FOR EACH num in numbers:
        SET difference = target - num
        IF visitedNumbers in difference:
            SET pair = empty list
            ADD num to pair
            ADD difference to pair
            ADD pair to result
            REMOVE difference from visitedNumbers
        ELSE:
            ADD num to visitedNumbers
    
    RETURN result
```

### Pseudocode.py :)

```python
def get_pairs(numbers: list[int], target: int) -> list[list[int]]:
    if numbers is None:
        raise ValueError("Numbers list must not be null")

    if target <= 0:
        raise ValueError("Target must be greater than zero")

    result: list[list[int]] = []
    visited_numbers: set[int] = set()

    for num in numbers:
        difference = target - num
        if difference in visited_numbers:
            result.append([difference, num])
            visited_numbers.remove(difference)
        else:
            visited_numbers.add(num)

    return result
```

