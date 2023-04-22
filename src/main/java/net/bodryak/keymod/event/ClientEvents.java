package net.bodryak.keymod.event;

import net.bodryak.keymod.Keymod;
import net.bodryak.keymod.client.Data;
import net.bodryak.keymod.client.Overlay;
import net.bodryak.keymod.keys.KeyBinding;
import net.bodryak.keymod.network.Messages;
import net.bodryak.keymod.network.packets.PressKeyOnPlayerC2SPacket;
import net.bodryak.keymod.util.Cipher;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
/*
    Данный класс нам потребуется разбить на еще два класса
    ClientForgeEvents и ClientModBusEvents

    класс ClientForgeEvents должен быть подпиан аннотацией @Mod.EventBusSubscriber(modid = Keymod.MOD_ID, value = Dist.CLIENT)
    класс ClientModBusEvents должен быть подписан аннотацией  @Mod.EventBusSubscriber(modid = Keymod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    все реакции(СОБЫТИЯ) в классах должны быть подписаны аннотацией @SubscribeEvent
 */

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Keymod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        //Создаем событие в котором клиент дает реакцию на ножатие кнопки
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            //Создаем реакцию на нажатие нашей кнопки, по которой мы собираемся отправлять пакет другому клиенту
            if(KeyBinding.SEND_OVERLAY.consumeClick()){
                //как только игрок нажимает кнопку нам нужно проверь таргет игрока на наличие другого игрока
                //В данном случае я использую crosshairPickEntity полученый на прямую из клиента, вы можете использовать свой код, если хотите увеличить дистанцию поиска таргета
                if(Minecraft.getInstance().crosshairPickEntity != null){
                    //если в таргете игрока обнаружена сущность и она является другим игроком
                    if(Minecraft.getInstance().crosshairPickEntity instanceof Player ){
                        /*
                        На данном этапе я хочу отметь, что следующий код написан очень глупо, я не рекомендую использовать его в своих проектах
                        но в силу того что я новичек как и в программировани так и в данном API я прибегнул к такому способу
                        поскольку не нашел возможности отправить строку с именем игрока на сервер, нашел только возможность
                        отправить на сервер число
                        только лишь по этому, дальше вы будете наблюдать безусловную дич
                         */
                        //получаем строку с именем игрока
                        String playerName = Minecraft.getInstance().crosshairPickEntity.getName().getString();
                        //дальше поскольку я все еще не нашел способ отправлять строки на сервер, я кодирую строку игрока в число
                        // я не буду описывать код кодировшика, он сырой и довольно глупый, вы можете его использовать как временное решение
                        int playerId = Cipher.getIntFromString(playerName);
                        //отправляем пакет на сервер
                        //далее мы переходик на шаг 3
                        //код ниже пишется на шаге 4
                        Messages.sendToServer(new PressKeyOnPlayerC2SPacket(playerId));
                        //Пакет отправлен, переходим к шагу 5
                    }
                }
            }
            //код ниже пишется на шаге 6
            if(KeyBinding.OFF_OVERLAY.consumeClick()){
                //в данном примере я только отключаю оверлей, если вы хотите отправлять ответный оверлей игроку, то в пакете который включает оверлей, потребуется передать так же id отправителя
                //устанавливаем значение Data.display на false
                Data.setDisplay(false);
                //на этом Всё
            }
        }

    }
    @Mod.EventBusSubscriber(modid = Keymod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        //регитрация кнопок
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.SEND_OVERLAY);
            event.register(KeyBinding.OFF_OVERLAY);

        }
        //регистрация оверлеев
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("call", Overlay.CALL_HUD);
        }
    }
}
