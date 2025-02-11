package br.pucpr.authserver.course

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/course")
class CourseController(
    val courseService: CourseService
) {
    @PostMapping
    fun insert(@RequestBody course: Course): ResponseEntity<Course> = courseService.save(course)
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/{id}")
    fun findByIdOrNull(@PathVariable id: Long) = courseService.findByIdOrNull(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        courseService.delete(id)
            .let { ResponseEntity.ok().build() }

    @GetMapping
    fun findAll() = courseService.findAll()

    @DeleteMapping
    fun deleteAll() = courseService.deleteAll()
}