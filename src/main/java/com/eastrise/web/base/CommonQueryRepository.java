package com.eastrise.web.base;

import com.eastrise.utils.ChangeNameForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by gzq on 2018/11/13 15:50
 */
@Repository
public class CommonQueryRepository {
    private Logger logger = LoggerFactory.getLogger(CommonQueryRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 分页查询
     * @param  pageSize，pageNumber，sql
     * @return Map<String, Object>
     * @author 郭志强
     */
    public ApiPageResponse findPageBySqlQuery(int pageSize, int pageNumber, String sql) {
        //查询结果
        String pageSql = PageSqlUtil.getPageSql(pageSize,pageNumber, sql);
        logger.info("pageSql:"+pageSql);
        List<Map<String, Object>> pageResult = jdbcTemplate.queryForList(pageSql);
        return ApiPageResponse.of(findCountBySqlQuery(sql),ChangeNameForm.changeSqlResult(pageResult));
    }
    /**
     * 查询sql 的结果数量
     * @param  sql 查询语句
     * @return int
     * @author 郭志强
     */
    public int findCountBySqlQuery(String sql){
        logger.info("Sql:"+sql);
        return jdbcTemplate.queryForList(sql).size();
    }
    /**
     * 查询sql
     * @param  sql 查询语句
     * @return List<Map<String, Object>>
     * @author lh
     */
    public List<Map<String, Object>> findResultBySqlQuery(String sql ) throws Exception{
        logger.info("Sql:"+sql);
        List<Map<String, Object>> queryResult = jdbcTemplate.queryForList(sql);
        return queryResult;
    }
}
