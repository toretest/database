package net.toregard.database.rest

import net.toregard.database.model.Person
import net.toregard.database.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sync")
class Sync(val personRepository: PersonRepository
) {

    @GetMapping()
    fun checksynk() :  ResponseEntity<Any?>{
        val personsSynk =PersonsSynk()
        personRepository.findAll().forEach {
            personsSynk.addPerson(it)
        }
        return ResponseEntity(personsSynk, HttpStatus.OK)
    }

    data class PersonSynk (val id : Long, val hashValue : Int)

    class PersonsSynk(){
        val data  = arrayListOf<PersonSynk>()

        fun addPerson(person : Person) {
           val hash = data.add(PersonSynk(person.id,person.hashCode()))
        }
    }

}
