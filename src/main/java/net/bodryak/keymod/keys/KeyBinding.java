package net.bodryak.keymod.keys;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;
/*
    Тут все лаканично прописываем константы и создаем копии наших кнопок
 */
public class KeyBinding {
    // константы группы
    public static final String KEY_CATEGORY = "key.category.keymod.keymod";
    //константы кнопок
    public static final String KEY_SEND_OVERLAY = "key.keymod.sendoverlay";
    public static final String KEY_OFF_OVERLAY = "key.keymod.offoverlay";

    //оздание копий класса KeyMapping
    public static final KeyMapping SEND_OVERLAY = new KeyMapping(KEY_SEND_OVERLAY, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F, KEY_CATEGORY);
    //эта кнопка используется на шаге 6
    //создаем кнопку реакции на оверлей
    //и по аналогии с кнопкой отправки делаем все тоже самое, либо что то другое в зависимости от предпочтений и нужд
    //переходим ClientEvents
    public static final KeyMapping OFF_OVERLAY = new KeyMapping(KEY_OFF_OVERLAY, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY);
    //далее нужно зарегистрировать данные кнопку
    //создаем класс ClientEvents и переходим уже туда
    //И регистрируем кнопки и реакции нажатия на них

}
