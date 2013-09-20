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
 
 	public ${entity}DaoImpl(){
		namespace="${dao_package}.${entity}Dao";
	}
 	
    @Override
    public long create(${entity} ${lower_entity}) {
        save("create", ${lower_entity});
        return ${lower_entity}.getId();
    }

   
    @Override
    public int update(${entity} ${lower_entity}) {
        return update("update", ${lower_entity});
    }

   
    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        return delete("deleteByPrimaryKeys", ids);
    }

   
    @Override
    public ${entity} getByPrimaryKey(Long id) {
        return get("getByPrimaryKey", id);
    }

   
    @Override
    public List<${entity}> selectByCriteria(${entity} ${lower_entity}) {
        return list("selectByCriteria", ${lower_entity});
    }

   
    @Override
    public int countByCriteria(${entity} ${lower_entity}) {
        return get("countByCriteria", ${lower_entity});
    }

    
    @Override
    public void page(Page<${entity}> page) {
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
        List<${entity}> list = list("selectByCriteria", page.getCriteria(),
            rowBounds);
        page.setList(list);
        page.setTotalCount(countByCriteria(page.getCriteria()));
    }
}
