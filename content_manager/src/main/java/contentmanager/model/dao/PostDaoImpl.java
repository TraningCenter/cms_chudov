package contentmanager.model.dao;

import contentmanager.model.entity.PostEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class PostDaoImpl implements PostDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public List<PostEntity> getPostById(Long postId) {
        List<PostEntity> allPosts = hibernateTemplate.loadAll(PostEntity.class);
        List<PostEntity> posts = new ArrayList<PostEntity>();

        for (PostEntity post: allPosts){
            if (post.getPostId().equals(postId)){
                posts.add(post);
            }
        }
        return posts;
    }

    @Transactional
    public void savePost(List<PostEntity> posts) {
        for (PostEntity post: posts){
            hibernateTemplate.saveOrUpdate(post);
        }
    }

    @Transactional
    public void updatePost(List<PostEntity> posts) {
        for (PostEntity post: posts){
            hibernateTemplate.update(post);
        }
    }
}
