package org.russell.algorithms.practice.amz;

import java.util.Collections;
import java.util.List;

/**
 * Amz exercise:
 *
 * Given an array of packets and a number of channels,
 * find the max quality of packets that you can send over the channels.
 * - Each channel must be used at least once
 * - max quality of a subarray is defined by the element in the middle if size is odd,
 * or the average of the 2 middle elements if size is even
 */
public class MaxQualityOfPackets {

    /**
     * If you sort the elements, the max quality will always be
     * sending the (channels-1) bigger packets each over one channel,
     * plus the max quality of the remaining elements over the last channel
     */
    public static long maximumQuality(List<Integer> packets, int channels) {
        if (channels == packets.size()) {
            long maxQuality = 0;
            for (final Integer packet : packets) {
                maxQuality += packet;
            }
            return maxQuality;
        }

        Collections.sort(packets);
        double maxQuality = 0;
        for (int i = packets.size() - channels + 1; i < packets.size(); i++) {
            maxQuality = maxQuality + packets.get(i);
        }

        final int maxSize = packets.size() - channels;
        if (maxSize % 2 == 0) {
            maxQuality = maxQuality + packets.get(maxSize/2);
        } else {
            final double channelQuality = packets.get(maxSize/2) + packets.get((maxSize/2)+1);
            maxQuality = maxQuality + (channelQuality/2);
        }
        return (long) Math.ceil(maxQuality);
    }
}
