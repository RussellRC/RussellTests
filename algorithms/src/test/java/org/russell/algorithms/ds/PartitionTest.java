package org.russell.algorithms.ds;

import org.junit.jupiter.api.Test;
import org.russell.algorithms.practice.unsorted.Partition;

public class PartitionTest {

	@Test
	public void testAllPartitions() {
		System.out.println(Partition.allPartitions(5));
	}
	
	@Test
	public void testPartitionsWithSize() {
		System.out.println("\n##### All partitions of 7 with size 2 #####");
		System.out.println(Partition.partitionsWithSize(7, 2));
	}

	@Test
	public void testPartitionsStdout() {
		Partition.partitionStdout(5);
	}
}
