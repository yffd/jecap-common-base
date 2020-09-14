package com.yffd.jecap.common.base.service;

import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.page.PageData;
import com.yffd.jecap.common.base.result.RtnResult;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface IBaseService<E extends IBaseEntity> {

    /** 添加 */
    int addBy(E entity);
    /** 添加-批量 */
    int addBatch(List<E> entityList);

    /** 修改 */
    int editBy(E oldEntity, E newEntity);
    /** 修改 */
    int editById(E entity);

    /** 删除 */
    int removeBy(E entity);
    /** 删除 */
    int removeById(Serializable id);
    /** 删除-批量 */
    int removeByIds(Set<Serializable> ids);

    /** 存在 */
    boolean existBy(E entity);
    /** 存在 */
    boolean existById(Serializable id);

    /** 获取-单个 */
    E getById(Serializable id);
    /** 获取-单个 */
    List<E> getByIds(Set<? extends Serializable> ids);
    /** 获取-单个 */
    E getBy(E entity);
    /** 获取-列表 */
    List<E> getList(E entity);
    /** 获取-列表 */
    List<E> getAll();
    /** 获取-范围 */
    PageData<E> getRange(E entity, int pageNum, int pageSize);
    /** 获取-分页 */
    RtnResult<E> getPage(E entity, int pageNum, int pageSize);

}
