package br.pucpr.authserver.roles

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.intellij.lang.annotations.Pattern

@Entity
@Table(name = "roles")
class Role
    (
    @Id
    @Pattern("^[A-Z][A-Z0-9]+$")
    var name: String,

    var description: String
) {


}