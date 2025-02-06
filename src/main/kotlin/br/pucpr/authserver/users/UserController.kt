package br.pucpr.authserver.users

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
    @GetMapping
    fun findAll() : List<User> = userService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<User> = userService.getById(id)
        ?.let { ResponseEntity.ok(it)}
        ?: ResponseEntity.notFound().build()

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Any> = userService.delete(id)
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()

    }

    @GetMapping("/users")
    fun getSorted(@RequestParam sortDir : String) : ResponseEntity<List<User>> {
        if (sortDir.isEmpty() || (sortDir.lowercase() != "asc" && sortDir.lowercase() !="desc")){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity.ok(userService.getSorted(sortDir))
    }
}