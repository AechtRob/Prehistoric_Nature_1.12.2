package net.lepidodendron.util;

import net.minecraft.util.IStringSerializable;

public enum EnumBiomeTypeCarboniferous implements IStringSerializable {

    Swamp("swamp"),
    Ice("ice"),
    Ocean("ocean")
    ;

    private final String name;

    private EnumBiomeTypeCarboniferous(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}