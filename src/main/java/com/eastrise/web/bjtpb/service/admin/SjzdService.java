package com.eastrise.web.bjtpb.service.admin;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.OrgAddData;
import com.eastrise.web.bjtpb.controller.admin.form.SjzdAddData;
import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.web.bjtpb.entity.TSysSjzd;
import com.eastrise.web.bjtpb.repository.IOrgRepository;
import com.eastrise.web.bjtpb.repository.ISjzdRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * create by gzq on 2018/11/14 14:28
 */
@Service
public class SjzdService {
    @Autowired
    private ISjzdRepository sjzdRepository;

    @Autowired
    private CommonQueryRepository commonQueryRepository;

    /**
     * 系统管理/部门管理列表
     */
    public ApiPageResponse findPageData(int pageSize, int pageNumber, String sjbm, String sjmc,String sjlx) {


        StringBuilder sql = new StringBuilder("select t.* from t_sys_sjzd t where 1=1");
        if(Strings.isNotEmpty(sjbm)) {
            sjbm=sjbm.trim();
            sql.append(" and t.sjbm like '%"+sjbm+"%'");
        }
        if(Strings.isNotEmpty(sjmc)){
            sjmc=sjmc.trim();
            sql.append(" and t.sjmc like '%"+sjmc+"%'");
        }
        if(Strings.isNotEmpty(sjlx)){
            sql.append(" and t.sjlx = '"+sjlx+"'");
        }
        sql.append("order by t.xssx");
        return commonQueryRepository.findPageBySqlQuery(pageSize,pageNumber,sql.toString());
    }

    /**
     * 根据id获取数据字典详细信息
     * @param id
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> findById(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from t_sys_sjzd t where t.id='"+id+"'");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }
    public TSysSjzd save(TSysSjzd tSysSjzd) throws Exception {
        return sjzdRepository.save(tSysSjzd);
    }
    public void del(String id) throws Exception{
        sjzdRepository.deleteById(id);
    }
    public List<Map<String, Object>> findsjlx() throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct t.sjlx from t_sys_sjzd t");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }
    public List<Map<String, Object>> findsstj() throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct t.* from t_sys_sjzd t where t.sjmc='信息搜索' and t.status='1' order by t.xssx " );
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }
    public boolean isExist(SjzdAddData sjzdAddData) {
        StringBuilder sql = new StringBuilder("select t.sjbm from t_sys_sjzd t where t.sjbm = '"+sjzdAddData.getSjbm()+"'");

        int i = commonQueryRepository.findCountBySqlQuery(sql.toString());
        return i>0?true:false;
    }
}
