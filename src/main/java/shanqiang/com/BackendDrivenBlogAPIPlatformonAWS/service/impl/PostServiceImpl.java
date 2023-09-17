package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Post;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.PostDto;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.repositories.PostRepository;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert DTO to entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    public PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    public Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
