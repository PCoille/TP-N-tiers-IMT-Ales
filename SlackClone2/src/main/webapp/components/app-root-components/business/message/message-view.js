class MessageView extends HTMLElement {
    constructor() {
        super();
        this._message;

    }
  
    connectedCallback() {
      $.get("components/app-root-components/business/message/message-view.html", (data) => this.innerHTML = data);
    }
    
    get message(){
        return this._message;
    }

    set message(msg){
        this._message = msg;

        this.tryToUpdateMessage(0,5)
    }

    tryToUpdateMessage(i, repeatMaxNb) {
        if (this.innerHTML) {
            this.updateMessage();
        }
        else if(i<repeatMaxNb) {
            setTimeout(() => this.tryToUpdateMessage(i+1, repeatMaxNb),100)
        }
        else {
            console.log("Error loading innerHTML")
        }
    }



    editInnerHtml(selector, html){
        //console.log(this.querySelector(selector))
        this.querySelector(selector).innerHTML = html;
    }

    updateMessage(){
        
        this.editInnerHtml(".msg-content",this.message.content);
        this.editInnerHtml(".msg-send-date",new Date(this.message.sendDate).
                                toLocaleString("en-GB", 
                                {
                                    weekday: 'short', 
                                    day: 'numeric', 
                                    month: 'numeric', 
                                    year:'numeric',
                                    hour:'numeric',
                                    minute:'numeric'
                                }));
        
        getJsonObjectWithCallback("user/get/" + this.message.emitter, 
                                (user)=>this.editInnerHtml(".msg-username",user.name));
        
        //console.log(this.innerHTML)
    }

    

  }
  
  customElements.define('message-view-component', MessageView);