package net.thejadeproject.ascensionib.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thejadeproject.ascensionib.AscensionIB;
import net.thejadeproject.ascensionib.screen.custom.PillCauldronLowHumanMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, AscensionIB.MOD_ID);



    public static final DeferredHolder<MenuType<?>, MenuType<PillCauldronLowHumanMenu>> PILL_CAULDRON_LOW_HUMAN_MENU =
            registerMenuType("pill_cauldron_low_human_menu", PillCauldronLowHumanMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
