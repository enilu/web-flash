package cn.enilu.flash.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * 封装基础的dao接口
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 12:50
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>
        , PagingAndSortingRepository<T, ID>
        , JpaSpecificationExecutor<T> {
    List<Object[]> queryBySql(String sql);
    List<T> query(String sql);
    Object getBySql(String sql);
    T get(String sql);
    int execute(String sql);
    Class<T> getDataClass();

}
