package com.example.demo.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsResult {
    private String title;
    private String link;
    private List<String> keywords;
    private List<String> creator;
    @JsonProperty("video_url")
    private String videoUrl;
    private String description;
    private String content;
    private String pubDate;
    @JsonProperty("full_description")
    private String fullDescription;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("source_id")
    private String sourceId;
    private List<String> country;
    private List<String> category;
    private String language;

    public NewsResult(String title, String link, List<String> keywords, List<String> creator, String videoUrl,
                      String description, String content, String pubDate, String fullDescription, String imageUrl,
                      String sourceId, List<String> country, List<String> category, String language) {
        this.title = title;
        this.link = link;
        this.keywords = keywords;
        this.creator = creator;
        this.videoUrl = videoUrl;
        this.description = description;
        this.content = content;
        this.pubDate = pubDate;
        this.fullDescription = fullDescription;
        this.imageUrl = imageUrl;
        this.sourceId = sourceId;
        this.country = country;
        this.category = category;
        this.language = language;
    }

    public NewsResult(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getCreator() {
        return creator;
    }

    public void setCreator(List<String> creator) {
        this.creator = creator;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
