package com.example.demo.dto;

import java.util.List;

public class NewsResponse {
    private String status;
    private Long totalResults;
    private List<NewsResult> results;
    private Long nextPage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsResult> getResults() {
        return results;
    }

    public void setResults(List<NewsResult> results) {
        this.results = results;
    }

    public Long getNextPage() {
        return nextPage;
    }

    public void setNextPage(Long nextPage) {
        this.nextPage = nextPage;
    }
}
