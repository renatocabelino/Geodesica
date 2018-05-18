package br.edu.ifes.vitoria.geodesica;

import android.util.Log;

import static java.lang.Math.round;

class BibliotecaFuncoes {

    // Correção de valores com a constante --> acerto <---
    public static double Correcao(double ValorEntr) {
        double ValorSaida = 0;
        double acerto = 0.000000000001;
        if (ValorEntr >= 0) {
            ValorSaida = ValorEntr + acerto;
        } else {
            ValorSaida = ValorEntr - acerto;
        }
        return ValorSaida;
    }

    public static float RadGraus(float AngRad) {
        float graud = (float) (AngRad * 180 / Math.PI);
        float resultado = 0;
        if (graud > 360) {
            resultado = graud - 360;
        } else {
            resultado = (graud);
        }
        return (resultado);
    }

    // Transforma o ângulo: de Graus Decimais para Graus Minuto e Segundo
    public static double GMS(double graud) {
        float resultado = 0;
        graud = Correcao(graud);
        int grau = (int) (graud);
        int minutos = (int) ((graud-grau) * 60);
        int segundos = (int) (minutos - ((int)(minutos)* 0.006));
        resultado = grau +((minutos)/100) + segundos;
        return (resultado);
    }

    //Cálculo de Azimute a partir de dois pares de Coordenadas
    public static float Azimute(float AbscisAnterior, float OrdenAnterior, float AbscisPosterior, float OrdenPosterior) {
        float resultado = 0;
        float deltax = AbscisPosterior - AbscisAnterior;
        float deltay = OrdenPosterior - OrdenAnterior;
        try {
            float angulo = (float) Math.atan(deltax/deltay);
            if ((deltax >= 0) && (angulo > 0)) {
                resultado = RadGraus(angulo);
            } else {
              if ((deltax >= 0) && (angulo < 0)) {
                    resultado = RadGraus(angulo) + 180;
                } else {
                  if ((deltax <= 0) && (angulo < 0)) {
                        resultado = RadGraus(angulo) + 360;
                    } else {
                        resultado = RadGraus(angulo) + 180;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("BibFuncoes.Azimute", "Divisão por zero!");
        } finally {
            if ((deltax > 0) && (deltay == 0)) {
                resultado = 90;
            } else {
                if ((deltax < 0) && (deltay == 0)) {
                    resultado = 270;
                }
            }
        }
        return round(GMS(resultado));
    }
    
}
