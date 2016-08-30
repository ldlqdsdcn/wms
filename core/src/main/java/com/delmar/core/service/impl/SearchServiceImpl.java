/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import com.delmar.core.def.SearchDataTypeDef;
import com.delmar.core.dto.SearchColumnDto;
import com.delmar.core.model.CommonSearchParam;
import com.delmar.core.model.CommonSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.SearchDao;
import com.delmar.core.model.Search;
import com.delmar.core.service.SearchService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.delmar.core.model.SearchColumn;
import com.delmar.core.dao.SearchColumnDao;

/**
 * @author 刘大磊 2016-08-29 16:03:22
 */
@Service("searchService")
public class SearchServiceImpl extends CoreServiceImpl<Search> implements
        SearchService {
    SearchDataTypeDef[] searchDataTypeDefs = SearchDataTypeDef.values();
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private SearchColumnDao searchColumnDao;

    /* (non-Javadoc)
     * @see CoreService.CoreServiceImpl#getCoreDao()
     */
    protected CoreDao<Search> getCoreDao() {
        return searchDao;
    }

    public void deleteSearchList(Integer[] ids) {
        if (ids != null)
            for (Integer id : ids) {
                deleteByPrimaryKey(id);
            }
    }


    public Integer saveSearch(Search search, List<SearchColumn> searchColumnList) {
        Integer id = save(search);
        for (SearchColumn searchColumn : searchColumnList) {
            searchColumn.setSearchId(id);
            searchColumnDao.save(searchColumn);
        }
        return id;
    }

    @Override
    public List<CommonSearchResult> selectCommonList(CommonSearchParam commonSearchParam) {
        return searchDao.selectCommonList(commonSearchParam);
    }

    @Override
    public List<CommonSearchResult> getCommonSearchListByColumnId(Integer columnId) {
        SearchColumn searchColumn = searchColumnDao.selectByPrimaryKey(columnId);
        CommonSearchParam param = new CommonSearchParam();
        param.setKeyValue(searchColumn.getFkKeyColumn());
        param.setLabelValue(searchColumn.getFkLabelColumn());
        param.setSearchString(searchColumn.getCoditions());
        param.setTableName(searchColumn.getFkTable());
        return searchDao.selectCommonList(param);
    }

    @Override
    public Search getSearchByPageUrl(String pageUrl) {
        Map<String, Object> param = new HashMap<>();
        param.put("pageUrl", pageUrl);
        List<Search> list = searchDao.selectByExample(param);
        if (list == null || list.size() == 0)
            return null;
        else {
            return list.get(0);
        }
    }

    @Override
    public String buildSqlBySearchColumnList(List<SearchColumnDto> searchColumnDtoList) {
        StringBuilder sqlBuilder = new StringBuilder();
        int i = 0;
        for (SearchColumnDto searchColumnDto : searchColumnDtoList) {
            if (i != 0) {
                sqlBuilder.append(" and ");
            }
            sqlBuilder.append(buildOneCondition(searchColumnDto));
            i++;
        }
        return sqlBuilder.toString();
    }

    private String buildOneCondition(SearchColumnDto searchColumnDto) {
        StringBuilder sqlBuilder = new StringBuilder();
        SearchColumn searchColumn = searchColumnDao.selectByPrimaryKey(searchColumnDto.getColumnId());
        int dataTypeIndex = searchColumn.getDataType() - 1;

        switch (searchDataTypeDefs[dataTypeIndex]) {
            case CHAR:
                sqlBuilder.append(searchColumn.getColumnName());
                sqlBuilder.append(" ").append(searchColumnDto.getOpearType()).append(" ");
                sqlBuilder.append("'%").append(searchColumnDto.getValue().trim()).append("%'");
                break;
            case FLOAT:
            case NUMBER:
                sqlBuilder.append(searchColumn.getColumnName());
                sqlBuilder.append(" ").append(searchColumnDto.getOpearType()).append(" ");
                sqlBuilder.append("").append(searchColumnDto.getValue().trim()).append("");
                break;
            case DATE:
                sqlBuilder.append(String.format("date_format(%s,'%Y-%m-%d')='%s'", searchColumn.getColumnName(), searchColumnDto.getValue()));
                sqlBuilder.append(" ").append(searchColumnDto.getOpearType()).append(" ");

                sqlBuilder.append("'").append(searchColumnDto.getValue().trim()).append("'");

                break;
            case DATETIME:
                sqlBuilder.append(String.format("date_format(%s,'%Y-%m-%d %H:%s:%i')='%s'", searchColumn.getColumnName(), searchColumnDto.getValue()));
                sqlBuilder.append(" ").append(searchColumnDto.getOpearType()).append(" ");
                sqlBuilder.append("'").append(searchColumnDto.getValue().trim()).append("'");
                break;
        }
        return sqlBuilder.toString();
    }

    public Integer deleteByPrimaryKey(Integer id) {
        Map<String, Object> searchColumnParam = new HashMap<String, Object>();
        searchColumnParam.put("searchId", id);
        searchColumnDao.deleteByExample(searchColumnParam);
        return super.deleteByPrimaryKey(id);
    }

    public List<SearchColumn> getSearchColumnListBySearchId(Integer searchId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("searchId", searchId);
        return searchColumnDao.selectByExample(param);
    }
}
