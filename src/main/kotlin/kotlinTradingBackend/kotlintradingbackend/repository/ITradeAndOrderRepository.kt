package kotlinTradingBackend.kotlintradingbackend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import kotlinTradingBackend.kotlintradingbackend.modal.TradeAndOrder

@Repository
public interface ITradeAndOrderRepository : JpaRepository<TradeAndOrder, Long> {
    fun deleteTradeAndOrderById(id: Long);

}