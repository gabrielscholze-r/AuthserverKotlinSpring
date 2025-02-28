package br.pucpr.authserver

import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.roles.RoleRepository
import br.pucpr.authserver.users.User
import br.pucpr.authserver.users.UserRepository
import br.pucpr.authserver.course.Course
import br.pucpr.authserver.course.CourseRepository
import br.pucpr.authserver.lesson.Lesson
import br.pucpr.authserver.lesson.LessonRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val courseRepository: CourseRepository,
    private val lessonRepository: LessonRepository
) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole = roleRepository.findByIdOrNull("ADMIN")
            ?: roleRepository.save(Role("ADMIN", "System Administrator"))

        val userRole = roleRepository.findByIdOrNull("USER")
            ?: roleRepository.save(Role("USER", "Premium User"))

        if (userRepository.findByRole("ADMIN").isEmpty()) {
            val admin = User(
                email = "admin@authserver.com",
                password = "admin",
                name = "Auth Server Administrator"
            )
            admin.roles.add(adminRole)
            userRepository.save(admin)
        }

        if (userRepository.findByRole("USER").isEmpty()) {
            val user = User(
                email = "user@authserver.com",
                password = "user",
                name = "Regular User"
            )
            user.roles.add(userRole)
            userRepository.save(user)
        }

        val courses = listOf(
            "Programação Orientada a Objetos" to "Programação Orientada a Objetos",
            "Programação Funcional" to "Programação funcional"
        )

        courses.forEach { (name, description) ->
            if (courseRepository.findByName(name) == null) {
                val course = courseRepository.save(Course(name = name, description = description))

                val lesson = Lesson(
                    name = "Lição 1 de $name",
                    duration = 60,
                    course = course
                )

                lessonRepository.save(lesson)
            }
        }
    }
}
