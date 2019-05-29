package cn.enilu.flash.dao.system;

import cn.enilu.flash.bean.entity.system.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FileInfoRepository  extends PagingAndSortingRepository<FileInfo,Long>
        , JpaRepository<FileInfo,Long>, JpaSpecificationExecutor<FileInfo> {
}
