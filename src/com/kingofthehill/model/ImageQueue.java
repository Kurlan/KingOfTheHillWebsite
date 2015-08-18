package com.kingofthehill.model;

import java.util.List;

import lombok.Data;

@Data
public class ImageQueue {
    private final String name;
    private final Long length;
    private final Grafiti lastGrafiti;
    private final List<Grafiti> lastSeveralGrafiti;
}
