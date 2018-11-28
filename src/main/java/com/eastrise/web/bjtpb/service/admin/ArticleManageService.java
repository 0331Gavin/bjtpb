package com.eastrise.web.bjtpb.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleContentForm;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.entity.TArticle;
import com.eastrise.web.bjtpb.entity.TAttachment;
import com.eastrise.web.bjtpb.repository.ArticleContentRepository;
import com.eastrise.web.bjtpb.repository.ArticleManageRepository;
import com.eastrise.web.bjtpb.repository.IAttachmentRepository;
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
    @Autowired
    private ArticleContentRepository articleContentRepository;
    @Autowired
    private IAttachmentRepository attachmentRepository;

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

    public TArticleManage savearticleManage(TArticleManage articleManage) throws Exception {
        return articleManageRepository.save(articleManage);
    }

    public void delArticle(String id) {
        articleManageRepository.deleteById(Integer.valueOf(id));
    }

    public boolean isArticleExist(ArticleTypeForm articleTypeForm) throws Exception {
        String sql = " select t.* from T_SYS_ARTICLEMANAGE t where 1=1 and t.category_name='" + articleTypeForm.getCategoryname() + "'";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 验证文章编码是否重复
     * @param articleTypeForm
     * @return
     * @throws Exception
     */
    public boolean isArticleCodeExist(ArticleTypeForm articleTypeForm) throws Exception {
        String sql = " select t.* from T_SYS_ARTICLEMANAGE t where 1=1 and t.category_code='" + articleTypeForm.getCategorycode() + "'";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }
    public boolean isArticleContentExist(ArticleContentForm articleContentForm) throws Exception {
        String sql = " select t.* from T_ARTICLE t where 1=1 and t.title='" + articleContentForm.getTiltle() + "'";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    public JSONObject getArticleContent(ArticleContentForm articleContentForm) throws Exception {
        JSONObject json = new JSONObject();
        StringBuilder sb =new StringBuilder("select t.*,a.category_name from T_ARTICLE t  left join T_SYS_ARTICLEMANAGE a on t.article_type_id =a.id where 1=1 ");
        if(StringUtils.isNotEmpty(articleContentForm.getTiltle())){
            sb.append(" and t.TITLE like '%"+articleContentForm.getTiltle()+"%'");
        }
        if(StringUtils.isNotEmpty(articleContentForm.getPublishDept())){
            sb.append(" and t.PUBLISH_DEPT_NAME like '%"+articleContentForm.getPublishDept()+"%'");
        }
        if(StringUtils.isNotEmpty(articleContentForm.getKssj())){
            sb.append(" and t.PUBLISH_TIME >='"+articleContentForm.getKssj()+"'");
        }
        if(StringUtils.isNotEmpty(articleContentForm.getJssj())){
            sb.append(" and t.PUBLISH_TIME <='"+articleContentForm.getJssj()+"'");
        }
        if(StringUtils.isNotEmpty(articleContentForm.getArticleTypeId())){
            sb.append(" and t.ARTICLE_TYPE_ID ='"+articleContentForm.getArticleTypeId()+"'");
        }
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sb.toString());
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
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("articleorder")));
                return criteriaQuery.getRestriction();
            }
        };
        return (List<TArticleManage>) articleManageRepository.findAll(specification);
    }

    /**
     * 获得seq 文章类别集合
     * @param id
     * @return
     */
    public List<TArticleManage> getCategorySeqListById(String id) {
        List<TArticleManage> articleManageList = Lists.newArrayList();
        TArticleManage articleManage = findArticle(id);
        if (StringUtils.isNotEmpty(articleManage.getCategoryseq())) {
            String[] ids = articleManage.getCategoryseq().split("\\.");
            for (String s : ids) {
                articleManage = findArticle(s);
                articleManageList.add(articleManage);
            }
        }
        return articleManageList;
    }

    public TArticle saveArticleContent(TArticle tArticle)throws Exception {
        return articleContentRepository.save(tArticle);
    }

    public TArticle findArticleContByid(String id) {
        TArticle TArticle = articleContentRepository.findByIdAndStatus(id);
        return TArticle;
    }

    public void delArticleCont(String id) {
        articleContentRepository.deleteById(id);
    }


    public TAttachment saveAttachment(TAttachment tAttachment){
       return attachmentRepository.save(tAttachment);
    }
    public TAttachment findTAttachmentByBuzId(String id){
        return attachmentRepository.findByBuzId(id);
    }
    public TAttachment findAttachmentById(String id){
        return attachmentRepository.findByIdAndStatus(id);
    }
    public void delAttachment(String id) {
        attachmentRepository.deleteById(id);
    }
}
