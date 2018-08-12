package com.boot.entity;

import java.util.List;

public class Mean {
    private String name;
    private String url;
    private List<Mean> subMean;

    public Mean(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Mean(String name, String url, List<Mean> subMean) {
        this.name = name;
        this.url = url;
        this.subMean = subMean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Mean> getSubMean() {
        return subMean;
    }

    public void setSubMean(List<Mean> subMean) {
        this.subMean = subMean;
    }
}
