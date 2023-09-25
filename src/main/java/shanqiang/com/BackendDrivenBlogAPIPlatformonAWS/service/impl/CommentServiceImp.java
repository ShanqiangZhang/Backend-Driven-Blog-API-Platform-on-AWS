package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Comment;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Post;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.exception.BlogAPIException;
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
    private ModelMapper mapper;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = modelMapper;
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

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId+""));
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId+""));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId+""));
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId+""));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId+""));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId+""));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
