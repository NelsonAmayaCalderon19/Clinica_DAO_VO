/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import control.Controlador;
import vista.Vista;

/**
 *
 * @author NELSON
 */
public class Principal {
    public static void main(String[] args) {
         Principal m = new Principal();
    Vista v = new Vista();
    Controlador c = new Controlador(v);
    }
   
}
