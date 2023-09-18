package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service;

import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto);

}
