package fr.modcraftmc.modcraftvote.vote;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;

public class VoteTracker {

    private HashMap<ServerPlayerEntity, Tracker> tracked = new HashMap<>();


    public void addVoteTracker(ServerPlayerEntity entity) {

        if (!tracked.entrySet().contains(entity)) {
            tracked.put(entity, new Tracker(entity));

            tracked.get(entity).startTracking();
        }

    }

    public void sendLoot(ServerPlayerEntity entity, VoteResponse response) {


        if (entity.inventory.addItemStackToInventory(new ItemStack(Items.DIAMOND))) {

            entity.sendMessage(new StringTextComponent("§eMerci d'avoir voté, vous avez reçu votre récompense."));
            entity.sendMessage(new StringTextComponent("§eProchain vote disponible dans 1h30."));
        } else {
            entity.sendMessage(new StringTextComponent("§eVotre inventaire est plein."));
        }

    }

}
