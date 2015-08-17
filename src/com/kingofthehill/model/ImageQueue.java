package com.kingofthehill.model;

import lombok.Data;

@Data
public class ImageQueue {
    private final String name;
    private final Long length;
    private final Grafiti lastGrafiti;
}
