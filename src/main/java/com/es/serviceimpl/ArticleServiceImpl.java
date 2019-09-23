package com.es.serviceimpl;

import com.es.esentity.Article;
import com.es.service.ArticleService;
import com.es.utils.Constants;
import com.es.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author luozuyi
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Result singleTitle(String word, Pageable pageable) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            if(StringUtils.isBlank(word)){
                code = "1";
                msg = "搜索条件不能为空";
            }else{
                SearchQuery searchQuery = new NativeSearchQueryBuilder()
                                            .withQuery(queryStringQuery(word))
                                            .withPageable(pageable)
                                            .build();
                List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
                result.setData(articles);
                code = Constants.SUCCESS;
                msg = "查询成功";
            }
        }catch (Exception e){
            code = Constants.ERROR;
            msg = "后台出错";
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    @Override
    public Result singleMatch(String content, Pageable pageable) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            if(StringUtils.isBlank(content)){
                code = "1";
                msg = "搜索条件不能为空";
            }else{
                SearchQuery searchQuery = new NativeSearchQueryBuilder()
                        .withQuery(matchQuery("content", content))
                        .withPageable(pageable)
                        .build();
                List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
                result.setData(articles);
                code = Constants.SUCCESS;
                msg = "查询成功";
            }
        }catch (Exception e){
            code = Constants.ERROR;
            msg = "后台出错";
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    @Override
    public Result singleTerm(Integer userId, Pageable pageable) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            if(userId == null){
                code = "1";
                msg = "用户id不能为空";
            }else{
                SearchQuery searchQuery = new NativeSearchQueryBuilder()
                        .withQuery(termQuery("userId", userId))
                        .withPageable(pageable)
                        .build();
                List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
                result.setData(articles);
                code = Constants.SUCCESS;
                msg = "查询成功";
            }
        }catch (Exception e){
            code = Constants.ERROR;
            msg = "后台出错";
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    @Override
    public Result multiMatch(String title, Pageable pageable) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            if(StringUtils.isBlank(title)){
                code = "1";
                msg = "title不能为空";
            }else{
                SearchQuery searchQuery = new NativeSearchQueryBuilder()
                        .withQuery(multiMatchQuery(title, "title", "content"))
                        .withPageable(pageable)
                        .build();
                List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
                result.setData(articles);
                code = Constants.SUCCESS;
                msg = "查询成功";
            }
        }catch (Exception e){
            code = Constants.ERROR;
            msg = "后台出错";
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    @Override
    public Result bool(String title, Integer userId, Integer weight) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            if(userId == null && StringUtils.isBlank(title)){
                code = "1";
                msg = "userId和title不能同时为空";
            }else{
                SearchQuery searchQuery = new NativeSearchQueryBuilder()
                        .withQuery(
                                boolQuery()
                                .must(termQuery("userId", userId))
                                .should(rangeQuery("weight").lt(weight))
                                .must(matchQuery("title", title)))
                        .build();
                List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
                result.setData(articles);
                code = Constants.SUCCESS;
                msg = "查询成功";
            }
        }catch (Exception e){
            code = Constants.ERROR;
            msg = "后台出错";
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
