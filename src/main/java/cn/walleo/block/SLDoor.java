package cn.walleo.block;

import cn.walleo.secretlab;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SLDoor {
    //创建SLDoor注册器
    public static final DeferredRegister<Block> SLDOOR_BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, secretlab.MODID);
    public static final DeferredRegister<Item> SLDOOR_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, secretlab.MODID);



//    // 创建一个轻收容区铁门Block id: "secretlab:lcz_door1.java"
//    public static final RegistryObject<Block> LCZ_DOOR_BLOCK_1 = SLDOOR_BLOCK.register("lcz_door1.java",
//            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
//                    .mapColor(MapColor.RAW_IRON)
//                    .pushReaction(PushReaction.IGNORE)
//            ));
////                    .mapColor(MapColor.STONE) //在地图上显示的材质
////                    //.instabreak() //即刻破坏，即生存内点一下就破坏，那么这里注释掉
////                    .pushReaction(PushReaction.PUSH_ONLY) //只能被活塞推动但不能收回
////                    .lightLevel(param -> 15) //内光源
////                    .emissiveRendering((pState, pLevel, pPos) -> true) //外发光，照亮周围
////                    .sound(SoundType.METAL) //走在上面发出的声音
////            ));
//    // 创建一个方块的Item，ID为"secretlab:lcz_door1.java"，结合了命名空间和路径,此处命名空间不能加item，如lcz_door1_item
//    public static final RegistryObject<Item> LCZ_DOOR_ITEM_1 = SLDOOR_ITEMS.register("lcz_door1.java",
//            () -> new BlockItem(LCZ_DOOR_BLOCK_1
//                    .get(), new Item
//                    .Properties()
//            ));


    //注册lczdoor1.java
    public static final RegistryObject<Block> LCZ_DOOR1 = SLDOOR_BLOCK.register("lcz_door1",
        () -> new LCZDoor(BlockSetType.IRON,
                BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .pushReaction(PushReaction.IGNORE)
                    .lightLevel(param -> 15)
                    .emissiveRendering((pState, pLevel, pPos) -> true)
                    .sound(SoundType.METAL)
                    .noOcclusion()
        ));

public static final RegistryObject<Item> LCZ_DOOR1_ITEM = SLDOOR_ITEMS.register("lcz_door1",
        () -> new BlockItem(LCZ_DOOR1.get(), new Item.Properties()));
















    //注册
    public static void register(IEventBus regBus){
        SLDOOR_ITEMS.register(regBus);
        SLDOOR_BLOCK.register(regBus);
    }

}
