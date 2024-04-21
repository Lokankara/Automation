package com.autotest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtensionAttributes {
    @JsonProperty("is_subscribed")
    private boolean isSubscribed;

    public boolean isSubscribed() {
        return false;
    }
}
