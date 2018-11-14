package com.eastrise.web.bjtpb.service.admin;

import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.web.bjtpb.repository.IOrgRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * create by gzq on 2018/11/14 14:28
 */
@Service
public class OrgService {

    @Autowired
    private IOrgRepository orgRepository;

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
                        predicates.add(criteriaBuilder.like(root.get("org_seq"), tmpOrg.getOrgSeq()+".%" ));
                    }else{
                        predicates.add(criteriaBuilder.equal(root.get("parent_id"), orgId ));
                    }
                }else{
                    Long rootId=Long.parseLong("1");
                    if(isAll){
                        TSysOrg tmpOrg=findOrgById(rootId);
                        predicates.add(criteriaBuilder.like(root.get("org_seq"), tmpOrg.getOrgSeq()+"%" ));
                    }else{
                        predicates.add(criteriaBuilder.equal(root.get("id"), rootId ));
                    }
                }
                predicates.add(criteriaBuilder.equal(root.get("status"), "1"));
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
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


}
