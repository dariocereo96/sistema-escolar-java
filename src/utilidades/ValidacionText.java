/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author PABLO
 */
public class ValidacionText {

    public static void soloNumeros(KeyEvent evt, int size) {
        Character c = evt.getKeyChar();
        JTextField text = (JTextField) evt.getSource();

        if (!Character.isDigit(c) || text.getText().length() >= size) {
            evt.consume();
        }
    }

    public static void soloLetras(KeyEvent evt, int size) {
        Character c = evt.getKeyChar();
        JTextField text = (JTextField) evt.getSource();
        if (Character.isLetter(c) || Character.isWhitespace(c)) {
            if (text.getText().length() >= size) {
                evt.consume();
            }

        } else {
            evt.consume();
        }
    }

}
