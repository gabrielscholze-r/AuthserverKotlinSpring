package br.pucpr.authserver.users

enum class SortDir{
    ASC,
    DESC;
    companion object{
        fun findOrNull(sortDir: String):SortDir? =
            entries.find { it.name.lowercase() == sortDir.lowercase() }

    }
}