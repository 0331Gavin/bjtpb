package com.eastrise.web.bjtpb.repository;

import com.eastrise.web.bjtpb.entity.TSysSjzd;
import com.eastrise.web.bjtpb.entity.TSysYqlj;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * create by LH on 2018/11/14 13:08
 */
public interface IYqljRepository extends CrudRepository<TSysYqlj, String>, PagingAndSortingRepository<TSysYqlj, String>,JpaSpecificationExecutor<TSysYqlj> {

}
