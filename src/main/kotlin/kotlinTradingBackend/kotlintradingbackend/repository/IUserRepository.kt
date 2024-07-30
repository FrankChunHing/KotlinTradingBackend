package kotlinTradingBackend.kotlintradingbackend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import kotlinTradingBackend.kotlintradingbackend.modal.User
import java.util.Optional

@Repository
interface IUserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
    fun findByUsernameAndPassword(username: String, password: String): Optional<User>
}