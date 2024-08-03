package com.example.budgetapp.presentation.viewmodel.dataVM

import androidx.test.filters.SmallTest
import com.example.budgetapp.TestDispatcherRule
import com.example.budgetapp.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

data class DomainModel(val id: Int)

data class PresentationModel(val id: Int)

class TestDataViewModel(repository: Repository<DomainModel>) :
    DataViewModel<DomainModel, PresentationModel>(repository) {
    override fun presentationToDomain(presentation: PresentationModel): DomainModel {
        return DomainModel(presentation.id)
    }

    override fun domainToPresentation(domain: DomainModel): PresentationModel {
        return PresentationModel(domain.id)
    }
}

@SmallTest
class DataViewModelTest {
    @get:Rule
    val mainDispatcherRule = TestDispatcherRule()

    private lateinit var dataViewModel: TestDataViewModel
    private val mockRepository = mockk<Repository<DomainModel>>()

    @Before
    fun setUp() {
        dataViewModel = TestDataViewModel(mockRepository)
    }

    @Test
    fun `getAll returns all entities`() = runTest {
        val domainModels = listOf(DomainModel(1), DomainModel(2), DomainModel(3))
        every { mockRepository.getAll() } returns flowOf(domainModels)

        val presentationModels = dataViewModel.getAll().first()

        assert(presentationModels.size == domainModels.size)
        assert(presentationModels[0].id == domainModels[0].id)
        assert(presentationModels[1].id == domainModels[1].id)
        assert(presentationModels[2].id == domainModels[2].id)
    }

    @Test
    fun `getById returns existing entity`() = runTest {
        val domainModels = listOf(DomainModel(1), DomainModel(2))
        every { mockRepository.getById(1) } returns flowOf(domainModels[0])
        every { mockRepository.getById(2) } returns flowOf(domainModels[1])

        val presentationModel1 = dataViewModel.getById(1).first()
        val presentationModel2 = dataViewModel.getById(2).first()

        assert(presentationModel1?.id == domainModels[0].id)
        assert(presentationModel2?.id == domainModels[1].id)
    }

    @Test
    fun `getById returns null for non-existing entity`() = runTest {
        every { mockRepository.getById(1) } returns flowOf(null)

        val presentationModel = dataViewModel.getById(1).first()

        assert(presentationModel == null)
    }

    @Test
    fun `insert calls repository insert`() = runTest {
        coEvery { mockRepository.insert(any()) } returns Unit

        val presentationModel = PresentationModel(1)
        val domainModel = DomainModel(1)
        dataViewModel.insert(presentationModel)

        coVerify { mockRepository.insert(domainModel) }
    }

    @Test
    fun `update calls repository update`() = runTest {
        coEvery { mockRepository.update(any()) } returns Unit

        val presentationModel = PresentationModel(1)
        val domainModel = DomainModel(1)
        dataViewModel.update(presentationModel)

        coVerify { mockRepository.update(domainModel) }
    }

    @Test
    fun `delete calls repository delete`() = runTest {
        coEvery { mockRepository.delete(any()) } returns Unit

        val presentationModel = PresentationModel(1)
        val domainModel = DomainModel(1)
        dataViewModel.delete(presentationModel)

        coVerify { mockRepository.delete(domainModel) }
    }
}
