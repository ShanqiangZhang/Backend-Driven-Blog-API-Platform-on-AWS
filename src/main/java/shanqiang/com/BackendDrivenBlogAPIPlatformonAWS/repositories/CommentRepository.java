package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
