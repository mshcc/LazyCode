package ${servicePkg}.impl;

import ${servicePkg}.${entityName}Service;
import ${entityPkg}.${entityName};
import ${mapperPkg}.${mapperName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ${author}
* @Date ${generatorDate}
*/
@Service
public class ${entityName}ServiceImpl implements ${entityName}Service {

    @Autowired
    private ${mapperName} mapper;

    @Override
    public int insert(${entityName} record) {
        return mapper.insert(record);
    }

    @Override
    public int insertList(List<${entityName}> records) {
        return mapper.insertList(records);
    }

    @Override
    public int delete(${entityName} record) {
        return mapper.delete(record);
    }

<#if primaryKeyFieldNameInDb=="-1">
<#else>
    @Override
    public int deleteByPrimaryKey(${primaryKeyType} primaryKey) {
        return mapper.deleteByPrimaryKey(primaryKey);
    }
</#if>
    @Override
    public int deleteList(List<${entityName}> records) {
        return mapper.deleteList(records);
    }

    @Override
    public int update(${entityName} record) {
        return mapper.update(record);
    }

<#if primaryKeyFieldNameInDb=="-1">
<#else>
    @Override
    public ${entityName} selectByPrimaryKey(${primaryKeyType} primaryKey) {
        return mapper.selectByPrimaryKey(primaryKey);
    }
</#if>
    @Override
    public List<${entityName}> selectList(${entityName} record) {
        return mapper.selectList(record);
    }

}
