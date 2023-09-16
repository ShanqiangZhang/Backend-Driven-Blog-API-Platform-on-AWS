package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String filedName;
    private String filedValue;

    public ResourceNotFoundException(String resourceName, String filedName, String filedValue) {
        //example: post not found with id : 1
        super(String.format("%s not found with %s: '%s", resourceName, filedName, filedValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }

}
