package cn.enilu.flash.service;

import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.utils.factory.Page;

import java.util.List;

/**
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 22:30
 */
public interface SelectService <T, ID> {

    /**
     * 根据主键查询
     * @param id 主键
     * @return 查询结果,无结果时返回{@code null}
     */
    T get(ID id);

    /**
     * 根据多个主键查询
     * @param ids 主键集合
     * @return 查询结果,如果无结果返回空集合
     */
    List<T> query(Iterable<ID> ids);

    /**
     * 查询所有结果
     * @return 所有结果,如果无结果则返回空集合
     */
    List<T> queryAll();

    /**
     * 查询所有结果
     * @return 获取分页结果
     */
    Page<T> queryPage(Page<T> page);

    /**
     * 根据条件里欸报查询列表数据
     * @param filters
     * @return
     */
    List<T> queryAll(List<SearchFilter> filters);

    /**
     * 根据的那个条件查询列表数据
     * @param filter
     * @return
     */
    List<T> queryAll(SearchFilter filter);
}
