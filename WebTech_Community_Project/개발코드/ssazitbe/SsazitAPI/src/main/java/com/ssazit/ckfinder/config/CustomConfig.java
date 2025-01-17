package com.ssazit.ckfinder.config;

import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.cksource.ckfinder.config.Config;


public class CustomConfig extends Config {
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private boolean enabled = false;
}
