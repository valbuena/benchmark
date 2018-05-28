package com.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class StreamForInEnumBenchMark {


    public enum EnumBenchmark {
        ZERO(0),
        ONE(1),
        TWO (2),
        THREE (3),
        FOUR (4),
        FIVE (5);

        private int value;

        public int getValue(){
            return value;
        }

        EnumBenchmark(int value){
            this.value = value;
        }


    }


    @State(Scope.Benchmark)
    public static class MyEnum {
        public int currentEnum = new Random().nextInt(EnumBenchmark.values().length);
    }

    @Benchmark
    public void testStream(MyEnum state) {

        Optional<EnumBenchmark> result = Stream.of(EnumBenchmark.values())
                .filter(o -> o.getValue() == state.currentEnum)
                .findFirst();
    }

    @Benchmark
    public void testFor(MyEnum state) {
        Optional<EnumBenchmark> result = Optional.empty();
        for (EnumBenchmark o : EnumBenchmark.values()) {
            if (o.getValue() == state.currentEnum) {
                result = Optional.of(o);
            }
        }

    }


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
