/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu2090.framework.annotation.Url;
import etu2090.framework.ModelViews.ModelView;
public class Dept{
    @Url("getAllDept")
    public ModelView myMethodDept() {
        String s1=".jsp";
        String s="test";
        String vString=s+s1;
       ModelView m=new ModelView(vString);
        return m;
    }
    
}