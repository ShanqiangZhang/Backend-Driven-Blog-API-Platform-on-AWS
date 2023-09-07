package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.bean.Student;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "shanq", "zhang");
        return ResponseEntity.ok(student);
    }

    @GetMapping("/list-student")
    public ResponseEntity<List<Student>> getSomeStudent() {
        List<Student> studentList = Arrays.asList(
            new Student(1, "shanq", "zhang"),
            new Student(2, "lisi", "a"),
            new Student(3, "aq", "s")
        );
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId) {
        Student student = new Student(studentId, "shanqiang", "zhang");
        return ResponseEntity.ok(student);
    }
    @GetMapping("/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {

        return new Student(id, firstName, lastName);
    }

}
