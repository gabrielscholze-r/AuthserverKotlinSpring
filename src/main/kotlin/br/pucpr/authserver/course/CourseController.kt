package br.pucpr.authserver.course

import br.pucpr.authserver.course.request.AddLesson
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/course")
class CourseController(
    val courseService: CourseService
) {
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun insert(@RequestBody course: Course): ResponseEntity<Course> =
        courseService.save(course)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "AuthServer")
    fun findByIdOrNull(@PathVariable id: Long) = courseService.findByIdOrNull(id)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        courseService.delete(id)
            .let { ResponseEntity.ok().build() }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "AuthServer")
    fun findAll() = courseService.findAll()

    @PutMapping("/lesson")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun addLesson(@RequestBody lesson: AddLesson) = courseService.addLesson(lesson)

    @PutMapping("/{id}/{lessonId}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun deleteLesson(@PathVariable id: Long, @PathVariable lessonId: Long): ResponseEntity<Void> =
        courseService.deleteLesson(id, lessonId)
            .let { ResponseEntity.ok().build() }
}