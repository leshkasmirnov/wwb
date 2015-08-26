/**
 * 
 */
package com.wildwestbank.modules.app.services;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wildwestbank.BaseJunitTest;
import com.wildwestbank.modules.app.exceptions.TransactionExecutionException;
import com.wildwestbank.modules.app.internal.TransactionTypes;
import com.wildwestbank.modules.common.db.dao.AccountsRepository;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class TransactionExecutorImplTest extends BaseJunitTest {

	@Autowired
	private TransactionExecutor transactionExecutor;

	@Autowired
	private AccountsRepository accountsRepository;

	@Test
	public void testUp() {
		try {
			String sourceAccountNumber = "12321321321";

			Account account = accountsRepository.findByAccountNumber(sourceAccountNumber);
			BigDecimal currentAmount = account.getAmount();

			Transaction transaction = new Transaction();
			transaction.setSourceAccountNumber(sourceAccountNumber);
			transaction.setTransactionType(TransactionTypes.UP.getValue());
			BigDecimal addingSumm = new BigDecimal(1000);
			transaction.setSumm(addingSumm);

			transactionExecutor.execute(transaction, 1);

			Account accountAfterTransaction = accountsRepository
					.findByAccountNumber(sourceAccountNumber);

			Assert.assertEquals(currentAmount.add(addingSumm), accountAfterTransaction.getAmount());
		} catch (TransactionExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDown() {
		try {
			String sourceAccountNumber = "12321321321";

			Account account = accountsRepository.findByAccountNumber(sourceAccountNumber);
			BigDecimal currentAmount = account.getAmount();

			Transaction transaction = new Transaction();
			transaction.setSourceAccountNumber(sourceAccountNumber);
			transaction.setTransactionType(TransactionTypes.DOWN.getValue());
			BigDecimal subtractingSumm = new BigDecimal(1000);
			transaction.setSumm(subtractingSumm);

			transactionExecutor.execute(transaction, 1);

			Account accountAfterTransaction = accountsRepository
					.findByAccountNumber(sourceAccountNumber);

			Assert.assertEquals(currentAmount.subtract(subtractingSumm),
					accountAfterTransaction.getAmount());
		} catch (TransactionExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTransfer() {
		try {
			String sourceAccountNumber = "12321321321";
			String destinationAccountNumber = "34534543544";

			Account sourceAccount = accountsRepository.findByAccountNumber(sourceAccountNumber);
			BigDecimal sourceCurrentAmount = sourceAccount.getAmount();
			Account destAccount = accountsRepository.findByAccountNumber(destinationAccountNumber);
			BigDecimal destCurrentAmount = destAccount.getAmount();

			Transaction transaction = new Transaction();
			transaction.setSourceAccountNumber(sourceAccountNumber);
			transaction.setDestinationAccountNumber(destinationAccountNumber);
			transaction.setTransactionType(TransactionTypes.TRANSFER.getValue());
			BigDecimal summ = new BigDecimal(10000);
			transaction.setSumm(summ);

			transactionExecutor.execute(transaction, 1);

			Account sourceAccountAfterTransaction = accountsRepository
					.findByAccountNumber(sourceAccountNumber);
			Account destAccountAfterTransaction = accountsRepository
					.findByAccountNumber(destinationAccountNumber);

			Assert.assertEquals(sourceCurrentAmount.subtract(summ),
					sourceAccountAfterTransaction.getAmount());
			Assert.assertEquals(destCurrentAmount.add(summ),
					destAccountAfterTransaction.getAmount());
		} catch (TransactionExecutionException e) {
			e.printStackTrace();
		}
	}
}
