package net.kav.kav_soul_like.json_reader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.util.item_requirement.weapon_req;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.lwjgl.opengl.EXTABGR;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

//thx to https://github.com/Globox1997/LevelZ/blob/1.19/src/main/java/net/levelz/data/LevelLoader.java#L23
public class statsloader implements SimpleSynchronousResourceReloadListener {

    @Override
    public Identifier getFabricId() {
        return new Identifier(Kav_soul_like.MOD_ID,"stats_loader");
    }

    @Override
    public void reload(ResourceManager manager) {

        weapon_req.clear();

        manager.findResources("stats", id -> id.getPath().endsWith(".json")).forEach((id, resourceRef) -> {
            try {
                InputStream stream = resourceRef.getInputStream();
                JsonObject data = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
                if(data.get("category").getAsString().equals("weapon"))
                {
                    weapon_req.add(data.get("name").getAsString(),"weapon",data.get("strength").getAsInt(),data.get("health").getAsInt(),data.get("agility").getAsInt(),data.get("stamina").getAsInt(),data.get("defence").getAsInt(),data.get("magic").getAsInt());



                } else if (data.get("category").getAsString().equals("armor")) {
                    weapon_req.add(data.get("name").getAsString(),"armor",data.get("strength").getAsInt(),data.get("health").getAsInt(),data.get("agility").getAsInt(),data.get("stamina").getAsInt(),data.get("defence").getAsInt(),data.get("magic").getAsInt());

                }
                else if(data.get("category").getAsString().equals("shield"))
                {
                    weapon_req.add(data.get("name").getAsString(),"shield",data.get("strength").getAsInt(),data.get("health").getAsInt(),data.get("agility").getAsInt(),data.get("stamina").getAsInt(),data.get("defence").getAsInt(),data.get("magic").getAsInt());

                }
                else
                {
                    Kav_soul_like.LOGGER.error("Category named wrong");
                }


            }
            catch (Exception e)
            {
                Kav_soul_like.LOGGER.error("ERROR LOADING THE STATS");
            }


        });
    }
}
