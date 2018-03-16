package contentmanager.model.service;

import contentmanager.model.dao.PostMetaDao;
import contentmanager.model.entity.PostMetaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMetaDao postMetaDao;

    public List<PostMetaEntity> getAllPosts(){
        return this.postMetaDao.getAllPosts();
    }

    public PostMetaEntity getPostById(Long postId){
        return this.postMetaDao.getPostById(postId);
    }

    public List<PostMetaEntity> getAllUsersPost(List<Long> postIdList){
        List<PostMetaEntity> usersPost = new ArrayList<PostMetaEntity>();
        for (Long postId: postIdList){
            usersPost.add(postMetaDao.getPostById(postId));
        }

        return usersPost;
    }

    public void savePost(PostMetaEntity post){
        postMetaDao.savePost(post);
    }

    public void updatePost(PostMetaEntity post){
        postMetaDao.savePost(post);
    }
}
