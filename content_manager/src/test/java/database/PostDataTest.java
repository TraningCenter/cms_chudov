package database;

import contentmanager.config.PersistenceConfig;
import contentmanager.config.WebConfig;
import contentmanager.model.entity.PostEntity;
import contentmanager.model.entity.PostMetaEntity;
import contentmanager.model.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, PersistenceConfig.class})
@Transactional
@WebAppConfiguration
public class PostDataTest {

    @Autowired
    private PostService postService;

    @Test
    public void savePostTest(){
        String post1 = "Hello hibernate!";
        String post2 = "Hello postgers!";
        String post3 = "Let's test you!";

        Long postId = 12l;

        PostEntity postEntity1 = new PostEntity(postId, post1);
        PostEntity postEntity2 = new PostEntity(postId, post2);
        PostEntity postEntity3 = new PostEntity(postId, post3);

        List<PostEntity> post = new ArrayList<PostEntity>();
        post.add(postEntity1);
        post.add(postEntity2);
        post.add(postEntity3);

        String title = "Test post";

        PostMetaEntity postMeta = new PostMetaEntity(postId, title, new Timestamp(System.currentTimeMillis()), post);

        postService.savePost(postMeta);

        PostMetaEntity postMetaResponse = null;
        List<PostMetaEntity> postsMeta = postService.getAllPosts();

        for (PostMetaEntity postMetaEntity: postsMeta){
            if (postId.equals(postMetaEntity.getPostId())){
                postMetaResponse = postMetaEntity;
            }
        }

        List<PostEntity> postResponse = postMetaResponse.getPosts();

        Assert.assertEquals(postMeta.getPostId(), postMetaResponse.getPostId());

        Assert.assertEquals(post.size(), postResponse.size());

        for (int i = 0; i < postResponse.size(); i++) {
            Assert.assertEquals(post.get(i).getPostId(), postResponse.get(i).getPostId());
            Assert.assertEquals(post.get(i).getContent(), postResponse.get(i).getContent());
        }
    }
}
