package com.example.moviecatalogue.util

import org.mockito.Mockito.`when`
import org.mockito.ArgumentMatchers.anyInt
import androidx.paging.PagedList
import org.mockito.Mockito.mock
import org.mockito.stubbing.Answer

object PagedListUtil {

    @Suppress("UNCHECKED_CAST")
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList: PagedList<*> = mock(PagedList::class.java)

        val answer: Answer<T> = Answer { invocation ->
            val index = invocation.arguments[0] as Int
            list[index]
        }

        `when`<T>(pagedList[anyInt()] as T).thenAnswer(answer)
        `when`(pagedList.size).thenReturn(list.size)
        return pagedList as PagedList<T>
    }
}