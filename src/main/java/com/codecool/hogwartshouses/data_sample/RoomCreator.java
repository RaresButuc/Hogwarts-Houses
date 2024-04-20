package com.codecool.hogwartshouses.data_sample;

import com.codecool.hogwartshouses.service.DAO.RoomMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomCreator {

    @Autowired
    RoomMemory roomMemory;

    public void initialize() {
    //TODO
    }

    public RoomCreator() {
        initialize();
    }
}
