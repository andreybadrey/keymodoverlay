package net.bodryak.keymod.network.packets;

import net.bodryak.keymod.network.Messages;
import net.bodryak.keymod.util.Cipher;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
/*
    И так в данном классе описан метод отправке данных на сервер
    поскольку, я не нашел способа отправить данные в формате String
    вы наблюдаете подобную дич что творится ниже, я считаю данный код глупым
    но на данном этапе развития своих навыков я прибегнул к нему
    Вам нужно ипользовать другой код, потому что делать так как сделал я очень глупо,
    с моей точки зрения, и скорее всего с точки зрения всех остальных программитов
    но в целом, шаблон пакета является верным
    вы его можете скопировать ниже в самом конце файла
 */
public class PressKeyOnPlayerC2SPacket {
    private final int pId;

    //Метод записывает в переменную pId переданное значение
    public PressKeyOnPlayerC2SPacket(int val){
        this.pId =val;
    }

    //Метод считывает переданное значение в формате Byte
    public PressKeyOnPlayerC2SPacket(FriendlyByteBuf buf){
        this.pId = buf.readInt();
    }

    //метод записывает в формате байт передаваемое значение
    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(pId);
    }

    //метод ниже выполняет код на сервере либо на клиенте, в зависимости от пакета
    public boolean handler(Supplier<NetworkEvent.Context> supplier){

        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() ->{
            //тут мы получем игрока который отправил пакет и записываем его в переменную(обьект) player
            ServerPlayer player = context.getSender();
            //тут мы получаем локацию игрока который передал пакет и записываем ее в world
            Level world= player.getLevel();

            //далее, поскольку id игрока который находился в таргете был передан в закодированном виде
            //нужно найти этого игрока закодировав имена всех игроков аналогичным методом
            //конструкция ниже осуществляет это используя     entityiterator
            for (Entity entityiterator : new ArrayList<>(world.players())){
                //если существо в entityiterator является игроком, мы кодируем его имя и сравниваем с переданным значением
                if (entityiterator instanceof Player _player && !_player.level.isClientSide()){
                    String playerName = _player.getName().getString();
                    ServerPlayer serverPlayer = (ServerPlayer) _player;
                    int id = Cipher.getIntFromString(playerName);
                    //как только совпадение будет найдено данному игроку передается пакет
                    //но сначало данный пакет надо зарегистрировать в нашем классе Messages
                    if(pId == id){
                        //код ниже пишется на 5
                        //отправляем пакет данному игроку
                        Messages.sendToPlayer(new DisplayOverlayS2CPacket(), serverPlayer);
                        //теперь когда один игрок нажмет кнопку F держа в таргете другого игрока, у игрока в таргете отобразится оверлей
                        //на этом шаг 5 считается выполненым переходим к шагу 6
                    }

                }

            }


            });

        return true;
    }
}

/*
        Шаблон пакета

public class PressKeyOnPlayerC2SPacket {
    private final int val;
    public PressKeyOnPlayerC2SPacket(){
    }

    public PressKeyOnPlayerC2SPacket(FriendlyByteBuf buf){
        this.val = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(val);
    }

    public boolean handler(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            //Этот код выполняется на сервере

            });

        return true;
    }
}
 */
