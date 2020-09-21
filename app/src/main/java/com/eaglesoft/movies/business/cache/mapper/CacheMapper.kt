package com.eaglesoft.movies.business.cache.mapper

import com.eaglesoft.movies.business.cache.model.CeMovie
import com.eaglesoft.movies.business.domain.model.Genre
import com.eaglesoft.movies.business.domain.model.Movie
import com.eaglesoft.movies.business.util.EntityMapper
import com.google.gson.Gson

class CacheMapper() : EntityMapper<CeMovie, Movie> {
    override fun mapFromEntity(entity: CeMovie?): Movie? {
        return Movie(
            backdropPath = entity?.backdropPath,
            genres = genreMapFromEntity(entity?.genres),
            homepage = entity?.homepage,
            id = entity?.id,
            imdbId = entity?.imdbId,
            originalLanguage = entity?.originalLanguage,
            originalTitle = entity?.originalTitle,
            overview = entity?.overview,
            popularity = entity?.popularity,
            releaseDate = entity?.releaseDate,
            title = entity?.title,
            voteAverage = entity?.voteAverage,
            voteCount = entity?.voteCount
        )
    }

    override fun mapToEntity(domainModel: Movie?): CeMovie? {
        return CeMovie(
            backdropPath = domainModel?.backdropPath,
            genres = genreMapToEntity(domainModel?.genres),
            homepage = domainModel?.homepage,
            id = domainModel?.id,
            imdbId = domainModel?.imdbId,
            originalLanguage = domainModel?.originalLanguage,
            originalTitle = domainModel?.originalTitle,
            overview = domainModel?.overview,
            popularity = domainModel?.popularity,
            releaseDate = domainModel?.releaseDate,
            title = domainModel?.title,
            voteAverage = domainModel?.voteAverage,
            voteCount = domainModel?.voteCount
        )
    }

    private fun genreMapToEntity(genre: List<Genre>?): String? {
        return Gson().toJson(genre)
    }

    private fun genreMapFromEntity(genre: String?): List<Genre>? {
        return Gson().fromJson(genre, Array<Genre>::class.java).toList()
    }


}