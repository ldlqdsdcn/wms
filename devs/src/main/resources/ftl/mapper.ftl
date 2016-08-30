<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.delmar.${module}.mybatis.sql.${model}Mapper" >
    <resultMap id="BaseResultMap" type="com.delmar.${module}.model.${model}" >
    <#list columnList as column>
        <#if column.columnName=='id'>
            <id column="id" property="id" jdbcType="INTEGER" />
        <#else>
            <result column="${column.columnName}" property="${column.propertyName}" jdbcType="${column.type}" />
        </#if>
    </#list>
    </resultMap>
    <sql id="Example_Where_Clause" >
        <where >
            <#list foreignList as item>
                <if test="${item.propertyName}!=null"> and ${item.columnName}=${r'#{'}${item.propertyName},jdbcType=${item.type}} </if>
            </#list>
                <if test="searchString!=null"> and ${r'${searchString}'} </if>
                <if test="accessString!=null"> and ${r'${accessString}'} </if>

        </where>
    </sql>
    <sql id="Base_Column_List" >
    <#list columnList as column>  ${column.columnName}<#if column_has_next>,</#if> </#list>
    </sql>
    <select id="selectByExample" resultMap="BaseResultMap" parameterType="java.util.Map" >
        select
        <if test="distinct" >
            distinct
        </if>
        <include refid="Base_Column_List" />
        from ${tableName}
        <if test="_parameter != null" >
            <include refid="Example_Where_Clause" />
        </if>
        <if test="orderByClause != null" >
            order by ${r'${orderByClause}'}
        </if>
    </select>
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Integer" >
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        where id = ${r'#{id,jdbcType=INTEGER}'}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer" >
        delete from ${tableName}
        where id = ${r'#{id,jdbcType=INTEGER}'}
    </delete>
    <delete id="deleteByExample" parameterType="java.util.Map" >
        delete from ${tableName}
        <if test="_parameter != null" >
            <include refid="Example_Where_Clause" />
        </if>
    </delete>
    <insert id="insert" parameterType="com.delmar.${module}.model.${model}" >
        <selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER" >
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into ${tableName} (<#list columnList as column>  <#if column.columnName!='id'>${column.columnName}<#if column_has_next>,</#if></#if> </#list>)
        values (<#list columnList as column> <#if column.columnName!='id'>${r'#{'}${column.propertyName},jdbcType=${column.type}}<#if column_has_next>,</#if></#if></#list> )
    </insert>
    <insert id="insertSelective" parameterType="com.delmar.${module}.model.${model}" >
        <selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER" >
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <#list columnList as column><#if column.columnName!='id'>
                <if test="${column.propertyName} != null" >
                    ${column.columnName},
                </if>
                </#if></#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
<#list columnList as column><#if column.columnName!='id'>
    <if test="${column.propertyName} != null" >
    ${r'#{'}${column.propertyName},jdbcType=VARCHAR}${'},'}
    </if>
</#if></#list>
        </trim>
    </insert>
    <select id="countByExample" parameterType="java.util.Map" resultType="java.lang.Integer" >
        select count(*) from ${tableName}
        <if test="_parameter != null" >
            <include refid="Example_Where_Clause" />
        </if>
    </select>
    <update id="updateByPrimaryKeySelective" parameterType="com.delmar.${module}.model.${model}" >
        update ${tableName}
        <set >
<#list columnList as column><#if column.columnName!='id'>
    <if test="${column.propertyName} != null" >
        ${column.columnName} = ${r'#{'}${column.propertyName},jdbcType=${column.type}},
    </if>
</#if></#list>

        </set>
        where id = ${r'#{id,jdbcType=INTEGER}'}
    </update>
    <update id="updateByPrimaryKey" parameterType="com.delmar.core.model.Window" >
        update ${tableName}
        set <#list columnList as column>
            <#if column.columnName!='id'>${column.columnName} = ${r'#{'}${column.propertyName},jdbcType=${column.type}}<#if column_has_next>,</#if>
            </#if></#list>

        where id = ${r'#{id,jdbcType=INTEGER}'}
    </update>
</mapper>