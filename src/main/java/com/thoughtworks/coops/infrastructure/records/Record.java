package com.thoughtworks.coops.infrastructure.records;

import com.thoughtworks.coops.api.jersey.Routes;

import java.util.Map;

public interface Record {
    Map<String, Object> toRefJson(Routes routes);

    Map<String, Object> toJson(Routes routes);
}
