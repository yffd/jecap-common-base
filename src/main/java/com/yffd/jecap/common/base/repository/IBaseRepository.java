package com.yffd.jecap.common.base.repository;

import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.page.PageData;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface IBaseRepository<E extends IBaseEntity> {

    int add(E entity);

    int modify(E oldEntity, E newEntity);

    int modifyById(E entity);

    int remove(E entity);

    int removeById(Serializable id);

    int removeByIds(Set<? extends Serializable> ids);

    E get(E entity);

    E getById(Serializable id);

    List<E> getByIds(Set<? extends Serializable> ids);

    List<E> getList(E entity);

    List<E> getAll();

    PageData<E> getPage(E entity, int pageNum, int pageSize);

}
