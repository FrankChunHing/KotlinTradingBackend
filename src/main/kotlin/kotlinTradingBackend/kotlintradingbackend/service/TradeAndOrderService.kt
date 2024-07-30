package kotlinTradingBackend.kotlintradingbackend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlinTradingBackend.kotlintradingbackend.modal.TradeAndOrder
import kotlinTradingBackend.kotlintradingbackend.repository.ITradeAndOrderRepository
import java.util.Optional

@Service
class TradeAndOrderService @Autowired constructor(
    private val tradeAndOrderRepository: ITradeAndOrderRepository
) {

    fun getAllTradesAndOrders(): List<TradeAndOrder> {
        return tradeAndOrderRepository.findAll()
    }

    fun getTradeAndOrderById(id: Long): Optional<TradeAndOrder> {
        return tradeAndOrderRepository.findById(id)
    }

    fun createTradeAndOrder(tradeAndOrder: TradeAndOrder): TradeAndOrder {
        return tradeAndOrderRepository.save(tradeAndOrder)
    }

    @Transactional
    fun deleteTradeAndOrderById(id: Long) {
        tradeAndOrderRepository.deleteById(id)
    }}