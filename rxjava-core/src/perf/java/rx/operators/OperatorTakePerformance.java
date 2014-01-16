package rx.operators;

import rx.Observable;
import rx.perf.AbstractPerformanceTester;
import rx.perf.IntegerSumObserver;
import rx.util.functions.Action0;

public class OperatorTakePerformance extends AbstractPerformanceTester {

    public static void main(String args[]) {

        final OperatorTakePerformance spt = new OperatorTakePerformance();
        try {
            spt.runTest(new Action0() {

                @Override
                public void call() {
                    spt.timeTake5();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Observable.range(0, 10).take(5);
     * 
     * Run: 10 - 8,780,556 ops/sec
     * Run: 11 - 8,822,590 ops/sec
     * Run: 12 - 8,842,733 ops/sec
     * Run: 13 - 8,825,486 ops/sec
     * Run: 14 - 8,771,545 ops/sec
     */
    public long timeTake5() {

        Observable<Integer> s = Observable.range(0, 10).take(5);

        IntegerSumObserver o = new IntegerSumObserver();

        for (long l = 0; l < REPETITIONS; l++) {
            s.subscribe(o);
        }
        return o.sum;
    }

}
