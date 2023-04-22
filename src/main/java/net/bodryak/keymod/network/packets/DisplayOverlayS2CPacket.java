package net.bodryak.keymod.network.packets;

import net.bodryak.keymod.client.Data;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/*
    шаблон этого кода идентичен PressKeyOnPlayerC2SPacket
 */
public class DisplayOverlayS2CPacket {

    public DisplayOverlayS2CPacket(){

    }

    public DisplayOverlayS2CPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handler(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            //прописываем действие которое нужно выполнить на клиенте
            //это присвоение Data.display значиния true
            //после того как клиент игрока получит данный пакет, у игрока отразится оверлей
            Data.setDisplay(true);
            //пропишем наш пакет в пакетной системе, переходим в Messages
            });
        return true;
    }
}
