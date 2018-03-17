package contentmanager.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class PostDto implements Serializable{

    @JsonProperty("postId")
    private Long postId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("postDate")
    private Long postDate;

    @JsonProperty("content")
    private List<String> content;

    public PostDto(){}

    public PostDto(Long postId, String title, Long postDate, List<String> content){
        this.postId = postId;
        this.title = title;
        this.postDate = postDate;
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPostDate() {
        return postDate;
    }

    public void setPostDate(Long postDate) {
        this.postDate = postDate;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
