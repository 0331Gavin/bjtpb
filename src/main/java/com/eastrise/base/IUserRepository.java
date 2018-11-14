package com.eastrise.base;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 系统的模块信息管理仓库类
 */
@Repository
public interface IUserRepository extends CrudRepository<TSysUser, String>
        , JpaSpecificationExecutor<TSysUser>, PagingAndSortingRepository<TSysUser,String> {

    @Query("select u from TSysUser u where u.id = ?1 and u.status <> '-1'")
    TSysUser findByIdAndStatus(String id);

    @Query("select u from TSysUser u where u.loginName = ?1 and u.status <> '-1'")
    TSysUser findByLoginNameAndStatus(String loginName);

    TSysUser findByLoginName(String loginName);

    TSysUser findByUserName(String userName);
    TSysUser findByIdAndStatusNot(String userId,String status);


}
