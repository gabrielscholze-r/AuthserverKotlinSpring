package br.pucpr.authserver.course.request


import org.jetbrains.annotations.NotNull

data class AddLesson(
    @field:NotNull
    val name: String = "",

    @field:NotNull
    val duration: Int,

    @field:NotNull
    val course: Long
) {




}