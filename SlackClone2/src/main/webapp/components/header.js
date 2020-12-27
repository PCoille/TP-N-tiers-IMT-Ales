class Header extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    $.get("components/header.html", (data) => this.innerHTML = data);
  }
}

customElements.define('header-component', Header);