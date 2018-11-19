package com.eastrise.web.bjtpb.repository;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleManageRepository extends CrudRepository<TArticleManage, Integer>, PagingAndSortingRepository<TArticleManage, Integer>,JpaSpecificationExecutor<TArticleManage> {

    @Query("select u from TArticleManage u where u.id = ?1 and u.status <> '-1'")
    TArticleManage findByIdAndStatus(int id);
}
