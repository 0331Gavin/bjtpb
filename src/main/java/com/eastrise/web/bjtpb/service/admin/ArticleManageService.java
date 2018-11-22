package com.eastrise.web.bjtpb.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.repository.ArticleManageRepository;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.eastrise.web.bjtpb.entity.TArticleManage;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class ArticleManageService {
    @Autowired
    private CommonQueryRepository commonQueryRepository;
    @Autowired
    private ArticleManageRepository articleManageRepository;

    public List<Map<String, Object>> getArticleGateGory() throws Exception {
        String sql = "select t.* from T_SYS_ARTICLEMANAGE t where t.status <>0 order by t.article_order";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < result.size(); i++) {
            if(!(result.get(i).get("ID")+"").equals("0")){
                Map<String, Object> map = new HashMap<String, Object>();
                String parentId = result.get(i).get("PARENT_ID") + "";
                map.put("id", Integer.valueOf(result.get(i).get("ID") + ""));
                map.put("categoryName", result.get(i).get("CATEGORY_NAME"));
                if (!parentId.equals("0")) {
                    map.put("_parentId", Integer.valueOf(parentId));
                }
                arrayList.add(map);
            }
        }
        return arrayList;
    }

    public TArticleManage findArticle(String id) {
        TArticleManage articleManage = articleManageRepository.findByIdAndStatus(Integer.valueOf(id));
        return articleManage;
    }
    public TArticleManage findArticleByCode(String code,String status){
        TArticleManage articleManage = articleManageRepository.findByCategorycodeAndStatus(code,status);
        return articleManage;
    }

    public List<TArticleManage> findArticleByParentId(String parentId,String status){
        List<TArticleManage> articleManages = articleManageRepository.findByParentidAndStatusOrderByArticleorder(parentId,status);
        return articleManages;
    }

    public TArticleManage save(TArticleManage articleManage) throws Exception {
        return articleManageRepository.save(articleManage);
    }

    public void del(String id) {
        articleManageRepository.deleteById(Integer.valueOf(id));
    }

    public boolean isExist(ArticleTypeForm articleTypeForm) throws Exception {
        String sql = " select t.* from T_SYS_ARTICLEMANAGE t where 1=1 and t.category_name='" + articleTypeForm.getCategoryname() + "'";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    public JSONObject getArticleContent() throws Exception {
        JSONObject json = new JSONObject();
        String sql = "select t.* from T_ARTICLE t where t.status <>0";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        json.put("rows", result);
        json.put("total", result.size());
        return json;
    }
    /**
     * 通过ID查询下级
     * @param
     * @return
     */
    public List<TArticleManage> findChildArticleById() {
        Specification specification=new Specification<TArticleManage>() {
            @Override
            public Predicate toPredicate(Root<TArticleManage> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                int rootId=0;
                predicates.add(criteriaBuilder.equal(root.get("id"), rootId ));
                predicates.add(criteriaBuilder.equal(root.get("status"), "1"));
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };

        return (List<TArticleManage>) articleManageRepository.findAll(specification);
    }

    /**
     * 获得seq 文章类别集合
     * @param id
     * @return
     */
    public List<TArticleManage> getCategorySeqListById(String id){
        List<TArticleManage> articleManageList = Lists.newArrayList();
        TArticleManage articleManage = findArticle(id);
        if(StringUtils.isNotEmpty(articleManage.getCategoryseq())){
            String[] ids = articleManage.getCategoryseq().split("\\.");
            for(String s:ids){
                articleManage = findArticle(s);
                articleManageList.add(articleManage);
            }
        }
        return articleManageList;
    }
}
