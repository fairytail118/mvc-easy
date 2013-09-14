/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package ${dao_package};

import java.util.List;

import ${entity_package}.${entity};
import com.easy.core.common.Page;

/**
 * @author 
 * @version v 0.1 ${date}
 */
public interface ${entity}Dao {

    /**
     * 保存管理员
     * 
     * @param ${lower_entity}
     * @return 返回主键
     */
    public long create(${entity} ${lower_entity});

    /**
     * 更新管理员
     * 
     * @param ${lower_entity}
     * @param 返回更新数量
     */
    public int update(${entity} ${lower_entity});

    /**
     * 根据主键删除
     * 
     * @param ids
     * @return
     */
    public int deleteByPrimaryKeys(Long... ids);

    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    public ${entity} getByPrimaryKey(Long id);

    /**
     * 条件查询列表
     * 
     * @param ${lower_entity}
     * @return
     */
    public List<${entity}> selectByCriteria(${entity} ${lower_entity});

    /**
     * 条件查询数量
     * 
     * @param ${lower_entity}
     * @return
     */
    public int countByCriteria(${entity} ${lower_entity});

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<${entity}> page);

}
