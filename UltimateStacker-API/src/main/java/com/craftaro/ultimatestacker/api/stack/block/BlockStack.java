package com.craftaro.ultimatestacker.api.stack.block;

import com.craftaro.core.third_party.com.cryptomorin.xseries.XMaterial;
import com.craftaro.ultimatestacker.api.utils.Hologramable;
import com.craftaro.ultimatestacker.api.utils.Stackable;
import com.craftaro.core.compatibility.CompatibleMaterial;
import com.craftaro.core.database.Data;

public interface BlockStack extends Stackable, Hologramable, Data {


    int getId();

    void destroy();

    XMaterial getMaterial();
}