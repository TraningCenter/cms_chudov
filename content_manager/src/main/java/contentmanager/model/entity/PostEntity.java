package contentmanager.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "post")
public class PostEntity implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id",
            referencedColumnName = "post_id",
            insertable = false,
            updatable = false)
    private PostMetaEntity postMeta;

    public PostEntity(){
    }

    public PostEntity(Long postId, String content){
        this.postId = postId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostMetaEntity getPostMeta() {
        return postMeta;
    }

    public void setPostMeta(PostMetaEntity postMeta) {
        this.postMeta = postMeta;
    }
}
