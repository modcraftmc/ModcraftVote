package fr.modcraftmc.modcraftvote.vote;

import fr.modcraftmc.modcraftvote.ModcraftVote;
import fr.modcraftmc.modcraftvote.TimeHelper;
import fr.modcraftmc.modcraftvote.utils.HttpUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Tracker {
    private ServerPlayerEntity entity;

    private TimeHelper timeHelper = new TimeHelper();

    public Tracker(ServerPlayerEntity entity) {
        this.entity = entity;
    }

    public void startTracking() {

        Timer timer = new Timer();

        timeHelper.reset();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (timeHelper.hasReached(300000)) {

                    entity.sendMessage(new StringTextComponent("§cVous avez trop de temps pour voter, vote annulé."));

                    timer.cancel();
                    timer.purge();
                }

                VoteResponse response = HttpUtils.sendRequest(entity.getPlayerIP());

                if (response.status.equalsIgnoreCase("1")) {

                    Date date = new Date();
                    Timestamp currentts = new Timestamp(date.getTime());

                    long current = currentts.getTime();
                    long datevote = Long.parseLong(response.vote);


                    System.out.println(datevote + 5600);
                    System.out.println(current);

                    if ((datevote + 5600) >= current) {


                        if (response.pseudo.equalsIgnoreCase(entity.getName().getString())) {
                            ModcraftVote.tracker.sendLoot(entity, response);
                        }

                    } else {
                        entity.sendMessage(new StringTextComponent("vous avez deja voter"));
                    }

                    timer.cancel();
                    timer.purge();
                }

            }
        }, 10000, 5000);
    }
}
