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
    /**
     * 根据原生sql语句查询数据列表
     * @param sql
     * @return
     */
    List<Object[]> queryBySql(String sql);

    /**
     * 根据原生sql查询对象列表
     * @param sql
     * @return
     */
    List<T> query(String sql);

    /**
     * 根据原生sql查询数组对象
     * @param sql
     * @return
     */
    Object getBySql(String sql);

    /**
     * 根据原生sql查询对象
     * @param sql
     * @return
     */
    T get(String sql);
    T getOne(ID id);

    /**
     * 执行sql
     * @param sql
     * @return
     */
    int execute(String sql);

    /**
     * 获取数据类型
     * @return
     */
    Class<T> getDataClass();
}
