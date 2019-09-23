package com.es.repository;

import com.es.esentity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface ArticleRepository extends ElasticsearchCrudRepository<Article,String>{
}
