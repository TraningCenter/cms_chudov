package contentmanager.controller;

import contentmanager.model.dto.PostDto;
import contentmanager.model.entity.PostMetaEntity;
import contentmanager.model.service.PostService;
import contentmanager.util.PostDtoEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostDtoEntityUtil postDtoEntityUtil;

    @Autowired
    private PostService postService;

    @PostMapping("/save")
    public ResponseEntity savePost(@RequestBody PostDto postDto){
        PostMetaEntity postMetaEntity = postDtoEntityUtil.dtoToEntity(postDto);
        postService.savePost(postMetaEntity);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllPosts(@RequestBody List<Long> postIds){
        List<PostDto> postDtoList = new ArrayList<>();

        for (Long postId: postIds){
            PostMetaEntity postMetaEntity = postService.getPostById(postId);
            postDtoList.add(postDtoEntityUtil.entityToDto(postMetaEntity));
        }

        return ResponseEntity.ok(postDtoList);
    }

    @PostMapping("/edit")
    public ResponseEntity editPost(@RequestBody PostDto postDto){
        PostMetaEntity postMetaEntity = postDtoEntityUtil.dtoToEntity(postDto);
        postService.updatePost(postMetaEntity);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
