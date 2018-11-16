package com.eastrise.web.bjtpb.service.admin;

import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.repository.ArticleManageRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eastrise.web.bjtpb.entity.TArticleManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleManageService {
    @Autowired
    private CommonQueryRepository commonQueryRepository;
    @Autowired
    private ArticleManageRepository articleManageRepository;

    public List<Map<String, Object>> getArticleGateGory()throws Exception{
        String sql="select t.* from T_SYS_ARTICLEMANAGE t where t.status <>0 order by t.article_order";
        List<Map<String, Object>> result= commonQueryRepository.findResultBySqlQuery(sql);
        List<Map<String, Object>> arrayList=new ArrayList<Map<String, Object>>();
        for(int i=0;i<result.size();i++){
            Map<String,Object> map =new HashMap<String, Object>();
            String parentId =result.get(i).get("PARENT_ID")+"";
            map.put("id",Integer.valueOf(result.get(i).get("ID")+""));
            map.put("categoryName",result.get(i).get("CATEGORY_NAME"));
            if(!parentId.equals("0")){
                map.put("_parentId",Integer.valueOf(parentId));
            }
            arrayList.add(map);
        }
        return arrayList;
    }

    public TArticleManage findArticle(String id){
        TArticleManage articleManage = articleManageRepository.findByIdAndStatus(Integer.valueOf(id));
        return articleManage;
    }

    public TArticleManage save(TArticleManage articleManage) throws Exception {
        return articleManageRepository.save(articleManage);
    }

    public void del(String id){
        articleManageRepository.deleteById(Integer.valueOf(id));
    }

    public boolean isExist(ArticleTypeForm articleTypeForm)throws Exception{
        String sql=" select t.* from T_SYS_ARTICLEMANAGE t where 1=1 and t.category_name='"+articleTypeForm.getCategoryname()+"'";
        List<Map<String, Object>> result=commonQueryRepository.findResultBySqlQuery(sql);
        if(result.size()>0){
            return false;
        }
        return true;
    }
}
