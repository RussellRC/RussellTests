package org.russell.tests.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SparkHello {

  private static final Pattern SPACE = Pattern.compile(" ");

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Usage: SparkHello <file>");
      System.exit(1);
    }
    final SparkConf sparkConf = new SparkConf().setAppName("SparkHello");
    final JavaSparkContext ctx = new JavaSparkContext(sparkConf);
    final JavaRDD<String> lines = ctx.textFile(args[0], 1);

    final JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());
    final JavaPairRDD<String, Integer> ones = words.mapToPair(word -> new Tuple2<>(word, 1));
    final JavaPairRDD<String, Integer> counts = ones.reduceByKey(Integer::sum);

    final List<Tuple2<String, Integer>> output = counts.collect();
    for (Tuple2<?, ?> tuple : output) {
      System.out.println(tuple._1() + ": " + tuple._2());
    }
    ctx.stop();
  }
}
