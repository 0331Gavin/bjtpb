package com.eastrise.web.bjtpb.repository;

import com.eastrise.web.bjtpb.entity.TSysOrg;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * create by gzq on 2018/11/14 13:08
 */
public interface IOrgRepository extends CrudRepository<TSysOrg, Long>, PagingAndSortingRepository<TSysOrg, Long>,JpaSpecificationExecutor<TSysOrg> {
}
