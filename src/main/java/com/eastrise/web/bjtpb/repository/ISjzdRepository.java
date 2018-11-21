package com.eastrise.web.bjtpb.repository;

import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.web.bjtpb.entity.TSysSjzd;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * create by gzq on 2018/11/14 13:08
 */
public interface ISjzdRepository extends CrudRepository<TSysSjzd, String>, PagingAndSortingRepository<TSysSjzd, String>,JpaSpecificationExecutor<TSysSjzd> {
    @Query("select u from TSysSjzd u where u.id = ?1 and u.status <> '-1'")
    TSysSjzd findByIdAndStatus(String id);
}
