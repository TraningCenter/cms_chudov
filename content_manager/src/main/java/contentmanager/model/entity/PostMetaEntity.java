package contentmanager.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "post_meta")
public class PostMetaEntity implements Serializable{

    @Id
    @GeneratedValue
    private Long metaId;

    @Column(name = "post_id", unique = true)
    private Long postId;

    @Column(name = "title")
    private String title;

    @Column(name = "post_date")
    private Date postDate;

    @OneToMany(mappedBy = "postMeta",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    private List<PostEntity> posts;

    public PostMetaEntity(){
    }

    public PostMetaEntity(Long postId, String title, Date postDate, List<PostEntity> posts){
        this.postId = postId;
        this.title = title;
        this.postDate = postDate;
        this.posts = posts;
    }

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }
}
