package com.starter.infra.model;

import java.util.List;
import java.util.Map;

public class ComponentSpec {
    private String name;
    private String displayName;
    private String description;
    private String category;
    private String image;
    private String composeFile;
    private int order;
    private Map<String, Integer> ports;
    private Map<String, String> env;
    private List<String> dependencies;
    private String status = "stopped";
    private String containerId;

    public ComponentSpec() {
    }

    public ComponentSpec(String name, String displayName, String category, String image, String composeFile, int order,
            Map<String, Integer> ports) {
        this.name = name;
        this.displayName = displayName;
        this.category = category;
        this.image = image;
        this.composeFile = composeFile;
        this.order = order;
        this.ports = ports;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String d) {
        displayName = d;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        description = d;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String c) {
        category = c;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String i) {
        image = i;
    }

    public String getComposeFile() {
        return composeFile;
    }

    public void setComposeFile(String c) {
        composeFile = c;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int o) {
        order = o;
    }

    public Map<String, Integer> getPorts() {
        return ports;
    }

    public void setPorts(Map<String, Integer> p) {
        ports = p;
    }

    public Map<String, String> getEnv() {
        return env;
    }

    public void setEnv(Map<String, String> e) {
        env = e;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> d) {
        dependencies = d;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        status = s;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String c) {
        containerId = c;
    }
}