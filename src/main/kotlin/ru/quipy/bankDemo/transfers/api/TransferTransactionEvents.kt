package ru.quipy.bankDemo.transfers.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.math.BigDecimal
import java.util.*

//const val TRANSFER_PARTICIPANT_ACCEPTED = "TRANSFER_PARTICIPANT_ACCEPTED"
//const val TRANSFER_CONFIRMED = "TRANSFER_CONFIRMED"
//const val TRANSFER_NOT_CONFIRMED = "TRANSFER_NOT_CONFIRMED"
//const val TRANSFER_PARTICIPANT_COMMITTED = "TRANSFER_PARTICIPANT_COMMITTED"
//const val TRANSFER_PARTICIPANT_ROLLBACKED = "TRANSFER_PARTICIPANT_ROLLBACKED"
//const val NOOP = "NOOP"
//const val TRANSFER_CANCELLED = "TRANSFER_CANCELLED"

const val TRANSFER_WITHDRAWAL_SUCCEEDED = "TRANSFER_WITHDRAWAL_SUCCEEDED"
const val TRANSFER_WITHDRAWAL_FAILED = "TRANSFER_WITHDRAWAL_FAILED"
const val TRANSFER_DEPOSIT_SUCCEEDED = "TRANSFER_DEPOSIT_SUCCEEDED"
const val TRANSFER_DEPOSIT_FAILED = "TRANSFER_DEPOSIT_FAILED"

const val TRANSFER_TRANSACTION_CREATED = "TRANSFER_TRANSACTION_CREATED"
const val TRANSFER_FAILED = "TRANSFER_FAILED"
const val TRANSFER_SUCCEEDED = "TRANSFER_SUCCEEDED"


@DomainEvent(name = TRANSFER_TRANSACTION_CREATED)
data class TransferTransactionCreatedEvent(
    val transferId: UUID,
    val sourceAccountId: UUID,
    val sourceBankAccountId: UUID,
    val destinationAccountId: UUID,
    val destinationBankAccountId: UUID,
    val transferAmount: BigDecimal,
) : Event<TransferTransactionAggregate>(
    name = TRANSFER_TRANSACTION_CREATED,
)

@DomainEvent(name = TRANSFER_SUCCEEDED)
data class TransactionSucceededEvent(
    val transferId: UUID,
    val sourceAccountId: UUID,
    val sourceBankAccountId: UUID,
    val destinationAccountId: UUID,
    val destinationBankAccountId: UUID,
) : Event<TransferTransactionAggregate>(
    name = TRANSFER_SUCCEEDED,
)
@DomainEvent(name = TRANSFER_WITHDRAWAL_SUCCEEDED)
data class TransactionWithdrawalSucceededEvent(
    val transferId: UUID,
    val sourceAccountId: UUID,
    val sourceBankAccountId: UUID,
    val destinationAccountId: UUID,
    val destinationBankAccountId: UUID,
) : Event<TransferTransactionAggregate>(
    name = TRANSFER_WITHDRAWAL_SUCCEEDED,
)

@DomainEvent(name = TRANSFER_DEPOSIT_SUCCEEDED)
data class TransactionDepositSucceededEvent(
    val transferId: UUID,
    val sourceAccountId: UUID,
    val sourceBankAccountId: UUID,
    val destinationAccountId: UUID,
    val destinationBankAccountId: UUID,
) : Event<TransferTransactionAggregate>(
    name = TRANSFER_DEPOSIT_SUCCEEDED,
)

@DomainEvent(name = TRANSFER_FAILED)
data class TransactionFailedEvent(
    val transferId: UUID,
    val sourceAccountId: UUID,
    val sourceBankAccountId: UUID,
    val destinationAccountId: UUID,
    val destinationBankAccountId: UUID,
) : Event<TransferTransactionAggregate>(
    name = TRANSFER_FAILED,
)

@DomainEvent(name = TRANSFER_WITHDRAWAL_FAILED)
data class TransactionWithdrawalFailedEvent(
    val transferId: UUID,
    val sourceAccountId: UUID,
    val sourceBankAccountId: UUID,
    val destinationAccountId: UUID,
    val destinationBankAccountId: UUID,
) : Event<TransferTransactionAggregate>(
    name = TRANSFER_WITHDRAWAL_FAILED,
)

@DomainEvent(name = TRANSFER_DEPOSIT_FAILED)
data class TransactionDepositFailedEvent(
    val transferId: UUID,
    val sourceAccountId: UUID,
    val sourceBankAccountId: UUID,
    val destinationAccountId: UUID,
    val destinationBankAccountId: UUID,
) : Event<TransferTransactionAggregate>(
    name = TRANSFER_DEPOSIT_FAILED,
)
