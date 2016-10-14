package com.delmar.core.web.displaytag.paging;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

import java.util.List;

/**
 * Created by admin on 2016/9/1.
 */
public class PaginatedListHelper implements PaginatedList{
    private int fullListSize; // 总记录数
    private List list; // 每页列表
    private int objectsPerPage=20;// 每页记录数 page size
    private int pageNumber = 1; // 当前页码
    private String sortCriterion;
    private SortOrderEnum sortDirection;
    public PaginatedListHelper(int pageNumber,int fullListSize,List list)
    {
        this.pageNumber=pageNumber;
        this.fullListSize=fullListSize;
        this.list=list;
    }
    @Override
    public List getList() {
        return list;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getObjectsPerPage() {
        return objectsPerPage;
    }

    @Override
    public int getFullListSize() {
        return fullListSize;
    }

    @Override
    public String getSortCriterion() {
        return sortCriterion;
    }

    @Override
    public SortOrderEnum getSortDirection() {
        return null;
    }

    @Override
    public String getSearchId() {
        return null;
    }

    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }

    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }
}
