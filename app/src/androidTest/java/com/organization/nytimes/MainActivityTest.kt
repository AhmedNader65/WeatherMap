package com.organization.nytimes

import androidx.compose.material.Text
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.organization.nytimes.data.ArticlesRepositoryImp
import com.organization.nytimes.data.FakeRepository
import com.organization.nytimes.data.di.CacheModule
import com.organization.nytimes.data.di.DataModule
import com.organization.nytimes.domain.repository.ArticlesRepository
import com.organization.nytimes.ui.main.ARTICLE_DETAILS_TEST_TAG
import com.organization.nytimes.ui.main.ARTICLE_LIST_TEST_TAG
import com.organization.nytimes.ui.main.ArticlesScreen
import com.organization.nytimes.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(
    DataModule::class
)
class MainActivityTest {

    @get:Rule()
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Module
    @InstallIn(ActivityRetainedComponent::class)
    abstract class DataModule {
        @Binds
        @ActivityRetainedScoped
        abstract fun bindArticlesRepository(repository: FakeRepository): ArticlesRepository
    }

    @Inject
    lateinit var repository: FakeRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testFirstItemInTheList_displayingFirstArticle() {
        runBlocking {
            repository.storeArticles(repository.requestArticles("", 7))
            composeRule
                .onNodeWithTag(testTag = ARTICLE_LIST_TEST_TAG)
                .onChildren().onFirst().assert(hasText(repository.localArticles.first().title))
        }
    }

    @Test
    fun testNavigation_onItemClick_opensDetails() {
        runBlocking {
            repository.storeArticles(repository.requestArticles("", 7))
            composeRule
                .onNodeWithTag(testTag = ARTICLE_LIST_TEST_TAG)
                .onChildren().onFirst().assert(hasText(repository.localArticles.first().title))
        }
        val device = UiDevice.getInstance(getInstrumentation())

        val lazyColumn: UiObject2 = device.findObject(
            By.res(ARTICLE_LIST_TEST_TAG)
        )
        lazyColumn.children[0].click()
        composeRule
            .onNodeWithTag(testTag = ARTICLE_DETAILS_TEST_TAG)
            .onChildren().assertAny(hasText(repository.localArticles.first().title))

    }
}