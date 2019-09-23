package com.es.service;

import com.es.utils.Result;
import org.springframework.data.domain.Pageable;

/**
 * @author luozuyi
 */
public interface ArticleService {
    /**
     * 单字符串全文查询
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     * @param word 字符串
     * @param pageable 分页
     * @return
     */
    Result singleTitle(String word, Pageable pageable);

    /**
     * 某字段按字符串模糊查询
     * 查询某个字段中模糊包含目标字符串，使用matchQuery
     * @param content 内容
     * @param pageable 分页
     * @return
     */
    Result singleMatch(String content, Pageable pageable);

    /**
     *Term查询
     * @param userId 用户查询
     * @param pageable 分页
     * @return
     */
    Result singleTerm(Integer userId, Pageable pageable);

    /**
     * 多个字段匹配某字符串
     * @param title 参数标题
     * @param pageable 分页对象
     * @return
     */
    Result multiMatch(String title, Pageable pageable);

    /**
     * 合并查询
     * boolQuery
     * @param title 标题
     * @param userId 用户id
     * @param weight 权重
     * @return
     */
    Result bool(String title, Integer userId, Integer weight);

    /**
     * 获取分词结果
     * @param searchContent 字符串
     * @return
     */
    Result getIkAnalyzeSearchTerms(String searchContent);
}
