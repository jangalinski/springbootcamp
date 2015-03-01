package com.github.jangalinski;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("${foo.name}")
    private String name;

    private List<String> list = new ArrayList<>();

    @Override
    public String toString() {
        return new ToStringCreator(this).append("name",name).append("list",list).toString();
    }
}
