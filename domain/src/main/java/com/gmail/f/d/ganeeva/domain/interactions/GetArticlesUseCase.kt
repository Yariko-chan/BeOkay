package com.gmail.f.d.ganeeva.domain.interactions

import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.entity.ArticleDomainModel
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase
import com.gmail.f.d.ganeeva.domain.utils.convertArticleDataToDomain

import javax.inject.Inject

import io.reactivex.Observable

/**
 * Created by Diana on 19.11.2017 at 14:32.
 */

class GetArticlesUseCase(internal var restService: RestService) : UseCase<Void, List<ArticleDomainModel>>() {
    override fun buildUseCase(param: Void?): Observable<List<ArticleDomainModel>> {
        return restService.getArticles(50, 0)
                .flatMap { articles -> Observable.fromArray(*articles) }
                .map { articleDataModel -> convertArticleDataToDomain(articleDataModel) }
                .toList()
                .toObservable()
    }

}
