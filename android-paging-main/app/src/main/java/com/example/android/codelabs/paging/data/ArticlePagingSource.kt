package com.example.android.codelabs.paging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import kotlin.math.max

private const val STARTING_KEY = 0
private val firstArticleCreatedTime = LocalDateTime.now()
private const val LOAD_DELAY_MILLIS = 3_000L

class ArticlePagingSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // 첫 번째 로드인 경우 STARTING_KEY
        val start = params.key ?: STARTING_KEY
        // params.loadSize가 암시하는 만큼 항목 로드
        val range = start.until(start + params.loadSize)

        if (start != STARTING_KEY) delay(LOAD_DELAY_MILLIS)

        return LoadResult.Page( // 로드를 성공했을 때
                data = range.map { number ->
                    Article(
                            // 연속적으로 증가사는 정수인 값이 기사의 id가 됨
                            id = number,
                            title = "Article $number",
                            description = "This describes article $number",
                            created = firstArticleCreatedTime.minusDays(number.toLong())
                    )
                },

                // STARTING_KEY 뒤가 로드되지 않도록
                prevKey = when (start) {
                    STARTING_KEY -> null
                    else -> ensureValidKey(key = range.first - params.loadSize)
                },
                nextKey = range.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // anchorPosition과 가장 가까운 데이터를 가져오자
        // 그 다음 id - (state.config.pageSize / 2)를 버퍼로 반환한다
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}