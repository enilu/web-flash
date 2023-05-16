package cn.enilu.flash.dao;

import cn.enilu.flash.utils.JsonUtil;
import cn.enilu.flash.utils.Lists;
import cn.enilu.flash.utils.Maps;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<T> queryBySql(String sql) {
        List list = entityManager.createNativeQuery(sql, klass).getResultList();
        return list ;
    }

    @Override
    public List<?> queryObjBySql(String sql, Class<?> klass) {
        List<Map> list = queryMapBySql(sql);
        if(list.isEmpty()){
            return Lists.newArrayList();
        }
        List result = Lists.newArrayList();
        for(Map map:list){
            try {
                Object bean = Maps.mapToObj(map,klass);
                result.add(bean);
            } catch (Exception e) {
            }
        }
        return result ;

    }

    @Override
    public List<Map> queryMapBySql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        return list;
    }

    @Override
    public Map getMapBySql(String sql) {
        List<Map> list = queryMapBySql(sql);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public T getOne(ID id) {
        Optional<T> optional = findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public T get(String sql) {
        List<T> list = entityManager.createNativeQuery(sql, klass).getResultList();
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
    public int truncate() {
        String query = new StringBuilder("TRUNCATE TABLE ")
                .append(getTableName())
                .toString();
        return entityManager.createNativeQuery(query).executeUpdate();
    }

    /**
     * 根据entityType获取表名称
     * @return
     */
    private String getTableName() {
        Metamodel meta = entityManager.getMetamodel();
        EntityType<T> entityType = (EntityType<T>) meta.entity(klass);
        Entity t = klass.getAnnotation(Entity.class);
        String tableName = (t == null)
                ? entityType.getName().toUpperCase()
                : t.name();
        return tableName;
    }

    @Override
    public List<T> query(String sql) {
        return entityManager.createNativeQuery(sql, klass).getResultList();
    }
}
