package com.eastrise.web.bjtpb.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import com.eastrise.web.bjtpb.repository.ArticleManageRepository;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    private CommonQueryRepository commonQueryRepository;
    public List<Map<String, Object>> findById(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* ,g.category_name from T_ARTICLE  t   left join t_Sys_Articlemanage g on t.article_type_id=g.id  where t.id='"+id+"'");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }


}
