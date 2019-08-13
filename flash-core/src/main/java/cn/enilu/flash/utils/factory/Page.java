package cn.enilu.flash.utils.factory;

import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.utils.Lists;
import cn.enilu.flash.utils.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

/**
 * Created  on 2018/3/22 0022.
 *
 * @author enilu
 */
public class Page<T>  {

    private Sort sort;
    /**
     * 该操作只是为了忽略RowBounds属性
     *
     *
     */
    private transient int offset;
    /**
     * 该操作只是为了忽略RowBounds属性
     *
     *
     */
    private transient int limit;

    /**
     * 总数
     */
    private int total;

    /**
     * 每页显示条数，默认 10
     */
    private int size = 10;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 当前页
     */
    private int current = 1;

    /**
     * 查询总记录数（默认 true）
     */
    private transient boolean searchCount = true;

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();

    private transient  List<SearchFilter> filters;

    public Page() {

    }

    public Page(int current, int size, String orderByField) {
        this(current,size,orderByField,true);


    }

    public Page(int current, int size, String orderByField, boolean isAsc) {
        this(current, size);
        setSort(Sort.by(isAsc? Sort.Direction.ASC: Sort.Direction.DESC,orderByField));

    }

    /**
     * <p>
     * 分页构造函数
     * </p>
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public Page(int current, int size) {
        this(current,size,true);
    }


    public Page(int current, int size, boolean searchCount) {

        setOffset(offsetCurrent(current, size));
        setLimit(size);
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.searchCount = searchCount;
    }

    protected static int offsetCurrent(int current, int size) {
        if (current > 0) {
            return (current - 1) * size;
        }
        return 0;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int offsetCurrent() {
        return offsetCurrent(this.current, this.size);
    }

    public boolean hasPrevious() {
        return this.current > 1;
    }

    public boolean hasNext() {
        return this.current < this.pages;
    }

    public int getTotal() {
        return total;
    }

    public Page setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Page setSize(int size) {
        this.size = size;
        return this;
    }

    public int getPages() {
        if (this.size == 0) {
            return 0;
        }
        this.pages = this.total / this.size;
        if (this.total % this.size != 0) {
            this.pages++;
        }
        return this.pages;
    }

    public int getCurrent() {
        return current;
    }

    public Page setCurrent(int current) {
        this.current = current;
        return this;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public Page setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public Page setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public Page setLimit(int limit) {
        this.limit = limit;
        return this;
    }



    public List<T> getRecords() {
        return records;
    }

    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }


    public List<SearchFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<SearchFilter> filters) {
        this.filters = filters;
    }
    public void addFilter(SearchFilter filter){
        if(filter==null){
            return ;
        }
        if(filters==null){
            filters = Lists.newArrayList();
        }
        filters.add(filter);
    }
    public void addFilter(String fieldName, SearchFilter.Operator operator, Object value){
        if(!StringUtils.isNullOrEmpty(value)){
            addFilter(SearchFilter.build(fieldName,operator,value));
        }
    }
    public void addFilter(String fieldName, SearchFilter.Operator operator){
        addFilter(SearchFilter.build(fieldName,operator));
    }

    @Override
    public String toString() {
        StringBuilder pg = new StringBuilder();
        pg.append(" Page:{ [").append(super.toString()).append("], ");
        if (records != null) {
            pg.append("records-size:").append(records.size());
        } else {
            pg.append("records is null");
        }
        return pg.append(" }").toString();
    }

}
