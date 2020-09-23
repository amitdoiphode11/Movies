package com.eaglesoft.movies.business.cache.database

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.eaglesoft.movies.business.cache.model.CeMovie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: CeMovie?): Long?

    @Query("SELECT * FROM movie")
    suspend fun getList(): List<CeMovie?>?

    @Query("SELECT * FROM movie")
    suspend fun getPagedList(): PagingSource.LoadResult.Page<Int, CeMovie>
}