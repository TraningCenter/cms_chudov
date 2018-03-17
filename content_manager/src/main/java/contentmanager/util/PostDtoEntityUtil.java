package contentmanager.util;

import contentmanager.model.dto.PostDto;
import contentmanager.model.entity.PostEntity;
import contentmanager.model.entity.PostMetaEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostDtoEntityUtil {

    public PostMetaEntity dtoToEntity(PostDto postDto){
        List<PostEntity> postList = new ArrayList<>();

        try {
            for (String post : postDto.getContent()) {
                postList.add(new PostEntity(postDto.getPostId(), post));
            }
        } catch (NullPointerException e){}

        return new PostMetaEntity(postDto.getPostId(), postDto.getTitle(),
                new Timestamp(postDto.getPostDate()), postList);
    }

    public PostDto entityToDto(PostMetaEntity postMetaEntity){
        List<String> contents = new ArrayList<>();

        try {
            for (PostEntity post : postMetaEntity.getPosts()) {
                contents.add(post.getContent());
            }
        } catch (NullPointerException e){}

        return new PostDto(postMetaEntity.getPostId(), postMetaEntity.getTitle(),
                postMetaEntity.getPostDate().getTime(), contents);
    }
}
