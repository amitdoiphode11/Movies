package com.eaglesoft.movies.business.network.mapper

import com.eaglesoft.movies.business.domain.model.Movies
import com.eaglesoft.movies.business.network.model.WeMovies
import com.eaglesoft.movies.business.util.EntityMapper

class NetworkMapper() : EntityMapper<WeMovies, Movies> {

    override fun mapFromEntity(entity: WeMovies?): Movies? {
        return Movies(movies = entity?.results)
    }

    override fun mapToEntity(domainModel: Movies?): WeMovies? {
        return WeMovies(results = domainModel?.movies)
    }
}