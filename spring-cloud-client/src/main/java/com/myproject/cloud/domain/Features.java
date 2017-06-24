package com.myproject.cloud.domain;

import lombok.Data;

import java.util.*;

@Data
public class Features {

    private Map<String, Object> featuresMap = new HashMap<>();

    public Set<String> getFeatures() {
        return featuresMap.keySet();
    }

    public Map<String, Object> getFeaturesMap() {
        return Collections.unmodifiableMap(featuresMap);
    }
}
