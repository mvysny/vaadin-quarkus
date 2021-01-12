import {PolymerElement,html} from '@polymer/polymer/polymer-element.js';
import '@polymer/paper-input/paper-input.js';

class HelloWorldCC extends PolymerElement {

    static get template() {
        return html`
            <div>
                <paper-input id="inputId"></paper-input>
                <button id="helloButton" on-click="getServerGreeting">Say hello</button>
                <div id="greeting"></div>
            </div>`;
    }

    static get is() {
        return 'hello-world-cc';
    }

    async getServerGreeting() {
        let input = this.$.inputId.value;
        let greeting = await this.$server.getGreeting(input);
        console.log(greeting);
        this.$.greeting.innerHTML = greeting;
    }
}

customElements.define(HelloWorldCC.is, HelloWorldCC);
