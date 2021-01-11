import {PolymerElement,html} from '@polymer/polymer/polymer-element.js';
import {} from '@polymer/polymer/lib/elements/dom-repeat.js';

class UserList extends PolymerElement {
    static get template() {
        return html`
            <table>
                <tr>
                    <th>E-mail</th><th>First Name</th><th>Last Name</th>
                </tr>
                <template is="dom-repeat" items="[[users]]">
                        <tr on-click="handleClick" id="[[item.email]]">
                            <td>{{item.email}}</td>
                            <td>{{item.firstName}}</td>
                            <td>{{item.lastName}}</td>
                        </tr>
                </dom-repeat>
            </table>`;
    }

    static get is() {return 'user-list'}
}
customElements.define(UserList.is, UserList);
