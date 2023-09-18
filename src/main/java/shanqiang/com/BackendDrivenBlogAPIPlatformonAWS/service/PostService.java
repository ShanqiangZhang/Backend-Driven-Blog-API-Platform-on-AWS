package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service;

import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.PostDto;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.PostResponse;

import java.util.List;

public interface PostService{
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);
}
