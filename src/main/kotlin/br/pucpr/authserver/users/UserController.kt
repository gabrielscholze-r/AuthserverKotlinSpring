package br.pucpr.authserver.users

import jakarta.validation.constraints.Null
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserSevice
) {
    @PostMapping
    fun insert(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insert(user))
    }
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<User> = userService.getById(id)
        ?.let { ResponseEntity.ok(it)}
        ?: ResponseEntity.notFound().build()

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Any> = userService.delete(id)
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()
    @GetMapping("/users/sort")
    fun getSorted(@RequestParam dir : String) : ResponseEntity<List<User>> {
        val sortDir = SortDir.findOrNull(dir) ?: return ResponseEntity.badRequest().build()
        return ResponseEntity.ok(userService.findAll(sortDir))
    }
}