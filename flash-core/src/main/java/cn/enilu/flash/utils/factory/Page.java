package cn.enilu.flash.utils.factory;

import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.utils.Lists;
import cn.enilu.flash.utils.StringUtil;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

/**
 * Created  on 2018/3/22 0022.
 *
 * @author enilu
 */
public class Page<T> {

    private Sort sort;
    /**
     * 该操作只是为了忽略RowBounds属性
     */
    private transient int offset;
    /**
     * 该操作只是为了忽略RowBounds属性
     */
    private transient int limit;

    /**
     * 总数
     */
    private int total;

    /**
     * 每页显示条数，默认 10
     */
    private int pageSize = 10;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 当前页
     */
    private int pageNum = 1;

    /**
     * 查询总记录数（默认 true）
     */
    private transient boolean searchCount = true;

    /**
     * 查询数据列表
     */
    private List<T> list = Collections.emptyList();

    private transient List<SearchFilter> filters;

    public Page() {

    }

    public Page(int current, int pageSize, String orderByField) {
        this(current, pageSize, orderByField, true);


    }

    public Page(int current, int pageSize, String orderByField, boolean isAsc) {
        this(current, pageSize);
        setSort(Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, orderByField));

    }

    /**
     * <p>
     * 分页构造函数
     * </p>
     *
     * @param current 当前页
     * @param pageSize    每页显示条数
     */
    public Page(int current, int pageSize) {
        this(current, pageSize, true);
    }


    public Page(int pageNum, int pageSize, boolean searchCount) {

        setOffset(offsetCurrent(pageNum, pageSize));
        setLimit(pageSize);
        if (pageNum > 1) {
            this.pageNum = pageNum;
        }
        this.pageSize = pageSize;
        this.searchCount = searchCount;
    }

    protected static int offsetCurrent(int current, int pageSize) {
        if (current > 0) {
            return (current - 1) * pageSize;
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
        return offsetCurrent(this.pageNum, this.pageSize);
    }

    public boolean hasPrevious() {
        return this.pageNum > 1;
    }

    public boolean hasNext() {
        return this.pageNum < this.pages;
    }

    public int getTotal() {
        return total;
    }

    public Page setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPages() {
        if (this.pageSize == 0) {
            return 0;
        }
        this.pages = this.total / this.pageSize;
        if (this.total % this.pageSize != 0) {
            this.pages++;
        }
        return this.pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public Page setPageNum(int current) {
        this.pageNum = current;
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


    public List<T> getList() {
        return list;
    }

    public Page<T> setList(List<T> list) {
        this.list = list;
        return this;
    }


    public List<SearchFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<SearchFilter> filters) {
        this.filters = filters;
    }

    public void addFilter(SearchFilter filter) {
        if (filter == null) {
            return;
        }
        if (filters == null) {
            filters = Lists.newArrayList();
        }
        filters.add(filter);
    }

    public void addFilter(String fieldName, SearchFilter.Operator operator, Object value) {
        if (!StringUtil.isNullOrEmpty(value)) {
            addFilter(SearchFilter.build(fieldName, operator, value));
        }
    }

    public void addFilter(String fieldName, Object val) {
        addFilter(fieldName, SearchFilter.Operator.EQ, val);
    }

    @Override
    public String toString() {
        StringBuilder pg = new StringBuilder();
        pg.append(" Page:{ [").append(super.toString()).append("], ");
        if (list != null) {
            pg.append("records-size:").append(list.size());
        } else {
            pg.append("records is null");
        }
        return pg.append(" }").toString();
    }

}
