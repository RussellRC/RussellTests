package russell.tests.algorithms.hr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MandragoraForestTest {

    @Test
    public void test01() throws IOException {
        final InputStream testIn = Thread.currentThread().getContextClassLoader()
                                         .getResourceAsStream("russell/tests/algorithms/hr/MandragoraForest/mf-in01.txt");
        Assert.assertTrue(testIn != null);
        final InputStream dupIn = Thread.currentThread().getContextClassLoader()
                                           .getResourceAsStream("russell/tests/algorithms/hr/MandragoraForest/mf-in01.txt");
        final InputStream testOut = Thread.currentThread().getContextClassLoader()
                                      .getResourceAsStream("russell/tests/algorithms/hr/MandragoraForest/mf-out01.txt");
        Assert.assertTrue(testOut != null);
        
        final Scanner scanIn = new Scanner(testIn);
        final Scanner scanOut = new Scanner(testOut);
        final Scanner scanDupIn = new Scanner(dupIn);
        
        final int cases = scanIn.nextInt();
        scanDupIn.nextLine();
        for (int i = 0; i < cases; i++) {
            long maxExp = MandragoraForest.maxExperience(scanIn);
            long expected = scanOut.nextLong();
            
            final String caseInput = String.format("%s -> %s", scanDupIn.nextLine(), scanDupIn.nextLine());
            
            if (expected != maxExp) {
                System.out.format("Case #%d failed: '%s' vs '%s'%n", i+1, expected, maxExp);
                System.out.format("Input: %s%n", caseInput);
            }
        }
        
    }
}
