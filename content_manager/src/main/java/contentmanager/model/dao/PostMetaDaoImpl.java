package contentmanager.model.dao;

import contentmanager.model.entity.PostMetaEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class PostMetaDaoImpl implements PostMetaDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public List<PostMetaEntity> getAllPosts() {
        List<PostMetaEntity> posts = hibernateTemplate.loadAll(PostMetaEntity.class);
        return posts;
    }

    @Transactional
    public PostMetaEntity getPostById(Long postId) {
        List<PostMetaEntity> posts = hibernateTemplate.loadAll(PostMetaEntity.class);
        for (PostMetaEntity post: posts){
            if (postId.equals(post.getPostId())){
                return post;
            }
        }
        return null;
    }

    @Transactional
    public void savePost(PostMetaEntity post) {
        hibernateTemplate.save(post);
    }

    @Transactional
    public void updatePost(PostMetaEntity post) {
        hibernateTemplate.update(post);
    }
}
