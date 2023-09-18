package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);
}
