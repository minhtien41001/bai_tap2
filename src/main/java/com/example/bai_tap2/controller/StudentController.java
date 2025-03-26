package com.example.bai_tap2.controller;

import com.example.bai_tap2.dto.StudentRequest;
import com.example.bai_tap2.entity.Student;
import com.example.bai_tap2.service.IStudentService;
import com.example.bai_tap2.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Student>>> getAllStudents() {
        List<Student> students = iStudentService.findAll();

        CustomResponse<List<Student>> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                "Lấy danh sách sinh viên thành công",
                students
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Student>> addStudent(@RequestBody StudentRequest studentRequest) {
        Student newStudent = iStudentService.addStudent(studentRequest);

        CustomResponse<Student> response = new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Thêm mới sinh viên thành công",
                newStudent
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Student>> updateStudent(
            @PathVariable("id") int id, @RequestBody StudentRequest studentRequest) {

        Student updatedStudent = iStudentService.updateStudent(id, studentRequest);

        CustomResponse<Student> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                "Cập nhật sinh viên thành công",
                updatedStudent
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deleteStudent(@PathVariable long id) {
        iStudentService.deleteStudent(id);

        CustomResponse<String> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                "Xóa sinh viên thành công",
                "Sinh viên có id " + id + " đã bị xóa."
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<CustomResponse<List<Student>>> searchStudentsByName(@RequestParam String fullName) {
        List<Student> students = iStudentService.searchStudentsByName(fullName);

        String message = students.isEmpty() ? "Không tìm thấy sinh viên nào phù hợp!" : "Tìm kiếm sinh viên thành công";

        CustomResponse<List<Student>> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                message,
                students
        );

        return ResponseEntity.ok(response);
    }
}
