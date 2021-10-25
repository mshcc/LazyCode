<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">


<mapper namespace="${mapperPkg}.${mapperName}">
    <resultMap type="${entityPkg}.${entityName}" id="${entityName}Result">
        <#list fields as field>
            <result property="${field.fieldName}" column="${field.fieldNameInDb}"/>
        </#list>
    </resultMap>

    <sql id="select${entityName}Vo">
        select <#list fields as field><#if field_has_next>${field.fieldNameInDb},<#else>${field.fieldNameInDb}</#if></#list>
        from ${tableName}
    </sql>

    <insert id="insert" parameterType="${entityPkg}.${entityName}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list fields as field>
                <if test="${field.fieldName} != null">
                    ${field.fieldNameInDb},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list fields as field>
                <if test="${field.fieldName} != null">
                    <#noparse>#{</#noparse>${field.fieldName}},
                </if>
            </#list>
        </trim>
    </insert>

    <insert id="insertList" parameterType="list">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list fields as field>
                ${field.fieldNameInDb},
            </#list>
        </trim>
        values
        <foreach collection="list" item="sco" index="index" separator=",">
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <#list fields as field>
                    <#noparse>#{sco.</#noparse>${field.fieldName}},
                </#list>
            </trim>
        </foreach>
    </insert>
    <#if primaryKeyFieldNameInDb=="-1">
    <#else>
        <delete id="deleteByPrimaryKey" parameterType="${primaryKeyType}">
            delete
            from ${tableName}
            where ${primaryKeyFieldNameInDb} = <#noparse>#{primaryKey}</#noparse>
        </delete>
    </#if>
    <delete id="deleteList" parameterType="List">
        delete
        from ${tableName} where ${primaryKeyFieldNameInDb} in
        <foreach item="record" collection="array" open="(" separator="," close=")">
            <#noparse>#{record.primaryKey}</#noparse>
        </foreach>
    </delete>

    <update id="update" parameterType="${entityPkg}.${entityName}">
        update ${tableName}
        <trim prefix="SET" suffixOverrides=",">
            <#list fields as field>
                <if test="${field.fieldName} != null">
                    ${field.fieldNameInDb} = <#noparse>#{</#noparse>${field.fieldName}},
                </if>
            </#list>
        </trim>
        where id = <#noparse>#{id}</#noparse>
    </update>

    <#if primaryKeyFieldNameInDb=="-1">
    <#else>
        <select id="selectByPrimaryKey" parameterType="${primaryKeyType}" resultMap="${entityName}Result">
            <include refid="select${entityName}Vo"/>
            where ${primaryKeyFieldNameInDb} = <#noparse>#{primaryKey}</#noparse>
        </select>
    </#if>

    <select id="selectList" parameterType="${entityName}" resultMap="${entityName}Result">
        <include refid="select${entityName}Vo"/>
        <where>
            <#list fields as field>
                <if test="${field.fieldName} != null and ${field.fieldName} != ''">
                    and ${field.fieldNameInDb} = <#noparse>#{</#noparse>${field.fieldName}}
                </if>
            </#list>
        </where>
    </select>
</mapper>
