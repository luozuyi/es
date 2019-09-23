package com.es.controller;

import com.es.service.ArticleService;
import com.es.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     *单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     * @param word 单字符串
     * @param pageable 分页对象
     * @return
     */
    @GetMapping(value = "articles/ingle-word")
    Result singleTitle(String word, @PageableDefault Pageable pageable){
        return articleService.singleTitle(word,pageable);
    }

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     * @param word 单字符串
     * @param pageable 分页对象
     * @return
     */
    @GetMapping(value = "articles/single-word/sorting")
    Result singleTitleSorting(String word, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable){
        return articleService.singleTitle(word,pageable);
    }

    /**
     *某字段按字符串模糊查询
     * 查询某个字段中模糊包含目标字符串，使用matchQuery
     * @param content 内容
     * @param pageable 分页
     * @return
     */
    @GetMapping(value = "articles/single-match")
    Result singleMatch(String content, @PageableDefault Pageable pageable){
        return articleService.singleMatch(content,pageable);
    }

    /**
     * term匹配，即不分词匹配，你传来什么值就会拿你传的值去做完全匹配
     * @param userId 用户id
     * @param pageable 分页对象
     * @return
     */
    @GetMapping(value = "articles/single-term")
    public Result singleTerm(Integer userId, @PageableDefault Pageable pageable) {
        return articleService.singleTerm(userId,pageable);
    }

    /**
     * 多字段匹配
     * 如果我们希望title，content两个字段去匹配某个字符串，
     * 只要任何一个字段包括该字符串即可，就可以使用multimatch
     * @param title 参数title
     * @param pageable 分页
     * @return
     */
    @GetMapping(value = "articles/multi-match")
    public Result multiMatch(String title, @PageableDefault Pageable pageable) {
        return articleService.multiMatch(title,pageable);
    }

    /**
     * 组合查询
     * @param title title
     * @param userId 用户id
     * @param weight 权重
     * @return
     */
    @GetMapping(value = "articles/bool")
    public Result bool(String title, Integer userId, Integer weight) {
        return articleService.bool(title,userId,weight);
    }
}
