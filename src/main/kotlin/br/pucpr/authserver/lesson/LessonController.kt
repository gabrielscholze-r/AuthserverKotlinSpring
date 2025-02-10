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

    @GetMapping("/{id}")
    fun getByCourse(@PathVariable id: Long) = service.findAllByCourse(id)
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()
}