package com.ea.core.dao.impl;

import com.ea.core.dao.WslogDao;
import com.ea.core.model.Wslog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/9/8.
 */
@Slf4j
public class WslogDaoForLog implements WslogDao{

    @Override
    public void insertWslog(Wslog wslog) {
      log.info(wslog.toString());
    }
}
