package br.pucpr.authserver.lesson

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lesson")
class LessonController(
    val service: LessonService
) {
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun insert(@RequestBody lesson: Lesson) = service.save(lesson)
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        service.delete(id)
            .let { ResponseEntity.ok().build() }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "AuthServer")
    fun getByCourse(@RequestParam dir: String = "ASC", @RequestParam course: Long): ResponseEntity<List<Lesson>> {
        val sortDir = SortDir.findOrNull(dir)
        if(sortDir == null)
            return ResponseEntity.badRequest().build()
        return service.findAllByCourse(sortDir, course)
            .let { ResponseEntity.ok(it) }
    }
}