package net.bodryak.keymod.client;
/*
    в этом классе всего два метода один устанавлевает значение(реакцию оерлея) другой ее получает
    медот setDisplay используется при получении клиентом пакета от сервера
    далее будет описана работа с пакетам

    мы переходим к Шаг 2
 */
public class Data {
    private static boolean display;

    public static void setDisplay(boolean v){
        Data.display=v;
    }

    public static boolean getDisplay(){
        return display;
    }
}
