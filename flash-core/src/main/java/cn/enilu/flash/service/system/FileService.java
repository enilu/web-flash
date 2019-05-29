package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.FileInfo;
import cn.enilu.flash.bean.enumeration.ConfigKeyEnum;
import cn.enilu.flash.dao.cache.ConfigCache;
import cn.enilu.flash.dao.cache.TokenCache;
import cn.enilu.flash.dao.system.FileInfoRepository;
import cn.enilu.flash.utils.StringUtils;
import cn.enilu.flash.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.*;

@Service
public class FileService {
    @Autowired
    private ConfigCache configCache;
    @Autowired
    private FileInfoRepository fileInfoRepository;
    @Autowired
    private TokenCache tokenCache;
    public FileInfo save(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString();
        String realFileName =   uuid +"."+ multipartFile.getOriginalFilename().split("\\.")[1];
        try {

            File file = new File(configCache.get(ConfigKeyEnum.SYSTEM_FILE_UPLOAD_PATH.getValue()) + File.separator+realFileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setCreateTime(new Date());
            fileInfo.setCreateBy(tokenCache.getIdUser());
            fileInfo.setOriginalFileName(multipartFile.getOriginalFilename());
            fileInfo.setRealFileName(realFileName);
            fileInfoRepository.save(fileInfo);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
             return null;
        }
    }

    public FileInfo get(Long id){
        FileInfo fileInfo = fileInfoRepository.getOne(id);
        fileInfo.setAblatePath(configCache.get(ConfigKeyEnum.SYSTEM_FILE_UPLOAD_PATH.getValue()) + File.separator+fileInfo.getRealFileName());
        return fileInfo;
    }

    public Page<FileInfo> findPage(Page<FileInfo> page, HashMap<String, String> params) {
        Pageable pageable  = new PageRequest(page.getCurrent() - 1, page.getSize(), Sort.Direction.DESC,"id");
        org.springframework.data.domain.Page<FileInfo> pageResult = fileInfoRepository.findAll(new Specification<FileInfo>() {
            @Override
            public Predicate toPredicate(Root<FileInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(params.get("originalFileName"))) {
                    list.add(criteriaBuilder.like(root.get("originalFileName").as(String.class), "%" + params.get("originalFileName") + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        page.setTotal(Integer.valueOf(pageResult.getTotalElements() + ""));
        page.setRecords(pageResult.getContent());
        return page;
    }
}
