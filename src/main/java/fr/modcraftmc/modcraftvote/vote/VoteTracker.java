package fr.modcraftmc.modcraftvote.vote;

import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashMap;

public class VoteTracker implements Runnable {

    private HashMap<ServerPlayerEntity, Tracker> tracked = new HashMap<>();


    public void addVoteTracker(ServerPlayerEntity entity) {

        if (!tracked.entrySet().contains(entity)) {
            tracked.put(entity, new Tracker(entity));

            tracked.get(entity).startTracking();
        }

    }

    @Override
    public void run() {


    }
}
