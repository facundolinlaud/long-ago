package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.dto.behaviors.BehaviorTaskDto;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BehaviorFactory {
    private Map<String, BehaviorTaskDto> behaviors;

    public BehaviorFactory() {
        Set<String> files = ModelFactory.getBehaviors();

        this.behaviors = files.stream()
                .map(ModelFactory::getBehavior)
                .collect(Collectors.toMap(BehaviorTaskDto::getId, Function.identity()));
    }

    public BehaviorTaskDto get(String id) {
        return behaviors.get(id);
    }
}
