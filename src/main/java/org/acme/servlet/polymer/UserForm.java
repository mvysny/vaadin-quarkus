package org.acme.servlet.polymer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.io.Serializable;

/**
 * Example taken from https://vaadin.com/docs/v14/flow/polymer-templates/tutorial-template-and-binder.html
 */
@Tag("user-form")
@JsModule("./src/user-form.js")
public class UserForm extends PolymerTemplate<TemplateModel> {

    @Id("email")
    private TextField email;

    @Id("first-name")
    private TextField firstName;

    @Id("last-name")
    private TextField lastName;

    @Id("comments")
    private TextArea comment;

    public static class User implements Serializable {
        private String email;
        private String firstName;
        private String lastName;
        private String comment;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    private Binder<User> binder;

    private void initBinder() {
        binder = new Binder<>();

        // email
        binder.forField(email).withValidator(
                new EmailValidator("This doesn't look like a valid email address")
        ).bind(User::getEmail, User::setEmail);

        // firstName
        binder.forField(firstName).withValidator(firstName -> firstName.length() > 1,
                "The first name must contains at least 2 characters").asRequired()
                .bind(User::getFirstName, User::setFirstName);

        // lastName
        binder.forField(lastName).asRequired("Last name can't be empty")
                .bind(User::getLastName, User::setLastName);

        // comment
        binder.forField(comment).bind(User::getComment, User::setComment);
    }

    public UserForm() {
        initBinder();
    }



    /**
     * Connects the bean to the binder.
     *
     * @param user bean
     */
    public void setBean(User user) {
        binder.setBean(user);
    }

    /**
     * Clears the form and disconnnect any bean.
     */
    public void removeBean() {
        binder.removeBean();
    }

    /**
     * Gets the binder of the UserForm
     *
     * @return binder it binds the fields of an object to the fields shown
     */
    public User getBean() {
        return binder.getBean();
    }
}
