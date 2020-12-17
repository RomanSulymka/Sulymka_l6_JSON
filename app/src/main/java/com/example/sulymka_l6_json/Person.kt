package com.example.sulymka_l6_json

data class Person(
    val name: String, val age: Int,
    var father: Person? = null,
    var mother: Person? = null,
    var adult: Boolean = adult(age),
    var allRelatives: Int = 0
) {

    fun countRelatives(person: Person) {
        this.father?.let {
            person.allRelatives++
            it.countRelatives(person)
        }
        this.mother?.let {
            person.allRelatives++
            it.countRelatives(person)
        }

    }

    companion object {
        var listRelatives: MutableList<Person> = mutableListOf<Person>()
    }

    override fun toString(): String {
        return this.name
    }
}
