package kotlinTradingBackend.kotlintradingbackend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

import kotlinTradingBackend.kotlintradingbackend.modal.TradeAndOrder
import kotlinTradingBackend.kotlintradingbackend.modal.User
import kotlinTradingBackend.kotlintradingbackend.repository.ITradeAndOrderRepository
import kotlinTradingBackend.kotlintradingbackend.repository.IUserRepository


@Service
class UserService @Autowired constructor(
    private val userRepository: IUserRepository,
    private val tradeAndOrderRepository: ITradeAndOrderRepository
) {

    fun login(user: User): String {
        val checkUserAndPassword = userRepository.findByUsernameAndPassword(user.username, user.password)
        return if (checkUserAndPassword.isPresent) {
            user.username
        } else {
            "login failed"
        }
    }

    fun register(user: User): String {
        val existingUser = userRepository.findByUsername(user.username)
        if (existingUser.isPresent) {
            return "User ${user.username} is not available. Please register another username."
        } else {
            userRepository.save(user)
            return "User registered successfully. Your username: ${user.username}"
        }
    }

    fun addTradeToUser(username: String, trade: TradeAndOrder): User? {
        val userOptional = userRepository.findByUsername(username)
        return if (userOptional.isPresent) {
            val user = userOptional.get()
            trade.user = user
            trade.user_name = user.username
            val tradesAndOrders = user.tradesAndOrders.toMutableList()
            tradesAndOrders.add(trade)
            user.tradesAndOrders = tradesAndOrders
            userRepository.save(user)
            user
        } else {
            println("User not found: $username")
            null
        }
    }

    fun amendTradeToUser(username: String, trade: TradeAndOrder): User? {
        val userOptional = userRepository.findByUsername(username)
        return if (userOptional.isPresent) {
            val user = userOptional.get()
            val tradesForThatUser = user.tradesAndOrders.toMutableList()
            for (i in tradesForThatUser.indices) {
                if (tradesForThatUser[i].id == trade.id) {
                    tradesForThatUser[i] = trade
                    trade.user = user
                    trade.user_name = user.username
                    user.tradesAndOrders = tradesForThatUser
                    userRepository.save(user)
                    return user
                }
            }
            println("Trade not found for user: $username")
            null
        } else {
            println("User not found: $username")
            null
        }
    }

    @Transactional
    fun deleteTradeToUser(username: String, trade: TradeAndOrder): User? {
        val userOptional = userRepository.findByUsername(username)
        return if (userOptional.isPresent) {
            val user = userOptional.get()
            val tradesForThatUser = user.tradesAndOrders.toMutableList()

            val removed = tradesForThatUser.removeIf { it.id == trade.id }
            if (removed) {
                user.tradesAndOrders = tradesForThatUser
                userRepository.save(user)
                tradeAndOrderRepository.deleteById(trade.id!!)
                user
            } else {
                println("Trade not found for user: $username")
                null
            }
        } else {
            println("User not found: $username")
            null
        }
    }

    fun getUserByUsername(username: String): Optional<User> {
        return userRepository.findByUsername(username)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}
