package kotlinTradingBackend.kotlintradingbackend.modal

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
//@Table(name = "tradeAndOrders")
data class TradeAndOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,

    @Column(nullable = false)
    var user_name: String,

    @Column(nullable = false)
    var time: Double,

    @Column(nullable = false)
    var symbol: String,

    @Column(nullable = false)
    var type: String,

    @Column(nullable = false)
    var side: String,

    @Column(nullable = false)
    var size: Double,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false)
    @JsonProperty("isExecuted")
    var isExecuted: Boolean,

    @Column(nullable = false)
    @JsonProperty("isClosed")
    var isClosed: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    var user: User? = null
) {
    constructor() : this(0, "", 0.0, "", "", "", 0.0, 0.0, false, false)

    constructor(
        id: Long?,
        time: Double,
        symbol: String,
        type: String,
        side: String,
        size: Double,
        price: Double,
        isExecuted: Boolean,
        isClosed: Boolean
    ) : this(id, "", time, symbol, type, side, size, price, isExecuted, isClosed, null)

    constructor(id: Long?): this(id, "", 0.0, "", "", "", 0.0, 0.0, false, false)

    constructor(
        id: Long?,
        user_name: String,
        time: Double,
        symbol: String,
        type: String,
        side: String,
        size: Double,
        price: Double,
        isExecuted: Boolean,
        isClosed: Boolean
    ) : this(id, user_name, time, symbol, type, side, size, price, isExecuted, isClosed, null)

    constructor(
        user_name: String,
        time: Double,
        symbol: String,
        type: String,
        side: String,
        size: Double,
        price: Double,
        isExecuted: Boolean,
        isClosed: Boolean
    ) : this(0, user_name, time, symbol, type, side, size, price, isExecuted, isClosed, null)

    constructor(
        user_name: String,
        time: Double,
        symbol: String,
        type: String,
        side: String,
        size: Double,
        price: Double,
        isExecuted: Boolean,
        isClosed: Boolean,
        user: User?
    ) : this(0, user_name, time, symbol, type, side, size, price, isExecuted, isClosed, user)


}