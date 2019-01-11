package com.eastrise.web.bjtpb.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.base.Constants;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.entity.TArticle;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import com.eastrise.web.bjtpb.repository.ArticleContentRepository;
import com.eastrise.web.bjtpb.repository.ArticleManageRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
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
    @Autowired
    private ArticleContentRepository articleContentRepository;


    public List<TArticle> getArticleListByArticleTypeId(String articleTypeId,String articleTag){
        return articleContentRepository.findAllByArticleTypeIdAndStatusAndArticleTagOrderByIsTopDescPublishTimeDesc(articleTypeId,"1",articleTag);
    }
    public List<Map<String, Object>> findById(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* ,g.category_name from T_ARTICLE  t   left join t_Sys_Articlemanage g on t.article_type_id=g.id  where t.id='"+id+"'");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }

    public ApiPageResponse findPublicPageData(int pageSize, int pageNumber, String articleTypeId, String KeyWord, String KeyWordType) {
       StringBuilder sql = new StringBuilder("select t.id,t.title,t.publish_dept_name deptName,t.publish_time time, type.category_name article_type,t.article_tag,t.is_open from T_ARTICLE t,T_SYS_ARTICLEMANAGE type where t.status='1' and t.article_type_id = type.id  ");
        if(StringUtils.isNotEmpty(articleTypeId)) sql.append(" and ( type.category_seq like '%."+articleTypeId+".%' or type.category_seq like '"+articleTypeId+".%' or type.category_seq like '%."+articleTypeId+"' or type.category_seq like '%"+articleTypeId+"%' ) ");
        if(StringUtils.isNotEmpty(KeyWordType)&& Constants.S_BT.equals(KeyWordType)&&StringUtils.isNotEmpty(KeyWord))
            sql.append(" and t.title like '%"+KeyWord+"%' ");
        if(StringUtils.isNotEmpty(KeyWordType)&& Constants.S_FBJG.equals(KeyWordType)&&StringUtils.isNotEmpty(KeyWord))
            sql.append(" and t.PUBLISH_DEPT_NAME like '%"+KeyWord+"%' ");
        if(StringUtils.isNotEmpty(KeyWordType)&& Constants.S_FBRQ.equals(KeyWordType)&&StringUtils.isNotEmpty(KeyWord))
            sql.append(" and t.publish_time like '%"+KeyWord+"%' ");
       sql.append(" order by t.is_top desc,T.seq,t.publish_time desc ");
        return commonQueryRepository.findPageBySqlQuery(pageSize,pageNumber,sql.toString());
    }
    public List<Map<String, Object>> getFilebyId(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from T_ATTACHMENT t  where t.buz_id='"+id+"'");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }

    public void upLookTotal(String id) {
        commonQueryRepository.executeUpdate("update t_Article t  set t.look_total=(t.look_total+1) where t.id='"+id+"'");
    }
}
