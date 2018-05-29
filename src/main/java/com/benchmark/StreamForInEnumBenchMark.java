package com.benchmark;

import org.openjdk.jmh.annotations.Benchmark;

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


    @Benchmark
    public void testStream() {

        int currentEnum = new Random().nextInt(EnumBenchmark.values().length);

        Optional<EnumBenchmark> result = Stream.of(EnumBenchmark.values())
                .filter(o -> o.getValue() == currentEnum)
                .findFirst();
    }

    @Benchmark
    public void testFor() {

        int currentEnum = new Random().nextInt(EnumBenchmark.values().length);

        Optional<EnumBenchmark> result = Optional.empty();
        for (EnumBenchmark o : EnumBenchmark.values()) {
            if (o.getValue() == currentEnum) {
                result = Optional.of(o);
                break;
            }
        }

    }


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
