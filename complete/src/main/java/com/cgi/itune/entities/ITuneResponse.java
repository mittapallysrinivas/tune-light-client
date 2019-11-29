package com.cgi.itune.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITuneResponse {
    Integer resultCount;
    List<ITunesCollection> results;


    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<ITunesCollection> getResults() {
        return results;
    }

    public void setResults(List<ITunesCollection> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ITuneResponse{" +
                "resultCount=" + resultCount +
                ", results=" + results +
                '}';
    }
}
