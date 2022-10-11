package com.organization.nytimes.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.organization.nytimes.data.api.ArticlesApi
import com.organization.nytimes.data.api.utils.FakeServer
import com.organization.nytimes.data.cache.ArticlesDatabase
import com.organization.nytimes.data.cache.Cache
import com.organization.nytimes.data.cache.RoomCache
import com.organization.nytimes.data.cache.daos.ArticlesDao
import com.organization.nytimes.data.di.CacheModule
import com.organization.nytimes.domain.repository.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@HiltAndroidTest
@UninstallModules(
    CacheModule::class
)
class ArticlesRepositoryImpTest {

    private val fakeServer = FakeServer()
    private lateinit var repository: ArticlesRepository
    private lateinit var api: ArticlesApi

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var cache: Cache

    @Inject
    lateinit var database: ArticlesDatabase

    @Inject
    lateinit var retrofitBuilder: Retrofit.Builder

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class TestCacheModule {
        @Binds
        abstract fun bindCache(cache: RoomCache): Cache

        companion object {

            @Provides
            @Singleton
            fun provideRoomDatabase(): ArticlesDatabase {
                return Room.inMemoryDatabaseBuilder(
                    InstrumentationRegistry.getInstrumentation().context,
                    ArticlesDatabase::class.java
                )
                    .allowMainThreadQueries()
                    .build()
            }
            @Provides
            fun provideArticlesDao(
                articleDatabase: ArticlesDatabase
            ): ArticlesDao = articleDatabase.articlesDao()
        }

    }

    @Before
    fun setup() {
        fakeServer.start()

        hiltRule.inject()

        api = retrofitBuilder
            .baseUrl(fakeServer.baseEndpoint)
            .build()
            .create(ArticlesApi::class.java)

        cache = RoomCache(database.articlesDao())

        repository = ArticlesRepositoryImp(
            cache,
            api
        )
    }

    @Test
    fun requestArticles() = runBlocking {
        // Given
        val expectedArticleId = 100000008485792L
        fakeServer.setHappyPathDispatcher()
        // When
        val resultArticles = repository.requestArticles("all-sections",7)
        // Then
        val product = resultArticles.first()
        assert(product.id == expectedArticleId)
    }

    @Test
    fun storeArticles() {
        // Given
        val expectedArticleId = 100000008565706L
        runBlocking {
            fakeServer.setHappyPathDispatcher()
            val resultArticles = repository.requestArticles("all-sections",7)
            // When
            repository.storeArticles(resultArticles)
            // Then
            val insertedValue = repository.getArticles().first()
            assert(insertedValue[0].id == expectedArticleId)
        }
    }

    @Test
    fun getArticles() {
    }

    @Test
    fun getArticle() {
    }

    @After
    fun teardown() {
        fakeServer.shutdown()
    }
}