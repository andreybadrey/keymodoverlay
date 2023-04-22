package net.bodryak.keymod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.bodryak.keymod.Keymod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

/*
    Логика овелея очень проста и примитивна
    1 оверлей включен всегда, при либых ценариях мода оверлей находится в активном состоянии
    2 оверлей принимает из вне значение всего одной переменной, и в зависимости от значения переменной, рендерит необходимую картинку на экран
        при желании данный код можно раширить и добавить в переменную множество состояний, и так же в оверлей множество  реакций на эти состояния
 */



public class Overlay {
    // в строке ниже регистрируется картинка для вывода на экран, если состояний переменной предполагается больше чем одно, на каждое состояние нужно зарегистрировать картинку ниже
    // можно прописовать пути к картинкам прямо в коде рендера, но с каждым тиком пока оверлей активен, будет создаваться новая копия картинки для вывода, это засрет ОЗУ вашего компьютера
    private static final ResourceLocation IMG = new ResourceLocation(Keymod.MOD_ID, "textures/hud/call.png");


    //ниже сам код оверлея, это еще не рендер
    public static final IGuiOverlay CALL_HUD = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        // в коде ниже мы получаем значение нашей переменной
        // переменная в данном прмере хранится в классе Data пакета net.bodryak.keymod.client
        if(Data.getDisplay()){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, IMG);
            GuiComponent.blit(poseStack,x - (216/2), 50,0,0,216,43,
                    216,43);
            //так же в этом коде вы можете прописать по аналогии рендер кнопок в зависимости от нужд
        }
        //регистрируем оверлей в классе CLientEvents
        //на этом работа с оверлеем окончена переходим в класс Data
    });
}
