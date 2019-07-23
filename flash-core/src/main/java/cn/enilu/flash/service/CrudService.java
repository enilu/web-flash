package cn.enilu.flash.service;

/**
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 22:31
 */
public interface CrudService <T, ID> extends
        InsertService<T, ID>,
        UpdateService<T,ID>,
        DeleteService<ID>,
        SelectService<T, ID> {
}
