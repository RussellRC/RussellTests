package russell.tests.algorithms.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import russell.tests.algorithms.domain.Partition;

@RunWith(JUnit4.class)
public class PartitionTest {

	@Test
	public void testAllPartitions() {
		System.out.println("\n##### All partitions of 7 #####");
		System.out.println(Partition.allPartitions(7));
	}
	
	@Test
	public void testPartitionsWithSize() {
		System.out.println("\n##### All partitions of 7 with size 2 #####");
		System.out.println(Partition.partitionsWithSize(7, 2));
	}
}
