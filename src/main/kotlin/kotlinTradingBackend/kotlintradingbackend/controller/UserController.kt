package kotlinTradingBackend.kotlintradingbackend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlinTradingBackend.kotlintradingbackend.modal.TradeAndOrder
import kotlinTradingBackend.kotlintradingbackend.modal.User
import kotlinTradingBackend.kotlintradingbackend.repository.IUserRepository
//import kotlinProject.kotlinspringbootbackend.service.UserCashService
import kotlinTradingBackend.kotlintradingbackend.service.UserService
import java.util.Optional

@RestController
class UserController @Autowired constructor(
    private val userService: UserService,
//    private val userCashService: UserCashService,
    private val userRepository: IUserRepository
) {

    @PostMapping("/api/v1/user/login")
    fun login(@RequestBody user: User): String {
        return userService.login(user)
    }

    @PostMapping("/api/v1/user/register")
    fun register(@RequestBody user: User): String {
        return userService.register(user)
    }

    @GetMapping("/users/{name}")
    fun getUserInfo(@PathVariable name: String): Optional<User> {
        return userService.getUserByUsername(name)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

    @PostMapping("/{username}/trades")
    fun addTradeToUser(@PathVariable username: String, @RequestBody trade: TradeAndOrder): ResponseEntity<User> {
        val updatedUser = userService.addTradeToUser(username, trade)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @PutMapping("/{username}/trades/amend")
    fun amendTradeToUser(@PathVariable username: String, @RequestBody trade: TradeAndOrder): ResponseEntity<User> {
        val updatedUser = userService.amendTradeToUser(username, trade)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @DeleteMapping("/{username}/trades/delete")
    fun deleteTradeFromUser(@PathVariable username: String, @RequestBody trade: TradeAndOrder): ResponseEntity<User> {
        val updatedUser = userService.deleteTradeToUser(username, trade)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

//    @GetMapping("/api/v1/user/cash")
//    fun getUserCash(): List<UserCash> {
//        return userCashService.getCash()
//    }

    @GetMapping("/users")
    fun getUsers(): List<User> {
        return userRepository.findAll()
    }
}