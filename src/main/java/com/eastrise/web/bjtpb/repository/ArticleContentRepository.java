package com.eastrise.web.bjtpb.repository;

import com.eastrise.web.bjtpb.entity.TArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleContentRepository extends JpaRepository<TArticle, String>,CrudRepository<TArticle, String>, PagingAndSortingRepository<TArticle, String>,JpaSpecificationExecutor<TArticle> {

    @Query("select u from TArticle u where u.id = ?1 ")
    TArticle findByIdAndStatus(String id);
    List<TArticle> findAllByArticleTypeIdAndStatusOrderByPublishTimeDesc(String articleTypeId,String status);
}
