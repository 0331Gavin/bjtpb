package com.eastrise.web.bjtpb.service.admin;

import com.eastrise.web.bjtpb.entity.TAttachment;
import com.eastrise.web.bjtpb.repository.IAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService {
    @Autowired
    private IAttachmentRepository iAttachmentRepository;

    public TAttachment saveAttachment(TAttachment tAttachment) throws Exception
    {
        //这插入用户  部门相关信息
        return iAttachmentRepository.save(tAttachment);
    }
}
