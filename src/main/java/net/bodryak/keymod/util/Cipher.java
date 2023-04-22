package net.bodryak.keymod.util;

public class Cipher {

    public static int getIntFromString(String str){
        int lens = str.length();
        int i;
        int r=0;
        for (i=0; String.valueOf(r).length() <9; i++){
            r= r*10 + getNumFromChar(str.charAt(i));
            if(lens-1 == i){break;}
        }
        for(int d=0;lens -1 > i; i++){
            r= r + getNumFromChar(str.charAt(i));
        }
        return r;
    }
    public static int getNumFromChar(char ch){
        if(ch == 'A'){return 1;}
        if(ch == 'B'){return 2;}
        if(ch == 'C'){return 3;}
        if(ch == 'D'){return 4;}
        if(ch == 'E'){return 5;}
        if(ch == 'F'){return 6;}
        if(ch == 'G'){return 7;}
        if(ch == 'H'){return 8;}
        if(ch == 'I'){return 9;}
        if(ch == 'J'){return 10;}
        if(ch == 'K'){return 11;}
        if(ch == 'L'){return 12;}
        if(ch == 'M'){return 13;}
        if(ch == 'N'){return 14;}
        if(ch == 'O'){return 15;}
        if(ch == 'P'){return 16;}
        if(ch == 'Q'){return 17;}
        if(ch == 'R'){return 18;}
        if(ch == 'S'){return 19;}
        if(ch == 'T'){return 20;}
        if(ch == 'U'){return 21;}
        if(ch == 'V'){return 22;}
        if(ch == 'W'){return 23;}
        if(ch == 'X'){return 24;}
        if(ch == 'Y'){return 25;}
        if(ch == 'Z'){return 26;}
        if(ch == 'a'){return 27;}
        if(ch == 'b'){return 28;}
        if(ch == 'c'){return 29;}
        if(ch == 'd'){return 30;}
        if(ch == 'e'){return 31;}
        if(ch == 'f'){return 32;}
        if(ch == 'g'){return 33;}
        if(ch == 'h'){return 34;}
        if(ch == 'i'){return 35;}
        if(ch == 'j'){return 36;}
        if(ch == 'k'){return 37;}
        if(ch == 'l'){return 38;}
        if(ch == 'm'){return 39;}
        if(ch == 'n'){return 40;}
        if(ch == 'o'){return 41;}
        if(ch == 'p'){return 42;}
        if(ch == 'q'){return 43;}
        if(ch == 'r'){return 44;}
        if(ch == 's'){return 45;}
        if(ch == 't'){return 46;}
        if(ch == 'u'){return 47;}
        if(ch == 'v'){return 48;}
        if(ch == 'w'){return 49;}
        if(ch == 'x'){return 50;}
        if(ch == 'y'){return 51;}
        if(ch == 'z'){return 52;}
        return 0;

    }
}
