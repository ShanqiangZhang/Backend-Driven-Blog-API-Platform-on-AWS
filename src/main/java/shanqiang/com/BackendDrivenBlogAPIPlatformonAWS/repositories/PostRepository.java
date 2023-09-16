package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
