package cn.walleo.tab;

import cn.walleo.item.TestItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
//import Main Class
import cn.walleo.secretlab;
//import SLDOOR.java
import cn.walleo.block.SLDoor;


public class TestTab {
    //对DeferredRegister(延时注册)提出我想注册一个CreativeModeTab(创造模式物品栏),这个物品栏的唯一变量为CREATIVE_MODE_TABS,使用forge提供的方法-DxxxxRegister.create向MC内置注册表Registries注册,MODID=secretlab.MODID
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, secretlab.MODID);


    // 为示例物品创建一个物品栏，其ID为 "secretlab:test_tab"

    public static final RegistryObject<CreativeModeTab> TEST_TAB = TestTab.CREATIVE_MODE_TABS.register("test_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.secretlab.test_tab"))    //使用Component.literal定义物品栏标题,但是使用translatable的话可以根据语言调整
            //.icon(Items.APPLE::getDefaultInstance)  //显示物品栏的图标为原版APPLE
            .icon(() -> new ItemStack(TestItem.TEST_ITEM.get()))    //显示物品栏图标为TestItem类中定义的TEST_ITEM
            .withTabsBefore(CreativeModeTabs.FOOD_AND_DRINKS)    //定义该物品栏在 FOOD_AND_DRINKS 原版物品栏的前面
            .withSearchBar()    //添加搜索框，适用于物品较多的时候
            .displayItems((parameters, output) -> {

                //output.accept(Items.BARRIER);// 往物品栏内添加物品，没有这行代码则物品栏为空. For your own tabs, this method is preferred over the event
                //output.accept(Items.COMMAND_BLOCK);//这里添加了屏障和命令方块


                output.accept(TestItem.TEST_ITEM.get());//get到新添加的物品
                output.accept(SLDoor.LCZ_DOOR_ITEM_1.get());//清收小门

            })
            .build());

    //首次注册TABS，需要在主类中再次注册
    public static void register(IEventBus regBus){
        CREATIVE_MODE_TABS.register(regBus);
    }
}
