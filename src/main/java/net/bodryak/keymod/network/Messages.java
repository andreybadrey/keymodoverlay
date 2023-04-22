package net.bodryak.keymod.network;

import net.bodryak.keymod.Keymod;
import net.bodryak.keymod.network.packets.DisplayOverlayS2CPacket;
import net.bodryak.keymod.network.packets.PressKeyOnPlayerC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
/*
    как и весь остальной код в данном примере, этот не отличается своей примитивностью
 */
public class Messages {
    //нам нужен обьект класса SimpleChannel
    private static SimpleChannel INSTANCE;
    //Создаем поле id пакета и метод который будет увеличивать id пакета на 1 каждый раз
    private static int packetId = 0;
    private static int id(){return packetId++;}


    //Создаем два метода: отправка на сервер и отправка на клиент
    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }



    //создаем метод регистрации наших пакетов, этот код шаблонный, думаю описывать его не так уж и важно
    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Keymod.MOD_ID, "messages"))
                .networkProtocolVersion(()->"1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;
        //Осталось прописать исполнение метода register в коде главного класса мода перегодим туда
        //на этой строке шаг 3 считается завершенным перегодим к шаг 4


        //код ниже пишется на шаге 4
        //регистрируем пакет, важно обратить внемание на NetworkDirection.PLAY_TO_SERVER
        //и прописываем его в коде шага 2 в классе ClientEvents
        net.messageBuilder(PressKeyOnPlayerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PressKeyOnPlayerC2SPacket::new)
                .encoder(PressKeyOnPlayerC2SPacket::toBytes)
                .consumerMainThread(PressKeyOnPlayerC2SPacket::handler)
                .add();

        //код ниже пишется на шаге 5
        //регистрируем пакет, важно обратить внемание на NetworkDirection.PLAY_TO_CLIENT
        //и прописываем его в нашем пакете PressKeyOnPlayerC2SPacket
        net.messageBuilder(DisplayOverlayS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(DisplayOverlayS2CPacket::new)
                .encoder(DisplayOverlayS2CPacket::toBytes)
                .consumerMainThread(DisplayOverlayS2CPacket::handler)
                .add();

    }

}
