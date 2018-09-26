package com.hniu.entity.vo;

import com.hniu.entity.Cost;
import com.hniu.entity.Readers;

public class CostVo extends Cost {
    private Readers readers;

    public Readers getReaders() {
        return readers;
    }

    public void setReaders(Readers readers) {
        this.readers = readers;
    }
}
