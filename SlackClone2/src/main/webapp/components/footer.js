class Footer extends HTMLElement {
    constructor() {
      super();
    }
  
    connectedCallback() {
      $.get("components/footer.html", (data) => this.innerHTML = data);
    }
  }
  
  customElements.define('footer-component', Footer);