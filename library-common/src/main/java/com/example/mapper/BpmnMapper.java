package com.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.constant.DatasourceConstant;
import com.example.entity.BpmnModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@DS(DatasourceConstant.DATASOURCE_SLAVE_01)
public interface BpmnMapper extends BaseMapper<BpmnModelEntity> {
    @Select("select id as id, name as name, model_key as model_key, description as description, model_editor_json as model_editor_json, thumbnail as thumbnail from act_de_model")
    List<BpmnModelEntity> getModelInfo(@Param("key") String model_key);
}
