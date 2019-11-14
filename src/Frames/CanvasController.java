/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Frames;

import Methods.Metodos;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;

/**
 *
 * @author DanielR
 */
public class CanvasController extends Canvas implements ActionListener,MouseListener{

    
       
    private Metodos singleton;
    private Color color;
    private Path2D shape;
    private String tempName;
    
    public CanvasController(int width,int height) {
        this.setBackground(color.white);
        this.setSize(width, height);
        this.singleton = Metodos.getInstance();
        this.tempName = "";
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D drawer = (Graphics2D) g;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //dio click
        if(!this.tempName.isEmpty()){
            this.tempName = "";
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void setTempName(String name){
        this.tempName = name;
    }
    
}
