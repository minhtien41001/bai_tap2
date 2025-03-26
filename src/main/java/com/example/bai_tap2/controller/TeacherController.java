package com.example.bai_tap2.controller;

import com.example.bai_tap2.dto.StudentRequest;
import com.example.bai_tap2.dto.TeacherRequest;
import com.example.bai_tap2.entity.Student;
import com.example.bai_tap2.entity.Teacher;
import com.example.bai_tap2.service.ITeacherService;
import com.example.bai_tap2.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Teacher>>> getAllTeachers() {
        List<Teacher> teachers = teacherService.findAll();

        CustomResponse<List<Teacher>> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                "Lấy danh sách giảng viên thành công",
                teachers
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Teacher>> addTeacher(@RequestBody TeacherRequest teacherRequest) {
        Teacher newTeacher = teacherService.addTeacher(teacherRequest);

        CustomResponse<Teacher> response = new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Thêm mới giảng viên thành công",
                newTeacher
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Teacher>> updateTeacher(
            @PathVariable("id") int id, @RequestBody TeacherRequest teacherRequest) {

        Teacher updateTeacher = teacherService.updateTeacher(id, teacherRequest);

        CustomResponse<Teacher> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                "Cập nhật giảng viên thành công",
                updateTeacher
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deleteTeacher(@PathVariable long id) {
        teacherService.deleteTeacher(id);

        CustomResponse<String> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                "Xóa giảng viên thành công",
                "Giảng viên có id " + id + " đã bị xóa."
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<CustomResponse<List<Teacher>>> searchTeachersByName(@RequestParam String fullName) {
        List<Teacher> teachers = teacherService.searchTeachersByName(fullName);

        String message = teachers.isEmpty() ? "Không tìm thấy giảng viên nào phù hợp!" : "Tìm kiếm giảng viên thành công";

        CustomResponse<List<Teacher>> response = new CustomResponse<>(
                HttpStatus.OK.value(),
                message,
                teachers
        );

        return ResponseEntity.ok(response);
    }
}
