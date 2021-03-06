package com.eastrise.web.bjtpb.service.admin;
import com.eastrise.base.TSysUser;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.OrgAddData;
import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.web.bjtpb.repository.IOrgRepository;
import org.apache.commons.lang.StringUtils;
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
public class OrgService {

    @Autowired
    private IOrgRepository orgRepository;
    @Autowired
    private CommonQueryRepository commonQueryRepository;
    /**
     * 通过ID查询下级组织列表
     * @param orgId 组织ID
     * @return 组织对象集合
     */
    public List<TSysOrg> findChildOrgById(long orgId,boolean isAll) {
        System.out.println(orgId);
        Specification specification=new Specification<TSysOrg>() {
            @Override
            public Predicate toPredicate(Root<TSysOrg> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(orgId>0){
                    if(isAll){
                        TSysOrg tmpOrg=findOrgById(orgId);
                        predicates.add(criteriaBuilder.like(root.get("orgSeq"), tmpOrg.getOrgSeq()+".%" ));
                    }else{
                        predicates.add(criteriaBuilder.equal(root.get("parentId"), orgId ));
                    }
                }else{
                    Long rootId=Long.parseLong("1");
                    if(isAll){
                        TSysOrg tmpOrg=findOrgById(rootId);
                        predicates.add(criteriaBuilder.like(root.get("orgSeq"), tmpOrg.getOrgSeq()+"%" ));
                    }else{
                        predicates.add(criteriaBuilder.equal(root.get("id"), rootId ));
                    }
                }
                predicates.add(criteriaBuilder.equal(root.get("status"), "1"));
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("orgOrder")));
                return criteriaQuery.getRestriction();
            }
        };

        return (List<TSysOrg>) orgRepository.findAll(specification);
    }

    /**
     * 根据ID查询组织信息
     * @param orgId 组织ID
     * @return 组织对象
     */
    public TSysOrg findOrgById(Long orgId) {
        System.out.println(orgId);
        Optional<TSysOrg> record = orgRepository.findOne(new Specification<TSysOrg>() {
            @Override
            public Predicate toPredicate(Root<TSysOrg> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("id"), orgId));
                predicates.add(criteriaBuilder.equal(root.get("status"), "1"));
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        });
        return record.get();
    }
    /**
     * 系统管理/部门管理列表
     */
    public ApiPageResponse findPageData(int pageSize, int pageNumber, String orgName, String sjorgname) {


        StringBuilder sql = new StringBuilder("select dept.* from (select   g.*,bm.sjorgname   from   t_sys_org g  left      join   (select t.org_name sjorgname,t.id from t_sys_org t)bm    on   bm.id=g.parent_id )dept where 1=1 ");
        if(Strings.isNotEmpty(orgName)) {
            sql.append(" and dept.org_name like '%"+orgName+"%'");
        }
        if(Strings.isNotEmpty(sjorgname)){
            long pardeptid =Long.valueOf(sjorgname);
            sql.append(" and dept.parent_id = '"+pardeptid+"'");
        }
        sql.append(" and dept.status>'-1' ");
        sql.append("order by dept.org_order");
        return commonQueryRepository.findPageBySqlQuery(pageSize,pageNumber,sql.toString());
    }

    /**
     * 根据id查询部门详细信息（包括父id对应的部门名称）
     * @param id
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> findById(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select dept.* from (select g.*,bm.sjorgname  from t_sys_org g left join  (select t.org_name sjorgname,t.id from t_sys_org t)bm    on   bm.id=g.parent_id )dept where dept.status='1' and dept.id='"+id+"'");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }

    /**
     * 根据id查询org_seq
     * @param id
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> findParentorgseqbyID(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.org_seq  from T_SYS_ORG  t where t.id='"+id+"'");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }

    /**
     * 根据seq查询其部门和子部门的id
     * @param id
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> findallOrgid(String id) throws Exception {
        //定义 用于查询的SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id  from T_SYS_ORG  t where ( t.org_seq like'"+""+id+".%"+"' or t.id='"+id+"')");
        return commonQueryRepository.findResultBySqlQuery(sql+"");
    }

    /**
     * 检查部门名称是否存在（弃用）
     * @param orgAddData
     * @return
     */
    public boolean checkOrgIsExist(OrgAddData orgAddData) {
        StringBuilder sql = new StringBuilder("select t.id from T_SYS_ORG t where t.ORG_NAME = '"+orgAddData.getOrgName()+"'");
        int i = commonQueryRepository.findCountBySqlQuery(sql.toString());
        return i>0?true:false;
    }

    /**
     * 根据部门id查询部门
     * @param orgId
     * @return
     */
    public TSysOrg findOrgInfo(Long orgId){
        TSysOrg tSysOrg = orgRepository.findByIdAndStatus(orgId);
        return tSysOrg;
    }
    /**
     * 新增部门
     * @param tSysOrg
     * @return
     */
    public TSysOrg save(TSysOrg tSysOrg) throws Exception {
        return orgRepository.save(tSysOrg);
    }

    /**
     * 根据id更新部门的orgseq
     * @param id
     * @param seq
     */
    public void updateSeq(Long id,String seq) {
        StringBuilder sql = new StringBuilder("update T_SYS_ORG  set org_seq='"+seq+"' where  id='"+id+"'");
        commonQueryRepository.executeUpdate(sql.toString());

    }

    /**
     * 根据id删除该部门以及子部门
     * @param id
     * @throws Exception
     */
    public void del(String id) throws Exception{
        StringBuilder sql = new StringBuilder("delete from T_SYS_ORG  where ( id = '"+id+"' or org_seq like '"+id+"%')");
        commonQueryRepository.executeUpdate(sql.toString());
    }
    /**
     * 根据部门id判断有没有用户
     * @param deptid
     * @return
     */
    public boolean findCountyh(String deptid) {
        StringBuilder sql = new StringBuilder("select t.id from T_SYS_USER t where t.dept_id = '"+deptid+"'");

        int i = commonQueryRepository.findCountBySqlQuery(sql.toString());
        return i>0?true:false;
    }
}
