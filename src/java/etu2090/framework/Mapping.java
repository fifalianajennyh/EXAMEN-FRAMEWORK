/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
///////
package etu2090.framework;

public class Mapping {

        private String className;
        private String method;
        public Mapping(){}
        
        public Mapping(String className, String method) {
        this.className = className;
        this.method = method;
       }
        
        public String getClassName() {
        return className;
        }
        
        public String getMethod() {
        return method;
        }
        public void setClassName(String className) {
            this.className = className;
        }
        public void setMethod(String method) {
            this.method = method;
        }
        
        
}
