package com.damian.springcloud.msvc.items.services;

import java.util.List;
import java.util.Optional;

import com.damian.springcloud.msvc.items.models.Items;

public interface ItemService {

    List<Items> findAll();
    Optional<Items> findById(Long id);

    //interface para la capa de servicio
}
