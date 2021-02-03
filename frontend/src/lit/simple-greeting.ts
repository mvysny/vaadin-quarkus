import {customElement, html, LitElement, property, query} from "lit-element";
import '@polymer/paper-input/paper-input.js';
import {PaperInputElement} from "@polymer/paper-input/paper-input";

@customElement('simple-greeting')
export class SimpleGreeting extends LitElement {

    @property()
    greeting = "";

    @query('#input')
    _input!: PaperInputElement;

    render() {
        return html`
            <div>
                <paper-input id="input"></paper-input>
                <button @click="${this._sayHello}">Say hello</button>
                <div>${this.greeting}</div>
            </div>`;
    }

    _sayHello() {
        const event = new CustomEvent('say-hello', {
            detail: {
                name: this._input.value
            }
        });
        this.dispatchEvent(event);
    }
}
