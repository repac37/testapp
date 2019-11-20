package com.repac.form;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.*;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.w3c.dom.ranges.Range;

public class FormValidationTest extends WebPage {


    public FormValidationTest() {
        prepareComponent();
    }

    private void prepareComponent() {
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMsg");
        feedbackPanel.setOutputMarkupId(true);
        Form form = new Form("form");
        TextField<String> name = new RequiredTextField<String>("name", new Model<String>());
        TextField<Integer> age = new RequiredTextField<Integer>("age", new Model<Integer>());
        TextField<String> email = new RequiredTextField<String>("email", new Model<String>());
        TextField<String> zipcode = new RequiredTextField<String>("zipcode", new Model<String>()){

            @Override
            protected void onInvalid() {
                super.onInvalid();
                if(hasErrorMessage())
                    add(AttributeModifier.replace("style",  "box-shadow: inset 0 0 1px 1px red;"));
                else
                    add(AttributeModifier.replace("style",  ""));
            }
        };
        zipcode.setOutputMarkupId(true);

        age.setType(Integer.class);
        age.add(RangeValidator.range(18,60));
        EmailAddressValidator  emailAddressValidator = EmailAddressValidator.getInstance();
        email.add(emailAddressValidator);

        zipcode.add(new ZipCodeVaildator());

        Button button = new Button("submit") {
            @Override
            public void onSubmit() {
                super.onSubmit();
            }
        };

        AjaxButton ajaxButton = new AjaxButton("submitButton") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {

                super.onSubmit(target);
                target.add(feedbackPanel);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
//                if (zipcode.hasFeedbackMessage()) {
//                    zipcode.add(AttributeModifier.replace("style", "border: 5px solid red;"));
//                } else {
//                    zipcode.add(AttributeModifier.replace("style", ""));
//                }
                target.add(zipcode);
                target.add(feedbackPanel);
            }
        };


        form.add(ajaxButton);
        form.add(button);
        form.add(name);
        form.add(age);
        form.add(email);
        form.add(zipcode);

        add(feedbackPanel);
        add(form);
    }

    private class ZipCodeVaildator implements IValidator<String>{
        @Override
        public void validate(IValidatable<String> validatable) {
            String zipCode = validatable.getValue();
            if(zipCode.startsWith("411") || zipCode.length() != 6){
                validatable.error(new ValidationError().setMessage("Incorrect ZipCode."));
            }
        }
    }

}

