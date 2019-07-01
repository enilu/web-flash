package cn.enilu.flash.service;

/**
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 22:28
 */
public interface InsertService<T, ID> {

    /**
     * 添加一条数据
     *
     * @param record 要添加的数据
     * @return 添加后生成的主键
     */
    T insert(T record);
}
