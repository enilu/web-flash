package cn.enilu.flash.service;

/**
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 22:30
 */
public interface UpdateService <T, ID> {
    /**
     * 修改记录信息
     *
     * @param record 要修改的对象
     * @return 返回修改的记录
     */
    T update(T record);
    /**
     * 添加或修改记录
     * @param record 要添加或修改的对象
     * @return 返回添加或修改的记录
     */
    T saveOrUpdate(T record);
}
