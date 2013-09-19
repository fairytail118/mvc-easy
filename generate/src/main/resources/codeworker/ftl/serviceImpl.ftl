/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package ${serviceimpl_package};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${dao_package}.${entity}Dao;
import ${entity_package}.${entity};
import ${service_package}.${entity}Service;
import com.easy.core.common.Page;

/**
 * @author ${author}
 * @version v 0.1 ${date}
 */
@Service
public class ${entity}ServiceImpl implements ${entity}Service {

    @Resource
    private ${entity}Dao ${lower_entity}Dao;

    @Override
    public ${entity} save(${entity} ${lower_entity}) {
        if (${lower_entity}.getId() == null) {
            ${lower_entity}Dao.create(${lower_entity});
        } else {
            ${lower_entity}Dao.update(${lower_entity});
        }
        return ${lower_entity};
    }

    
    @Override
    public void deleteByPrimaryKeys(Long[] keys) {
        ${lower_entity}Dao.deleteByPrimaryKeys(keys);
    }

    
    @Override
    public ${entity} getByPrimaryKey(Long id) {

        return ${lower_entity}Dao.getByPrimaryKey(id);
    }

    
    @Override
    public void page(Page<${entity}> page) {
        ${lower_entity}Dao.page(page);
    }

}
