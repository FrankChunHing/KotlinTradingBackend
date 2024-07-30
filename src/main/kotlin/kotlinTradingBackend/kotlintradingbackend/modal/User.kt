package kotlinTradingBackend.kotlintradingbackend.modal


import java.util.ArrayList

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.OneToMany
import jakarta.persistence.FetchType
import jakarta.persistence.CascadeType


@Entity
@Table(name = "usernameAndPassword")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var tradesAndOrders: List<TradeAndOrder> = ArrayList()
) {
    constructor() : this(0, "", "")

    constructor(username: String) : this(0, username, "")

    constructor(username: String, password: String) : this(0, username, password)
}