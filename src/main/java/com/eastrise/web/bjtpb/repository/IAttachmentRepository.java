package com.eastrise.web.bjtpb.repository;

import com.eastrise.web.bjtpb.entity.TAttachment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IAttachmentRepository extends CrudRepository<TAttachment, String> {
    @Query("select u from TAttachment u where u.id = ?1 ")
    TAttachment findByIdAndStatus(String id);

    @Query("select u from TAttachment u where u.buzId = ?1 ")
    TAttachment findByBuzId(String id);
}
