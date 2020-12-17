package com.example.sulymka_l6_json

import com.google.gson.Gson
import java.io.FileReader
import java.io.FileWriter

fun init(): Person {
    val me: Person = Person("Roma", 20)
    me.father = Person("Orest", 52)
    me.mother = Person("Maryna", 45)

    me.mother?.let { it.father = Person("Vasyl", 79) }
    me.mother?.let { it.mother = Person("Maria", 72) }

    me.father?.let { it.father = Person("Myron", 72) }
    me.father?.let { it.mother = Person("Nadia", 68) }

    me.countRelatives(me)

    Person.listRelatives = mutableListOf<Person>()
    createArrays(me)
    Person.listRelatives.forEach {
        it.allRelatives = Person.listRelatives.size
    }
    Person.listRelatives = mutableListOf<Person>()
    return me
}

fun main() {
    val me: Person = init()
    val path: String = "app/src/main/java/com/example/sulymka_l6_json/family.json"
    val reading = FileReader(path)

    val string = Gson().toJson(me)
    FileWriter(string).use {
        it.write(string)
    }
    println(string)

    val new: Person = Gson().fromJson(reading, Person::class.java)
    println(me == new)
}

fun adult(age: Int): Boolean {
    if (age >= 18) return true
    return false

}

fun createArrays(person: Person) {
    person.father?.let {
        Person.listRelatives.add(it)
        createArrays(it)
    }
    person.mother?.let {
        Person.listRelatives.add(it)
        createArrays(it)
    }
}