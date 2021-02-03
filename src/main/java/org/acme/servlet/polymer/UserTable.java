package org.acme.servlet.polymer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.List;

/**
 * Demoes passing of a list of beans via the TemplateModel; uses <code>dom-repeat</code>
 * on the client-side.
 */
@JsModule("./src/polymer/user-list.js")
@Tag("user-list")
public class UserTable extends PolymerTemplate<UserTable.UserModel> {
    public interface UserModel extends TemplateModel {
        @Include({"email", "firstName", "lastName"})
        void setUsers(List<UserForm.User> users);
        List<UserForm.User> getUsers();
    }

    public void setUsers(List<UserForm.User> users) {
        getModel().setUsers(users);
    }

    public List<UserForm.User> getUsers() {
        return getModel().getUsers();
    }

    @EventHandler
    public void handleClick(@RepeatIndex int itemIndex) {
        System.out.println(getUsers().get(itemIndex).getEmail());
    }
}
