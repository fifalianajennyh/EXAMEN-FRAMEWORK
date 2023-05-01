/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2090.framework.servlet;
import etu2090.framework.Mapping;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import etu2090.framework.annotation.Url;
import etu2090.framework.ModelViews.ModelView;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import model.Dept;
//import model.Emp;

/**
 * Front Servlet qui gère les requêtes HTTP.
 */
public class FrontServlet extends HttpServlet {

   // private static final long serialVersionUID = 1L;
     HashMap<String, Mapping> mappingUrls=new HashMap <String, Mapping>();
     String packages;
    /**
     * Initialise la servlet.
     * @param config
     * @throws javax.servlet.ServletException
     */

    @Override
    public void init(ServletConfig config) throws javax.servlet.ServletException {
        super.init(config);
        
        this.packages=getServletConfig().getInitParameter("modelPackage");
     
         try {
             this.getAllMapping(this.packages);
         } catch (URISyntaxException ex) {
             Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
       
                
    }

    
    @SuppressWarnings("empty-statement")
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, ServletException {
        PrintWriter out=resp.getWriter();
        String url=req.getRequestURI();
        String page=url.substring(url.lastIndexOf("/")+1);
        
        
       // Mapping mapping = this.getMappingUrls().get(page);
        
      for(Map.Entry<String, Mapping> entry : this.mappingUrls.entrySet()) 
            {
               String key = entry.getKey();
                Mapping mai = entry.getValue();
                out.print(page);
                //out.println("valeur de url    " + key + "     " + "    Nom de la classe qui a l'annotation       " + mai.getClassName() + "       " + "      methodes qui a l'annotation  " + mai.getMethod()); 
            if (key.compareTo(page)==0) {
                
                try {
                    PrintWriter oPrintWriter=resp.getWriter();
                    Class<?> class1=Class.forName(packages+"."+mai.getClassName());
                    Object object=class1.newInstance();
              //      oPrintWriter.println(class1.getName());

                    Method method=object.getClass().getMethod(mai.getMethod());
            //        oPrintWriter.println(method.getName());
                    ModelView view=(ModelView)method.invoke(object);
                    String modelString="WEB-INF/views/"+view.getView();
                 RequestDispatcher dispatcher = req.getRequestDispatcher(modelString);
                   dispatcher.forward(req, resp);                   

                /*Method method = target.getClass().getDeclaredMethod(mai.getMethod());
                    Object result = method.invoke(target);
                    out.println(method.getName());
                    /*if (result instanceof ModelView modelView) {
                        String view = modelView.getView();
                        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
                        dispatcher.forward(req, resp);
                    }*/
                } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException | InvocationTargetException e) {
                    out.println(e.getMessage());
                
                }

                
                }
        
        }
            
            
     }    
         
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException | ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (URISyntaxException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void getAllMapping(String packageName) throws URISyntaxException {
   // HashMap<String, Mapping> mappingUrls = new HashMap<>();
    
     //   String path="model";
       URL packageURL=Thread.currentThread().getContextClassLoader().getResource(packageName);
        File packageDirectory=new File(packageURL.toURI());
        File[] inside=packageDirectory.listFiles();
       for(int i=0;i<inside.length;i++)
       {
           String[] n=inside[i].getName().split("[.]");
       try{
            Class<?> classes =Class.forName(packageName+"."+n[0]);
        //    Object object = clazz.newInstance();
            Method[] methods = classes.getDeclaredMethods();
            for (int j=0;j<methods.length;j++) 
            {
                if (methods[j].isAnnotationPresent(Url.class))
                {
                    Url annotation = methods[j].getAnnotation(Url.class);
                    String cle = annotation.value();
                    String classN = classes.getSimpleName();
                    //String classN =object.getClass().getName();
                    String methodes = methods[j].getName();
                    Mapping map = new Mapping(classN, methodes);
                    this.mappingUrls.put(cle, map);
                }
            }
       }catch(ClassNotFoundException e){
       }
        }
        
   // return mappingUrls;
    }







/**
 * Retourne la map des urls mappées.
     * @return 
 */
    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

/**
 * Définit la map des urls mappées.
     * @param mappingUrls
 */
    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }
}