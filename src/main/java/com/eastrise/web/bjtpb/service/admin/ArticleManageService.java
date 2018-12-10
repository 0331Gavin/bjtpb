package com.eastrise.web.bjtpb.service.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleContentForm;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.entity.TArticle;
import com.eastrise.web.bjtpb.entity.TAttachment;
import com.eastrise.web.bjtpb.entity.TSysSjzd;
import com.eastrise.web.bjtpb.repository.ArticleContentRepository;
import com.eastrise.web.bjtpb.repository.ArticleManageRepository;
import com.eastrise.web.bjtpb.repository.IAttachmentRepository;
import com.eastrise.web.bjtpb.repository.ISjzdRepository;
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
        String sql = "select t.*,z.sjmc from T_SYS_ARTICLEMANAGE t left join T_SYS_SJZD z on t.view_model =z.sjbm  where t.status >'-1' order by t.article_order";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < result.size(); i++) {
            if(!(result.get(i).get("ID")+"").equals("-1")){
                Map<String, Object> map = new HashMap<String, Object>();
                String parentId = result.get(i).get("PARENT_ID") + "";
                map.put("id", Integer.valueOf(result.get(i).get("ID") + ""));
                map.put("categorySeq", result.get(i).get("CATEGORY_SEQ"));
                map.put("categoryCode", result.get(i).get("CATEGORY_CODE"));
                map.put("categoryName", result.get(i).get("CATEGORY_NAME"));
                map.put("viewModel", result.get(i).get("SJMC"));
                map.put("status", result.get(i).get("STATUS"));
                if(!result.get(i).get("CATEGORY_SEQ").toString().contains(".")){
                    map.put("state", "closed");
                }
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
        commonQueryRepository.executeUpdate("update T_SYS_ARTICLEMANAGE t set t.status = '-1' where t.id = '"+id+"'");
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
        if(StringUtils.isNotEmpty(articleTypeForm.getId())){
            sql+=" and t.id !='"+articleTypeForm.getId()+"'";
        }
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }
    public boolean isArticleContentExist(ArticleContentForm articleContentForm) throws Exception {
        String sql = " select t.* from T_ARTICLE t where 1=1 and t.title='" + articleContentForm.getTitle() + "'";
        List<Map<String, Object>> result = commonQueryRepository.findResultBySqlQuery(sql);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    public ApiPageResponse getArticleContent(ArticleContentForm articleContentForm,int pageSize,int pageNumber) throws Exception {
        StringBuilder sb =new StringBuilder("select t.*,a.category_name from T_ARTICLE t  left join T_SYS_ARTICLEMANAGE a on t.article_type_id =a.id where 1=1 ");
        if(StringUtils.isNotEmpty(articleContentForm.getTitle())){
            sb.append(" and t.TITLE like '%"+articleContentForm.getTitle()+"%'");
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
            sb.append(" and (a.category_seq = '"+articleContentForm.getArticleTypeId()+"' or a.category_seq like '"+articleContentForm.getArticleTypeId()+".%' or a.category_seq like '%."+articleContentForm.getArticleTypeId()+"' )");
        }
        sb.append(" order by t.is_top desc,");
        if(StringUtils.isNotEmpty(articleContentForm.getSort())){
            if("articleTag".equals(articleContentForm.getSort())){
                sb.append(" t.article_Tag ");
            }else if("title".equals(articleContentForm.getSort())){
                sb.append(" t.title ");
            }else if("categoryName".equals(articleContentForm.getSort())){
                sb.append(" a.category_name ");
            }else if("publishTime".equals(articleContentForm.getSort())){
                sb.append(" t.publish_Time ");
            }else if("publishDeptName".equals(articleContentForm.getSort())){
                sb.append(" t.publish_Dept_Name ");
            }else if("createUserName".equals(articleContentForm.getSort())){
                sb.append(" t.create_User_Name ");
            }else if("status".equals(articleContentForm.getSort())){
                sb.append(" t.status ");
            }
            if(StringUtils.isNotEmpty(articleContentForm.getOrder())){
                sb.append(articleContentForm.getOrder()+",");
            }else{
                sb.append(",");
            }
        }
        sb.append("  t.publish_time desc ,t.article_type_id,t.article_tag,t.create_user_id,t.title,t.status");
        ApiPageResponse response = commonQueryRepository.findPageBySqlQuery(pageSize,pageNumber,sb.toString());
//        List<Map<String, Object>> rows = response.getRows();
//        for(Map<String, Object> map:rows){
//            if(map.get("articleTypeId")!=null){
//                map.put("articleTypeSeqName",getCategorySeqNameListById(map.get("articleTypeId").toString()));
//            }else{
//                map.put("articleTypeSeqName",map.get("categoryName"));
//            }
//        }
        return response;
    }
    /**
     * 通过ID查询下级
     * @param
     * @return
     */
    public List<TArticleManage> findChildArticleById( int id) {
        Specification specification=new Specification<TArticleManage>() {
            @Override
            public Predicate toPredicate(Root<TArticleManage> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                int rootId=id;
                predicates.add(criteriaBuilder.equal(root.get("id"), rootId ));
                predicates.add(criteriaBuilder.equal(root.get("status"), "1"));
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("articleorder")));
                return criteriaQuery.getRestriction();
            }
        };
        return (List<TArticleManage>) articleManageRepository.findAll(specification);
    }

    public List<Map<String, Object>>  findSysSjzdList()throws Exception {
        String sql="select t.sjbm ,t.sjmc from T_SYS_SJZD t where t.sjlx='查看模式' ";
        List<Map<String, Object>> resultList=commonQueryRepository.findResultBySqlQuery(sql);
        List list =new ArrayList();
        for(int i=0;i<resultList.size();i++){
            Map<String,Object> map =new HashMap<String, Object>();
            map.put("id",resultList.get(i).get("sjbm"));
            map.put("text",resultList.get(i).get("sjmc"));
            list.add(map);
        }
        return list;
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
    public String getCategorySeqNameListById(String id) {
        String result = "";
        TArticleManage articleManage = findArticle(id);
        if (StringUtils.isNotEmpty(articleManage.getCategoryseq())) {
            String[] ids = articleManage.getCategoryseq().split("\\.");
            for (String s : ids) {
                articleManage = findArticle(s);
                result+=articleManage.getCategoryname()+".";
            }
        }
        if(result.length()>0){
            result = result.substring(0,result.length()-1);
        }
      return result;
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

    public void upTop(String id, String value) {
        commonQueryRepository.executeUpdate("update T_ARTICLE t set t.is_top='"+value+"' where t.id ='"+id+"' ");
    }
}
