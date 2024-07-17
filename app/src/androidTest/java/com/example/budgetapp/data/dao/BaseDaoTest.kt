package com.example.budgetapp.data.dao

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@Entity(tableName = "test_table")
data class TestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val value: Int
)

@Dao
interface TestDao : BaseDao<TestEntity> {
    @Query("SELECT * FROM test_table")
    override fun getAll(): Flow<List<TestEntity>>

    @Query("SELECT * FROM test_table WHERE id = :id")
    override fun getById(id: Int): Flow<TestEntity?>
}

@Database(entities = [TestEntity::class], version = 1, exportSchema = false)
abstract class TestDatabase : RoomDatabase() {
    abstract fun dao(): TestDao
}


@RunWith(AndroidJUnit4::class)
@SmallTest
class BaseDaoTest {
    private lateinit var db: TestDatabase
    private lateinit var dao: BaseDao<TestEntity>
    private lateinit var entity1: TestEntity


    @Before
    fun setUp() = runBlocking {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestDatabase::class.java
        ).build()
        dao = db.dao()

        entity1 = TestEntity(1, "Entity 1", 100)
        dao.insert(entity1)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insert adds new entity`() = runBlocking {
        val entity2 = TestEntity(2, "Entity 2", 200)
        dao.insert(entity2)

        val entities = dao.getAll().first()
        assert(entities.size == 2)
        assert(entities.contains(entity1))
        assert(entities.contains(entity2))
        assert(dao.getById(2).first() == entity2)
    }

    @Test
    fun `update modifies existing entity`() = runBlocking {
        val updatedEntity = TestEntity(1, "Updated Entity 1", 200)
        dao.update(updatedEntity)

        val entities = dao.getAll().first()
        assert(entities.size == 1)
        assert(entities.contains(updatedEntity))
        assert(dao.getById(1).first() == updatedEntity)
    }

    @Test
    fun `delete removes existing entity`() = runBlocking {
        dao.delete(entity1)

        val entities = dao.getAll().first()
        assert(entities.isEmpty())
        assert(dao.getById(1).first() == null)
    }
}
