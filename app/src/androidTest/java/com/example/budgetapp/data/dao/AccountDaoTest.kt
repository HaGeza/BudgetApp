package com.example.budgetapp.data.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.budgetapp.domain.model.Account
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal
import java.util.Currency

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}

@RunWith(AndroidJUnit4::class)
@SmallTest
class AccountDaoTest {
    private lateinit var dao: AccountDao
    private lateinit var db: AccountDatabase
    private lateinit var account1: Account
    private lateinit var account2: Account

    @Before
    fun setUp() = runBlocking {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AccountDatabase::class.java,
        ).build()
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
    fun `getById finds existing ids`() = runBlocking {
        assert(dao.getById(1).first() == account1)
        assert(dao.getById(2).first() == account2)
    }

    @Test
    fun `getById returns null for non-existing ids`() = runBlocking {
        assertNull(dao.getById(3).first())
    }

    @Test
    fun `getAll returns all accounts`() = runBlocking {
        val accounts = dao.getAll().first()
        assert(accounts.size == 2)
        assert(accounts.contains(account1))
        assert(accounts.contains(account2))
    }
}
