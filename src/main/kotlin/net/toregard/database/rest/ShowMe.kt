package net.toregard.database.rest

import net.toregard.database.model.Person
import net.toregard.database.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random


@CrossOrigin(maxAge = 3600)
@RestController
class AppsController(val personRepository: PersonRepository
) {
    @GetMapping
        fun get(): ResponseEntity<Any?> {
    personRepository.save(Person(0,"name1"+ Random(100).toString()))
    return ResponseEntity(personRepository.findAll(), HttpStatus.OK)
    }

    @PostMapping
    fun post(@RequestBody name : String ): ResponseEntity<Any?> {
    personRepository.save(Person(0,name))
    return ResponseEntity(personRepository.findAll(), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable(value = "id") id : Long,
        @RequestBody person : Person)
    : ResponseEntity<Person> =
        personRepository.findById(id).map {
            currentPerson ->
            val updatePerson: Person =
                currentPerson
                    .copy(
                        name = "name"+ Random(4000).toString()
                    )
            ResponseEntity.ok().body(personRepository.save(updatePerson))
        }.orElse(ResponseEntity.notFound().build())



    @DeleteMapping
    fun delete() :  ResponseEntity<Any?> {
        personRepository.deleteAll()
        return ResponseEntity(personRepository.findAll(), HttpStatus.OK)
    }
}
