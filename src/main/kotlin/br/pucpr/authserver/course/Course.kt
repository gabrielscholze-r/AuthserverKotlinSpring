package br.pucpr.authserver.course

import br.pucpr.authserver.lesson.Lesson
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "tblCourse")
class Course (
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val name: String,

    @NotBlank
    val description: String,

    @OneToMany(mappedBy = "course", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val lessons: MutableSet<Lesson> = mutableSetOf()
)