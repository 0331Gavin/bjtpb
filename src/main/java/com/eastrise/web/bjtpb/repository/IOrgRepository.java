package com.eastrise.web.bjtpb.repository;

import com.eastrise.web.bjtpb.entity.TSysOrg;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * create by gzq on 2018/11/14 13:08
 */
public interface IOrgRepository extends CrudRepository<TSysOrg, Long>, PagingAndSortingRepository<TSysOrg, Long>,JpaSpecificationExecutor<TSysOrg> {
    @Query("select u from TSysOrg u where u.id = ?1 and u.status <> '-1'")
    TSysOrg findByIdAndStatus(Long id);
}
