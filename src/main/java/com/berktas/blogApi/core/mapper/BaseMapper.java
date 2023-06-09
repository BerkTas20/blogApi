package com.berktas.blogApi.core.mapper;



import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;


public interface BaseMapper<Dto, Entity> {
    @Named("entityToDto")
    Dto entityToDto(Entity entity);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<Dto> entityListToDtoList(List<Entity> entityList);



}

