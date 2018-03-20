package postmanager.model.entity;


public class PostMetaEntity {

    private Long postId;
    private Long userId;
    private UserEntity user;

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }

    public UserEntity getUser() {
        return user;
    }
}
