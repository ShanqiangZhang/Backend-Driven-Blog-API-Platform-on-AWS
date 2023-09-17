package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service;

import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.PostDto;

import java.util.List;

public interface PostService{
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();
}
