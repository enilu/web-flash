package cn.enilu.flash.dao;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * 基础dao实现类
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 12:53
 */
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {
    private final EntityManager entityManager;
    private Class<T> klass;


    BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
                       EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.klass = (Class<T>) entityInformation.getJavaType();
    }

    @Override
    public List<Object[]> queryBySql(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public Object getBySql(String sql) {
        List list = entityManager.createNativeQuery(sql).getResultList();
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    @Override
    public T getOne(ID id){
        return findById(id).get();
    }
    @Override
    public T get(String sql) {
        List<T> list =  entityManager.createNativeQuery(sql,klass).getResultList();
        return list.get(0);
    }

    @Override
    public int execute(String sql) {
        return entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Override
    public Class<T> getDataClass() {
        return klass;
    }

    @Override
    public List<T> query(String sql) {
        return entityManager.createNativeQuery(sql,klass).getResultList();
    }
}
