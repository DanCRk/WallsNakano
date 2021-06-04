package com.futurefix.wallsnakano;

public class Estado {

    public static Boolean estadoactual=false;

    public Estado() {
    }

    public static void guardarEstado (boolean bol){
        estadoactual = bol;
    }

}
