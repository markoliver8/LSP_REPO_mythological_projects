package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Assignment 6: JUnit 5 tests for IntegerSet.
 *
 * Each test creates its own objects and verifies the expected behavior.
 */
public class IntegerSetTest {

    // ===== clear() =====

    @Test
    public void testClearClearsPopulatedSet() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(3);

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    @Test
    public void testClearClearsEmptySet() {
        IntegerSet set = new IntegerSet();

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    // ===== length() =====

    @Test
    public void testLengthCountsUniqueElements() {
        IntegerSet set = new IntegerSet();
        set.add(4);
        set.add(4);
        set.add(7);
        set.add(7);
        set.add(9);

        assertEquals(3, set.length());
    }

    @Test
    public void testLengthEmptySetLength() {
        IntegerSet set = new IntegerSet();

        assertEquals(0, set.length());
    }

    // ===== equals() =====

    @Test
    public void testEqualsIgnoresInsertionOrder() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);
        first.add(3);

        IntegerSet second = new IntegerSet();
        second.add(3);
        second.add(1);
        second.add(2);

        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }

    @Test
    public void testEqualsDetectsDifferentContents() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(1);
        second.add(3);

        assertFalse(first.equals(second));
    }

    @Test
    public void testEqualsEmptySetsEqual() {
        IntegerSet first = new IntegerSet();
        IntegerSet second = new IntegerSet();

        assertTrue(first.equals(second));
    }

    @Test
    public void testEqualsSameObjectAndDifferentSizes() {
        IntegerSet first = new IntegerSet();
        first.add(5);
        first.add(10);

        IntegerSet second = new IntegerSet();
        second.add(5);

        assertTrue(first.equals(first));
        assertFalse(first.equals(second));
        assertFalse(second.equals(first));
    }

    // ===== contains() =====

    @Test
    public void testContainsPresentAndAbsent() {
        IntegerSet set = new IntegerSet();
        set.add(12);
        set.add(-4);

        assertTrue(set.contains(12));
        assertTrue(set.contains(-4));
        assertFalse(set.contains(7));
    }

    @Test
    public void testContainsEmptySetContainsNothing() {
        IntegerSet set = new IntegerSet();

        assertFalse(set.contains(0));
        assertFalse(set.contains(100));
    }

    // ===== largest() =====

    @Test
    public void testLargestNegativeAndPositiveValues() {
        IntegerSet set = new IntegerSet();
        set.add(-10);
        set.add(0);
        set.add(25);
        set.add(-2);

        assertEquals(25, set.largest());
    }

    @Test
    public void testLargestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();

        assertThrows(IllegalStateException.class, () -> set.largest());
    }

    // ===== smallest() =====

    @Test
    public void testSmallestNegativeAndPositiveValues() {
        IntegerSet set = new IntegerSet();
        set.add(-10);
        set.add(0);
        set.add(25);
        set.add(-2);

        assertEquals(-10, set.smallest());
    }

    @Test
    public void testSmallestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();

        assertThrows(IllegalStateException.class, () -> set.smallest());
    }

    // ===== add() =====

    @Test
    public void testAddIgnoresDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(8);
        set.add(8);
        set.add(8);

        assertEquals(1, set.length());
        assertTrue(set.contains(8));
    }

    @Test
    public void testAddHandlesIntegerBoundaries() {
        IntegerSet set = new IntegerSet();
        set.add(Integer.MIN_VALUE);
        set.add(Integer.MAX_VALUE);
        set.add(0);

        assertEquals(3, set.length());
        assertTrue(set.contains(Integer.MIN_VALUE));
        assertTrue(set.contains(Integer.MAX_VALUE));
        assertTrue(set.contains(0));
    }

    // ===== remove() =====

    @Test
    public void testRemoveRemovesExistingElement() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(3);

        set.remove(2);

        assertFalse(set.contains(2));
        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertEquals(2, set.length());
    }

    @Test
    public void testRemoveIgnoresAbsentElement() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(3);

        set.remove(9);

        assertEquals(2, set.length());
        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertFalse(set.contains(9));
    }

    // ===== union() =====

    @Test
    public void testUnionOverlappingSets() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);
        first.add(3);

        IntegerSet second = new IntegerSet();
        second.add(3);
        second.add(4);
        second.add(5);

        IntegerSet result = first.union(second);

        assertEquals(5, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
    }

    @Test
    public void testUnionEmptyAndSameOperand() {
        IntegerSet populated = new IntegerSet();
        populated.add(2);
        populated.add(4);

        IntegerSet empty = new IntegerSet();
        IntegerSet withEmpty = populated.union(empty);
        IntegerSet withSelf = populated.union(populated);

        assertTrue(withEmpty.equals(populated));
        assertTrue(withSelf.equals(populated));
    }

    @Test
    public void testUnionDoesNotMutateInputs() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(3);

        IntegerSet firstBefore = new IntegerSet();
        firstBefore.add(1);
        firstBefore.add(2);
        IntegerSet secondBefore = new IntegerSet();
        secondBefore.add(2);
        secondBefore.add(3);

        IntegerSet result = first.union(second);

        assertTrue(first.equals(firstBefore));
        assertTrue(second.equals(secondBefore));
        assertTrue(result.equals(firstBefore.union(secondBefore)));

        result.add(99);
        assertFalse(first.contains(99));
        assertFalse(second.contains(99));
        assertFalse(first.equals(result));
        assertFalse(second.equals(result));
    }

    // ===== intersect() =====

    @Test
    public void testIntersectOverlappingSets() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);
        first.add(3);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(3);
        second.add(4);

        IntegerSet result = first.intersect(second);

        assertEquals(2, result.length());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertFalse(result.contains(1));
        assertFalse(result.contains(4));
    }

    @Test
    public void testIntersectDisjointAndSameOperand() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(3);
        second.add(4);

        IntegerSet disjointResult = first.intersect(second);
        IntegerSet selfResult = first.intersect(first);

        assertTrue(disjointResult.isEmpty());
        assertTrue(selfResult.equals(first));
    }

    @Test
    public void testIntersectIndependentResult() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(3);

        IntegerSet result = first.intersect(second);
        result.add(99);

        assertTrue(first.contains(1));
        assertTrue(first.contains(2));
        assertFalse(first.contains(99));
        assertTrue(second.contains(2));
        assertTrue(second.contains(3));
        assertFalse(second.contains(99));
    }

    // ===== diff() =====

    @Test
    public void testDiffDirectionMatters() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);
        first.add(3);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(3);
        second.add(4);

        IntegerSet firstMinusSecond = first.diff(second);
        IntegerSet secondMinusFirst = second.diff(first);

        assertEquals(1, firstMinusSecond.length());
        assertTrue(firstMinusSecond.contains(1));
        assertFalse(firstMinusSecond.contains(2));

        assertEquals(1, secondMinusFirst.length());
        assertTrue(secondMinusFirst.contains(4));
        assertFalse(secondMinusFirst.contains(3));
    }

    @Test
    public void testDiffEmptyAndSameOperand() {
        IntegerSet populated = new IntegerSet();
        populated.add(5);
        populated.add(6);

        IntegerSet empty = new IntegerSet();
        IntegerSet minusEmpty = populated.diff(empty);
        IntegerSet minusSelf = populated.diff(populated);

        assertTrue(minusEmpty.equals(populated));
        assertTrue(minusSelf.isEmpty());
    }

    @Test
    public void testDiffIndependentResult() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);
        first.add(3);

        IntegerSet second = new IntegerSet();
        second.add(2);

        IntegerSet result = first.diff(second);
        result.add(99);

        assertTrue(first.contains(1));
        assertTrue(first.contains(2));
        assertTrue(first.contains(3));
        assertFalse(first.contains(99));
        assertTrue(second.contains(2));
        assertFalse(second.contains(99));
    }

    // ===== complement() =====

    @Test
    public void testComplementReverseDifference() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(3);
        second.add(4);

        IntegerSet result = first.complement(second);

        assertEquals(2, result.length());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertFalse(result.contains(1));
        assertFalse(result.contains(2));
    }

    @Test
    public void testComplementEmptyOperands() {
        IntegerSet empty = new IntegerSet();
        IntegerSet populated = new IntegerSet();
        populated.add(1);
        populated.add(2);

        assertTrue(empty.complement(populated).equals(populated));
        assertTrue(populated.complement(empty).isEmpty());
        assertTrue(empty.complement(empty).isEmpty());
    }

    @Test
    public void testComplementIndependentResult() {
        IntegerSet first = new IntegerSet();
        first.add(1);

        IntegerSet second = new IntegerSet();
        second.add(1);
        second.add(2);
        second.add(3);

        IntegerSet result = first.complement(second);
        result.add(99);

        assertTrue(first.contains(1));
        assertFalse(first.contains(99));
        assertTrue(second.contains(1));
        assertTrue(second.contains(2));
        assertTrue(second.contains(3));
        assertFalse(second.contains(99));
    }

    // ===== isSubsetOf() =====

    @Test
    public void testIsSubsetOfProperSubset() {
        IntegerSet smaller = new IntegerSet();
        smaller.add(1);
        smaller.add(2);

        IntegerSet larger = new IntegerSet();
        larger.add(1);
        larger.add(2);
        larger.add(3);

        assertTrue(smaller.isSubsetOf(larger));
        assertFalse(larger.isSubsetOf(smaller));
    }

    @Test
    public void testIsSubsetOfNotSubset() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(4);

        IntegerSet second = new IntegerSet();
        second.add(1);
        second.add(2);
        second.add(3);

        assertFalse(first.isSubsetOf(second));
    }

    @Test
    public void testIsSubsetOfEqualSets() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(1);

        assertTrue(first.isSubsetOf(second));
        assertTrue(second.isSubsetOf(first));
    }

    @Test
    public void testIsSubsetOfEmptyCases() {
        IntegerSet empty = new IntegerSet();
        IntegerSet populated = new IntegerSet();
        populated.add(7);

        assertTrue(empty.isSubsetOf(populated));
        assertFalse(populated.isSubsetOf(empty));
        assertTrue(empty.isSubsetOf(empty));
    }

    // ===== isDisjoint() =====

    @Test
    public void testIsDisjointNoOverlap() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(3);
        second.add(4);

        assertTrue(first.isDisjoint(second));
        assertTrue(second.isDisjoint(first));
    }

    @Test
    public void testIsDisjointOverlap() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(3);

        assertFalse(first.isDisjoint(second));
        assertFalse(second.isDisjoint(first));
    }

    @Test
    public void testIsDisjointEmptyAndSameOperand() {
        IntegerSet empty = new IntegerSet();
        IntegerSet populated = new IntegerSet();
        populated.add(1);
        populated.add(2);

        assertTrue(empty.isDisjoint(populated));
        assertTrue(populated.isDisjoint(empty));
        assertFalse(populated.isDisjoint(populated));
        assertTrue(empty.isDisjoint(empty));
    }

    // ===== symmetricDiff() =====

    @Test
    public void testSymmetricDiffPartiallyOverlappingSets() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);
        first.add(3);

        IntegerSet second = new IntegerSet();
        second.add(3);
        second.add(4);
        second.add(5);

        IntegerSet result = first.symmetricDiff(second);

        assertEquals(4, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
        assertFalse(result.contains(3));
    }

    @Test
    public void testSymmetricDiffIdenticalSets() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(1);

        assertTrue(first.symmetricDiff(second).isEmpty());
    }

    @Test
    public void testSymmetricDiffEmptyAndDisjointSets() {
        IntegerSet empty = new IntegerSet();
        IntegerSet populated = new IntegerSet();
        populated.add(1);
        populated.add(2);

        IntegerSet withEmpty = empty.symmetricDiff(populated);
        assertTrue(withEmpty.equals(populated));

        IntegerSet first = new IntegerSet();
        first.add(3);
        IntegerSet second = new IntegerSet();
        second.add(4);

        IntegerSet disjointResult = first.symmetricDiff(second);
        assertEquals(2, disjointResult.length());
        assertTrue(disjointResult.contains(3));
        assertTrue(disjointResult.contains(4));
    }

    @Test
    public void testSymmetricDiffIndependentResult() {
        IntegerSet first = new IntegerSet();
        first.add(1);
        first.add(2);

        IntegerSet second = new IntegerSet();
        second.add(2);
        second.add(3);

        IntegerSet result = first.symmetricDiff(second);
        result.add(99);

        assertTrue(first.contains(1));
        assertTrue(first.contains(2));
        assertFalse(first.contains(99));
        assertTrue(second.contains(2));
        assertTrue(second.contains(3));
        assertFalse(second.contains(99));
    }

    // ===== isEmpty() =====

    @Test
    public void testIsEmptyNewSet() {
        IntegerSet set = new IntegerSet();

        assertTrue(set.isEmpty());
    }

    @Test
    public void testIsEmptyPopulatedSet() {
        IntegerSet set = new IntegerSet();
        set.add(1);

        assertFalse(set.isEmpty());
    }

    @Test
    public void testIsEmptyAfterClear() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    // ===== toString() =====

    @Test
    public void testToStringAscendingOrder() {
        IntegerSet set = new IntegerSet();
        set.add(8);
        set.add(-3);
        set.add(15);
        set.add(0);

        assertEquals("[-3, 0, 8, 15]", set.toString());
    }

    @Test
    public void testToStringEmptyFormatting() {
        IntegerSet set = new IntegerSet();

        assertEquals("[]", set.toString());
    }

    @Test
    public void testToStringExactSpacingAndDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(1);
        set.add(5);
        set.add(3);
        set.add(1);

        assertEquals("[1, 3, 5]", set.toString());
        assertEquals(3, set.length());
    }
}
