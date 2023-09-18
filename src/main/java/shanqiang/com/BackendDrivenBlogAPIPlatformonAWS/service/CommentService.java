package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service;

import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

}
