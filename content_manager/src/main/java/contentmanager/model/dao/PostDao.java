package contentmanager.model.dao;


import contentmanager.model.entity.PostEntity;

import java.util.List;

public interface PostDao {

    public List<PostEntity> getPostById(Long postId);
    public void savePost(List<PostEntity> post);
    public void updatePost(List<PostEntity> post);
}
