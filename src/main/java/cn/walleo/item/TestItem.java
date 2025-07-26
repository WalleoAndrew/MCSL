package cn.walleo.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//import MODID
import cn.walleo.secretlab;

public class TestItem {

    // 创建一个延迟注册器用于存放物品，这些物品都将在 "secretlab" 命名空间下注册
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, secretlab.MODID);

    // 创建一个新食物项，ID 为 "secretlab:example_id"，营养值 1，饱食度 2
    public static final RegistryObject<Item> TEST_FOOD = ITEMS.register("test_food", () ->
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible().nutrition(1)
                            .saturationModifier(2f)
                            .build()
                    )
            )
    );

    //创建一个自定义物品
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () ->
            new Item(new Item.Properties())     //物品的名称不在此处声明而是在assets.secretlab.lang中声明！
    );

    //注册延时注册
    public static void register(IEventBus regBus){
        ITEMS.register(regBus);
    }
}
