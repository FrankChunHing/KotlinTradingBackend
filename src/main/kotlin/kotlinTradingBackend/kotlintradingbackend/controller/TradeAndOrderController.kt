package kotlinTradingBackend.kotlintradingbackend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlinTradingBackend.kotlintradingbackend.modal.TradeAndOrder
import kotlinTradingBackend.kotlintradingbackend.service.TradeAndOrderService

import java.util.Optional

@RestController
class TradeAndOrderController @Autowired constructor(
    private val tradeAndOrderService: TradeAndOrderService
) {

    data class TradeOrderRequest(
        val username: String,
        val time: Double,
        val symbol: String,
        val type: String, // market or limit
        val side: String, // buy or sell
        val size: Double,
        val price: Double
    )

    @GetMapping("/api/v1/Orders")
    fun tradeOrder(): List<TradeAndOrder> {
        return tradeAndOrderService.getAllTradesAndOrders()
    }

    @GetMapping("/api/v1/Orders/{id}")
    fun getTradeAndOrder(@PathVariable id: Long): Optional<TradeAndOrder> {
        return tradeAndOrderService.getTradeAndOrderById(id)
    }

    @DeleteMapping("/api/v1/Order/delete")
    fun deleteTradeAndOrder(@RequestBody trade: TradeAndOrder): List<TradeAndOrder> {
        trade.id?.let { tradeAndOrderService.deleteTradeAndOrderById(it) }
        return tradeAndOrderService.getAllTradesAndOrders()
    }
}