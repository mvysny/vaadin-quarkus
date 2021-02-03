package org.acme.servlet.polymer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Example taken from https://vaadin.com/docs/v14/flow/polymer-templates/tutorial-template-and-binder.html
 */
@Tag("user-form")
@JsModule("./src/polymer/user-form.js")
public class UserForm extends PolymerTemplate<TemplateModel> {

    @Id("email")
    TextField email;

    @Id("first-name")
    TextField firstName;

    @Id("last-name")
    TextField lastName;

    @Id("comments")
    TextArea comment;

    public static class User implements Serializable {
        @NotEmpty
        @Email
        private String email;
        @NotEmpty
        @Length(min = 2)
        private String firstName;
        @NotEmpty
        @Length(min = 2)
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

        @Override
        public String toString() {
            return "User{" +
                    "email='" + email + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", comment='" + comment + '\'' +
                    '}';
        }

        public User() {
        }

        public User(String email, String firstName, String lastName) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    @NotNull
    private final Binder<User> binder = new BeanValidationBinder<>(User.class);

    private void initBinder() {
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

    @NotNull
    public Binder<User> getBinder() {
        return binder;
    }
}
