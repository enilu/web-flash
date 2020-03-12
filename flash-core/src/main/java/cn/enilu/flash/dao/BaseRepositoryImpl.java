package cn.enilu.flash.dao;

import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.utils.Lists;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.nutz.mapl.Mapl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    public List<Map> queryBySql(String sql) {
        return queryBySql(sql, Lists.newArrayList());
    }

    @Override
    public List<?> queryBySql(String sql, Class<?> klass) {
        List<Map> list = queryBySql(sql);
        if(list.isEmpty()){
            return null;
        }
        List result = Lists.newArrayList();
        for(Map map :list){
                try {
                    Object bean = Mapl.maplistToObj(map,klass);
                    result.add(bean);
                }catch (Exception e){
                }
        }
        return result;
    }

    @Override
    public Map queryBysql(String sql, List<SearchFilter> filters) {
        List<Map> list = queryBySql(sql,filters);
        if(list!=null&&!list.isEmpty()){
            list.get(0);
        }
        return null;
    }

    @Override
    public List<Map> queryBySql(String sql, List<SearchFilter> filters) {
        Query query = entityManager.createNativeQuery(sql);
        if(filters!=null&&!filters.isEmpty()){
            for(SearchFilter filter:filters){
                query.setParameter(filter.fieldName, filter.value);
            }
        }
        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        return list;
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
