package net.bodryak.keymod;

import com.mojang.logging.LogUtils;
import net.bodryak.keymod.network.Messages;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/*
    Примитивный пример реализации отправки пакетов с клиента на клиент
    При желании логику можно адаптировать на более раннии версии

    Шаг 1: и наверное самый простой создание оверлея
        в данном примере я создаю оверлей в пакете net.bodryak.keymod.client класс Overlay

    Шаг 2:На этом шаге нужно создать тригер отправки пакета на сервер, в данном примере это нажатие кнопки
        создаем пакет keys и класс KeyBinding

    Шаг 3: Создание пакетной системы
        Создаем класс Messages в пакете network и переходим туда

    Шаг 4: создание пакета который будет отправляться на сервер после нажатия кнопки
        переходим в пакет network и создаем там класс PressKeyOnPlayer

    Шаг 5: создание ответной реакции сервера, отправка пакета другому игроку
        Создание пакета который будет открывать оверлей у определнного игрока
        создаем новый класс DisplayOverlayS2CPacket и переходим в него

    Шаг 6: прописываем возможность игрока получившего оверлей, возможность убрать его по кнопке
            по аналогии со всеми остальными шагами, мы можем прописать альтернативную реакцию
            например отправть такой же оверлей в ответе и тому подобное
            Переходим в наш KeyBinding

 */

@Mod(Keymod.MOD_ID)
public class Keymod
{
    public static final String MOD_ID = "keymod";
    public Keymod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        //Регистрируем нашу пакетную систему тут
        Messages.register();
    }

}
