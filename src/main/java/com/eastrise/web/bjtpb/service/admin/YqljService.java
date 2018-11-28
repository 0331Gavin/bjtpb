package com.eastrise.web.bjtpb.service.admin;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.SjzdAddData;
import com.eastrise.web.bjtpb.entity.TSysSjzd;
import com.eastrise.web.bjtpb.entity.TSysYqlj;
import com.eastrise.web.bjtpb.repository.ISjzdRepository;
import com.eastrise.web.bjtpb.repository.IYqljRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by gzq on 2018/11/14 14:28
 */
@Service
public class YqljService {

    @Autowired
    private CommonQueryRepository commonQueryRepository;
    @Autowired
    private IYqljRepository yqljRepository;
    /**
     * 系统管理/友情链接管理列表
     */
    public ApiPageResponse findPageData(int pageSize, int pageNumber, String ljmc) {
        StringBuilder sql = new StringBuilder("select t.* from t_sys_yqlj t where 1=1");
        if(Strings.isNotEmpty(ljmc)) {
            ljmc=ljmc.trim();
            sql.append(" and t.ljmc like '%"+ljmc+"%'");
        }
        sql.append("order by t.ljsx");
        return commonQueryRepository.findPageBySqlQuery(pageSize,pageNumber,sql.toString());
    }

    /**
     * 友情链接修改or保存
     * @param tSysYqlj
     * @return
     * @throws Exception
     */
    public TSysYqlj save(TSysYqlj tSysYqlj) throws Exception {
        return yqljRepository.save(tSysYqlj);
    }
    public List<Map<String, Object>> findById(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from t_sys_yqlj t where t.id='"+id+"'");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }
    public List<Map<String, Object>> findAll() throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from t_sys_yqlj t ");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }

    public void del(String id) throws Exception{
        yqljRepository.deleteById(id);
    }
}
