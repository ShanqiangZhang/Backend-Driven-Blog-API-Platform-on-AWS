package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Comment;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Post;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.exception.ResourceNotFoundException;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.payload.CommentDto;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.repositories.CommentRepository;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.repositories.PostRepository;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }



    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        //retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId+""));
        //set post to comment entity
        comment.setPost(post);
        //comment entity to DB
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
