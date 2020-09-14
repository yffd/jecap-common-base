package com.yffd.jecap.common.base.dao;

import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.page.PageData;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface IBaseDao<E extends IBaseEntity> {

    int addBy(E entity);

    int editBy(E oldEntity, E newEntity);

    int editById(E entity);

    int removeBy(E entity);

    int removeById(Serializable id);

    int removeByIds(Set<? extends Serializable> ids);

    boolean existBy(E entity);

    E findById(Serializable id);

    List<E> findByIds(Set<? extends Serializable> ids);

    E findOne(E entity);

    List<E> findList(E entity);

    List<E> findAll();

    PageData<E> findPage(E entity, int pageNum, int pageSize);


}
