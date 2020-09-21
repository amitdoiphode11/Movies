package com.eaglesoft.movies.business.network.mapper

import com.eaglesoft.movies.business.domain.model.Movie
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.util.EntityMapper

class NetworkMapper() : EntityMapper<WeMovie, Movie> {

    override fun mapFromEntity(entity: WeMovie?): Movie? {
        return Movie(
            backdropPath = entity?.backdrop_path,
            genres = entity?.genres,
            homepage = entity?.homepage,
            id = entity?.id,
            imdbId = entity?.imdbId,
            originalLanguage = entity?.original_language,
            originalTitle = entity?.originalTitle,
            overview = entity?.overview,
            popularity = entity?.popularity,
            releaseDate = entity?.release_date,
            title = entity?.title,
            voteAverage = entity?.voteAverage,
            voteCount = entity?.vote_count,
        )
    }

    override fun mapToEntity(domainModel: Movie?): WeMovie? {
        return WeMovie(
            backdrop_path = domainModel?.backdropPath,
            genres = domainModel?.genres,
            homepage = domainModel?.homepage,
            id = domainModel?.id,
            imdbId = domainModel?.imdbId,
            original_language = domainModel?.originalLanguage,
            originalTitle = domainModel?.originalTitle,
            overview = domainModel?.overview,
            popularity = domainModel?.popularity,
            release_date = domainModel?.releaseDate,
            title = domainModel?.title,
            voteAverage = domainModel?.voteAverage,
            vote_count = domainModel?.voteCount
        )
    }

    fun mapFromEntityList(entities: List<WeMovie>?): List<Movie> {
        return entities?.map { mapFromEntity(it)!! }!!
    }

}