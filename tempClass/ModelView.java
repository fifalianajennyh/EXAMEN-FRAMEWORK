/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2090.framework.ModelViews;

/**
 *
 * @author itu
 */
public class ModelView {
      String view;

    // constructor
    public ModelView(String view) {
        this.setView(view);
    }

    // setter
    public void setView(String view) {
        this.view = view;
    }

    // getter
    public String getView() {
        return this.view;
    }   
}
