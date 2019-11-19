package com.repac.panels;

import com.repac.HomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class AnotherPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AnotherPage(){
        add(new Header("header"));
        add(new Menu("menu"));
        add(new Link<Void>("homePage") {

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });
    }


}
