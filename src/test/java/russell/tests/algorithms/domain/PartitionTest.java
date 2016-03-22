package russell.tests.algorithms.domain;

import russell.tests.algorithms.domain.Partition;

public class PartitionTest {

	
	public static void main(String[] args) {
		System.out.println("\n##### All partitions of 7 #####");
		System.out.println(Partition.allPartitions(7));
		
		System.out.println("\n##### All partitions of 7 with size 2 #####");
		System.out.println(Partition.partitionsWithSize(7, 2));
	}
}
