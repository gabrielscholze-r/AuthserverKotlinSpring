package br.pucpr.authserver.users.controller

import br.pucpr.authserver.security.JWT
import br.pucpr.authserver.security.UserToken
import br.pucpr.authserver.users.SortDir
import br.pucpr.authserver.users.UserRepository
import br.pucpr.authserver.users.UserService
import br.pucpr.authserver.users.controller.requests.CreateUserRequest
import br.pucpr.authserver.users.controller.requests.LoginRequest
import br.pucpr.authserver.users.controller.responses.LoginResponse
import br.pucpr.authserver.users.controller.responses.UserResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {
    @GetMapping("/check")
    fun ping() = "Pong"

    @PostMapping
    fun insert(@RequestBody @Valid user: CreateUserRequest) =
        userService.insert(user.toUser())
            .let { UserResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(@RequestParam dir: String = "ASC", @RequestParam role: String? = null): ResponseEntity<List<UserResponse>> {
        val sortDir = SortDir.findOrNull(dir)
        if (sortDir == null)
            return ResponseEntity.badRequest().build()
        return userService.findAll(sortDir, role)
            .map { UserResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        userService.findByIdOrNull(id)
            ?.let { UserResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable id: Long, authentication: Authentication): ResponseEntity<Void> {
        val userToken = authentication.principal as? UserToken
            ?: return ResponseEntity.status(HttpStatus.FORBIDDEN).build()

        if (userToken.id == id || userToken.isAdmin) {
            userService.delete(id)
            return ResponseEntity.ok().build()
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
    }


    @PutMapping("/{id}/roles/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun grant(@PathVariable id: Long, @PathVariable role: String): ResponseEntity<Void> =
        if (userService.addRole(id, role)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

    @PreAuthorize("#id == principal.id")
    @SecurityRequirement(name = "AuthServer")
    @PutMapping("/{id}/courses/{course}")
    fun subscribe(@PathVariable id: Long, @PathVariable course: Long): ResponseEntity<Void> =
        if (userService.subscribeToCourse(id, course)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

    @GetMapping("/{id}/courses")
    fun getCourses(@PathVariable id: Long) = userService.getCourses(id)

    @PostMapping("/login")
    fun login(@Valid @RequestBody user: LoginRequest) =
        userService.login(user.email!!,user.password!!)
            ?.let { ResponseEntity.ok().body(it) }
            ?: ResponseEntity.notFound().build()
}
