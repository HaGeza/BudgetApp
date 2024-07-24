package com.example.budgetapp.data.dao

import androidx.test.filters.SmallTest
import com.example.budgetapp.data.database.AppDatabase
import com.example.budgetapp.data.di.DataModule
import com.example.budgetapp.domain.model.Account
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import java.util.Currency
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DataModule::class)
@SmallTest
class AccountDaoTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: AppDatabase

    private lateinit var dao: AccountDao
    private lateinit var account1: Account
    private lateinit var account2: Account

    @Before
    fun setUp() = runTest {
        hiltRule.inject()
        dao = db.accountDao()

        account1 = Account(
            "Account 1", BigDecimal("100.00"), Currency.getInstance("USD"), 1
        )
        account2 = Account(
            "Account 2", BigDecimal("200.00"), Currency.getInstance("EUR"), 2
        )

        dao.insert(account1)
        dao.insert(account2)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `getById finds existing ids`() = runTest {
        assert(dao.getById(1).first() == account1)
        assert(dao.getById(2).first() == account2)
    }

    @Test
    fun `getById returns null for non-existing ids`() = runTest {
        assertNull(dao.getById(3).first())
    }

    @Test
    fun `getAll returns all accounts`() = runTest {
        val accounts = dao.getAll().first()
        assert(accounts.size == 2)
        assert(accounts.contains(account1))
        assert(accounts.contains(account2))
    }
}
