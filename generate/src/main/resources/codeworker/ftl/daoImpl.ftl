/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package ${daoimpl_package};

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import ${dao_package}.${entity}Dao;
import ${entity_package}.${entity};
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 
 * @author ${author}
 * @version v 0.1 ${date}
 */
@Repository
public class ${entity}DaoImpl extends MyBatisGenericDao implements ${entity}Dao {

    @Override
    public long create(${entity} ${lower_entity}) {
        save("${dao_package}.${entity}Dao.create", ${lower_entity});
        return ${lower_entity}.getId();
    }

   
    @Override
    public int update(${entity} ${lower_entity}) {
        return update("${dao_package}.${entity}Dao.update", ${lower_entity});
    }

   
    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        return delete("${dao_package}.${entity}Dao.deleteByPrimaryKeys", ids);
    }

   
    @Override
    public ${entity} getByPrimaryKey(Long id) {
        return get("${dao_package}.${entity}Dao.getByPrimaryKey", id);
    }

   
    @Override
    public List<${entity}> selectByCriteria(${entity} ${lower_entity}) {
        return list("${dao_package}.${entity}Dao.selectByCriteria", ${lower_entity});
    }

   
    @Override
    public int countByCriteria(${entity} ${lower_entity}) {
        return get("${dao_package}.${entity}Dao.countByCriteria", ${lower_entity});
    }

    
    @Override
    public void page(Page<${entity}> page) {
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
        List<${entity}> list = list("${dao_package}.${entity}Dao.selectByCriteria", page.getCriteria(),
            rowBounds);
        page.setList(list);
        page.setTotalCount(countByCriteria(page.getCriteria()));
    }
}
