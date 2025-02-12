package br.pucpr.authserver.lesson

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lesson")
class LessonController(
    val service: LessonService
) {
    @PostMapping
    fun insert(@RequestBody lesson: Lesson) = service.save(lesson)
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        service.delete(id)
            .let { ResponseEntity.ok().build() }

    @GetMapping
    fun getByCourse(@RequestParam dir: String = "ASC", @RequestParam course: Long): ResponseEntity<List<Lesson>> {
        val sortDir = SortDir.findOrNull(dir)
        if(sortDir == null)
            return ResponseEntity.badRequest().build()
        return service.findAllByCourse(sortDir, course)
            .let { ResponseEntity.ok(it) }
    }
}