package postmanager.model.entity;


import java.util.List;

public class UserEntity {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private List<PostMetaEntity> postMetaList;

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public List<PostMetaEntity> getPostMetaList() {
        return postMetaList;
    }
}
