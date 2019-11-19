package com.repac.ajax;

import com.repac.model.User;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;


import java.util.Arrays;
import java.util.List;

public class Example extends WebPage {
    private List<User> userList;
    private WebMarkupContainer container;
    private String addEmail = "Add Email";

    public Example() {
        this.userList = addUsers();
        createViewList();
        createSubmitButton();


    }

    public String getAddEmail() {
        return addEmail;
    }

    public void setAddEmail(String addEmail) {
        this.addEmail = addEmail;
    }

    private void createSubmitButton() {
        PropertyModel<String> addEmailModel = new PropertyModel<>(this, "addEmail");
        container.add(new Label("msg", addEmailModel));

        Form<?> form = new Form("form");
        form.add(new TextField<>("emailInput", addEmailModel));

        container.add(form);
    }

    private void createViewList() {
        container = new WebMarkupContainer("container");
        ListView listview = new ListView<User>("rows", userList){
            public void populateItem(final ListItem<User> item)
            {
                final User user = item.getModelObject();
                item.add(new Label("email", user.getEmail()));
            }
        };

        add(container);
        container.add(listview);
    }

    private List<User> addUsers() {
        return Arrays.asList(
            new User("mikael.tofvesson@testmail.com"),
            new User("kalle.svensson@testmail.com"),
            new User("angelica.seger@testmail.com")
        );
    }
}
