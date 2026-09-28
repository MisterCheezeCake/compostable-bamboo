package wtf.cheeze.bamboo;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompostableBamboo implements ModInitializer {

    public static final String MOD_ID = "compostable-bamboo";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.BAMBOO, builder -> {
                ResourceKey<ContextIntProvider> low = ResourceKey.create(
                        Registries.CONTEXT_INT_PROVIDER,
                        Identifier.withDefaultNamespace("compostable/low")
                );
                builder.set(DataComponents.COMPOSTABLE, new Compostable(low));
            });
        });

        LOGGER.info("Bamboo is now compostable (30%)!");
    }
}