package contentmanager.model.dao;


import contentmanager.model.entity.PostMetaEntity;

import java.util.List;

public interface PostMetaDao {

    public List<PostMetaEntity> getAllPosts();
    public PostMetaEntity getPostById(Long postId);
    public void savePost(PostMetaEntity post);
    public void updatePost(PostMetaEntity post);
}
