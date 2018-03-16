package contentmanager;


import contentmanager.config.WebConfig;
import contentmanager.model.entity.PostEntity;
import contentmanager.model.entity.PostMetaEntity;
import contentmanager.model.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Main {

    @Autowired
    private PostService postService;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        Main main = context.getBean(Main.class);

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

        PostMetaEntity postMeta = new PostMetaEntity(postId, title, new Date(System.currentTimeMillis()), post);

        main.postService.savePost(postMeta);

        PostMetaEntity postMetaEntity = main.postService.getPostById(postId);
        System.out.println(postMetaEntity.getMetaId());
        System.out.println(postMetaEntity.getPostId());
        System.out.println(postMetaEntity.getTitle());
        System.out.println(postMetaEntity.getPostDate());
        for (PostEntity p: postMetaEntity.getPosts()){
            System.out.println("    " + p.getId());
            System.out.println("    " + p.getPostId());
            System.out.println("    " + p.getContent());
        }
    }
}
