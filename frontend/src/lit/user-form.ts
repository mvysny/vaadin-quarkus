import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/vaadin-text-field/vaadin-text-field.js';
import '@vaadin/vaadin-text-field/vaadin-text-area.js';
import '@vaadin/vaadin-checkbox/vaadin-checkbox.js';
import {customElement, html, LitElement} from "lit-element";

@customElement("user-form-lit")
export class UserFormLit extends LitElement {
    render() {
        return html`
            <vaadin-form-layout id="form">
                <vaadin-text-field id="email" label="Email (login)" colspan="2"></vaadin-text-field>
                <vaadin-text-field id="first-name" label="First Name"></vaadin-text-field>
                <vaadin-text-field id="last-name" label="Last Name"></vaadin-text-field>
                <vaadin-text-area id="comments" label="Comments"></vaadin-text-area>
            </vaadin-form-layout>`;
    }
}
