package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service.impl;

import org.springframework.stereotype.Service;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Post;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.exception.ResourceNotFoundException;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.PostDto;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.repositories.PostRepository;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service.PostService;

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


    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id + ""));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        //get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id + ""));
        //update post
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id + ""));
        postRepository.delete(post);
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
