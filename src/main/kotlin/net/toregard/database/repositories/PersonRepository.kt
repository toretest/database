package net.toregard.database.repositories

import net.toregard.database.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface PersonRepository : JpaRepository<Person, Long>
