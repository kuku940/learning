package cn.xiaoyu.guava.range;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * Range表示一个间隔或者一个序列，它被用于获取一组数字/串在一个特定的范围内
 */
public class RangeTest {
    public static void main(String[] args) {
        // [0,9]
        Range<Integer> range = Range.closed(0, 9);
        printRange(range);
        System.out.println("5 is present:" + range.contains(5));
        System.out.println(("(1,2,3) is present:" + range.containsAll(Ints.asList(1, 2, 3))));
        System.out.println("Lower Bound: " + range.lowerEndpoint());
        System.out.println("Upper Bound: " + range.upperEndpoint());
        System.out.println();

        // (0,9)
        range = Range.open(0, 9);
        printRange(range);
        System.out.println();

        // (0,9]
        range = Range.openClosed(0, 9);
        printRange(range);
        System.out.println();

        // [0,9)
        range = Range.closedOpen(0, 9);
        printRange(range);
        System.out.println();

        range = Range.greaterThan(9);
        System.out.println("Lower Bound:" + range.lowerEndpoint());
        System.out.println("Upper Bound present:" + range.hasUpperBound());
        System.out.println();

        range = Range.closed(0, 9);
        Range<Integer> range1 = Range.closed(3, 5);
        printRange(range1);

        //check a subrange [3,5] in [0,9]
        System.out.println("[0,9] encloses [3,5]:" + range.encloses(range1));
        System.out.println();

        Range range2 = Range.closed(9, 20);
        printRange(range2);
        System.out.println("[0,9] is connected [9,20]" + range.isConnected(range2));
        System.out.println();

        Range range3 = Range.closed(5, 15);
        printRange(range.intersection(range3));
        printRange(range.span(range3));
    }

    private static void printRange(Range<Integer> range) {
        System.out.print("[");
        for (int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade + ",");
        }
        System.out.println("]");
    }
}
