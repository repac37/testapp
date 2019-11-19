package com.repac;

import com.repac.panels.AnotherPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		add(new Label("helloMessage", "HelloWorld!"));

		add(new Link<Void>("anotherPage") {
			@Override
			public void onClick() {
				setResponsePage(AnotherPage.class);
			}
		});
	}
}
