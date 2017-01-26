package com.ventura24;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class StringBufferForBenchmark {

    @State(Scope.Benchmark)
    public static class MyCounter {
        public int count = 0 ;
    }

    @Benchmark
    public void tesForStringBuffer(MyCounter state) {
        state.count++;
        StringBuffer stringBuffer = new StringBuffer(state.count);
        for (int i = 0; i< state.count; i++){
            stringBuffer.append("join ... strings ");
            stringBuffer.append(i);
            stringBuffer.append("of");
            stringBuffer.append(state.count);
        }
    }

    @Benchmark
    public void testForStringAdd(MyCounter state) {
        state.count++;
        String stringAdd = "" + state.count;
        for (int i = 0; i< state.count; i++) {
            stringAdd = stringAdd + "join ... strings " + state.count;
        }
    }


}