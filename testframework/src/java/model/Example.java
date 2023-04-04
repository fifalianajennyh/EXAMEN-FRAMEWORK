package model;

import etu2090.framework.annotation.Url;
import etu2090.framework.modelView.ModelView;

public class Example {

    @Url("/mety")
    public ModelView helloWorld() {
        return new ModelView("views/index.jsp");
    }
}