package ru.quipy.bankDemo.accounts.subscribers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.quipy.bankDemo.accounts.api.AccountAggregate
import ru.quipy.bankDemo.accounts.logic.Account
import ru.quipy.bankDemo.transfers.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.streams.AggregateSubscriptionsManager
import java.util.*
import javax.annotation.PostConstruct
import ru.quipy.saga.SagaManager

@Component
class TransactionsSubscriber(
    private val subscriptionsManager: AggregateSubscriptionsManager,
    private val accountEsService: EventSourcingService<UUID, AccountAggregate, Account>
) {
    private val logger: Logger = LoggerFactory.getLogger(TransactionsSubscriber::class.java)
    private val sagaManager: SagaManager
    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(TransferTransactionAggregate::class, "accounts::transaction-processing-subscriber") {
            `when`(TransferTransactionCreatedEvent::class) { event ->
                logger.info("Got transaction to process: $event")

                val transactionOutcome1 = accountEsService.update(event.sourceAccountId) { // todo sukhoa idempotence!
                    it.performTransferFrom(
                        event.sourceBankAccountId,
                        event.transferId,
                        event.transferAmount
                    )
                }

                val transactionOutcome2 = accountEsService.update(event.destinationAccountId) { // todo sukhoa idempotence!
                    it.performTransferTo(
                        event.destinationBankAccountId,
                        event.transferId,
                        event.transferAmount
                    )
                }

                logger.info("Transaction: ${event.transferId}. Outcomes: $transactionOutcome1, $transactionOutcome2")
            }
            `when`(TransactionSucceededEvent::class) { event ->
                logger.info("Got transaction succeeded event: $event")

                val transactionOutcome1 = accountEsService.update(event.sourceAccountId) { // todo sukhoa idempotence!
                    it.processPendingTransaction(event.sourceBankAccountId, event.transferId)
                }

                val transactionOutcome2 = accountEsService.update(event.destinationAccountId) { // todo sukhoa idempotence!
                    it.processPendingTransaction(event.destinationBankAccountId, event.transferId)
                }

                logger.info("Transaction: ${event.transferId}. Outcomes: $transactionOutcome1, $transactionOutcome2")
            }
        }
    }
}