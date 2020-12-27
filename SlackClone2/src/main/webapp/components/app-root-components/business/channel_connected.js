class ChannelConnectedArea extends HTMLElement {
    

    constructor() {
      super();
      this.channelId;
      this.userId;
      this.channelMsg;
    }
  
    connectedCallback() {
      $.get("components/app-root-components/business/channel_connected.html", (data) => this.innerHTML = data);
    }

    attributeChangedCallback(name, oldValue, newValue) {
      switch (name) {
          case 'channelid':
            console.log(`Value 'channelId' changed from ${oldValue} to ${newValue}`);
            this.onChannelIdChange();
            break;
          case 'userid':
            console.log(`Value 'userId' changed from ${oldValue} to ${newValue}`);
            break;
        }
  }

    get channelId(){
      return this.getAttribute('channelId');
    }

    set channelId(id){
        this.setAttribute('channelId', id);
    }

    get userId(){
      return this.getAttribute('userId');
    }

    set userId(id){
        this.setAttribute('userId', id);
    }

    updateChannel() {
      getJsonObjectWithCallback("business/channel_management/message/" + this.channelId,
                          (data) => {
                            this.channelMsg = data
                            this.loadMessages();
                          });
    }

    onChannelIdChange() {
      this.updateChannel()
    }

    loadMessages(){
       $("#channel-message-reading-area").html("");

       this.channelMsg.forEach((msg) => {
        let msgArea = new MessageView();
        $("#channel-message-reading-area").append(msgArea)
        msgArea.message = msg;
      });
    }

    getMessageFormValues() {
      return {
        "content": $("#message").val(),
        "channelId": this.channelId,
        "emitterId": this.userId
      }
    }

    sendMessage() {
      let message = this.getMessageFormValues();
      postJsonObjectWithCallback(message,
                               "business/messaging/send",
                               ()=>this.updateChannel())
    }

    static get observedAttributes() { return ['channelid', 'userid']; }

  }
  
  customElements.define('connected-channel-area', ChannelConnectedArea);

function getChannelConnectedArea() {
  return $("connected-channel-area")[0];
}