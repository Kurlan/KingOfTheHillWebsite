package com.kingofthehill.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueueLengths {
    private final long freeQueueLength;
    private final long paidQueueLength;
}
