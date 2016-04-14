/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;
import javax.swing.JPanel;
import Model.Aplikasi;
/**
 *
 * @author Erwin
 */
public class Controller extends MouseAdapter implements ActionListener{
    private Aplikasi Model;
//    private PanelContainer view;
    
    private String current;
    private JPanel mainPanel;
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
