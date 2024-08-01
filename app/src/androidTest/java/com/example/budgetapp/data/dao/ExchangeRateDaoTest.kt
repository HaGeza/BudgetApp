package com.example.budgetapp.data.dao

import androidx.test.filters.SmallTest
import com.example.budgetapp.data.database.AppDatabase
import com.example.budgetapp.data.di.DataModule
import com.example.budgetapp.domain.model.ExchangeRate
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
import java.util.Currency
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DataModule::class)
@SmallTest
class ExchangeRateDaoTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: AppDatabase

    private lateinit var dao: ExchangeRateDao
    private val usd = Currency.getInstance("USD")
    private val eur = Currency.getInstance("EUR")
    private val exchangeRate1 = ExchangeRate(usd, eur, 0.85, 1)
    private val exchangeRate2 = ExchangeRate(eur, usd, 1.18, 2)

    @Before
    fun setUp() = runTest {
        hiltRule.inject()
        dao = db.exchangeRateDao()

        dao.insert(exchangeRate1)
        dao.insert(exchangeRate2)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `getById finds existing ids`() = runTest {
        assert(dao.getById(1).first() == exchangeRate1)
        assert(dao.getById(2).first() == exchangeRate2)
    }

    @Test
    fun `getById returns null for non-existing ids`() = runTest {
        assertNull(dao.getById(3).first())
    }

    @Test
    fun `getAll returns all accounts`() = runTest {
        val accounts = dao.getAll().first()
        assert(accounts.size == 2)
        assert(accounts.contains(exchangeRate1))
        assert(accounts.contains(exchangeRate2))
    }

    @Test
    fun `getBySource finds correct item`() = runTest {
        assert(dao.getBySource("USD").first() == exchangeRate1)
        assert(dao.getBySource("EUR").first() == exchangeRate2)
    }

    @Test
    fun `getBySource returns null for non-existing source`() = runTest {
        assertNull(dao.getBySource("GBP").first())
    }
}
