class AppRoot extends HTMLElement {
    constructor() {
      super();
      this.baseUrl = "components/app-root-components/";
    }
    
    connectedCallback() {
        this.load("index.html");
    }

    attributeChangedCallback(name, oldValue, newValue) {
        switch (name) {
            case 'path':
              console.log(`Value changed from ${oldValue} to ${newValue}`);
              this.load(newValue);
              break;
          }
    }

    get path(){
        return this.getAttribute('path')
    }

    set path(p){
        this.setAttribute('path', p)
    }

    load(file) {
        $.get(this.baseUrl + file, (data) => this.innerHTML = data);
    }

    static get observedAttributes() { return ['path']; }
  }

  customElements.define('app-root', AppRoot);

  function getAppRoot() {
      return $("#app-root")[0];
  }