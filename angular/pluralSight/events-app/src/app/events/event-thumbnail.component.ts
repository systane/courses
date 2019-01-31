import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'event-thumbnail',
  template: `
    <div class="well hoverwell thumbnail">
      <h2>{{event?.name}}</h2>
      <div>Date: {{event.date}}</div>
      <div>Time: {{event.time}}</div>
      <div>Price: \${{ event.price }}</div>
      <div *ngIf="event?.location">
        <span>Location: {{event.location.address}}</span>
        <span>&nbsp;</span>
        <span>{{event.location.city}}, {{event.location.country}}</span>
      </div>
      <div *ngIf="event?.onlineUrl">
        Online URL: {{event.onlineUrl}}
      </div>
      <button class="btn btn-primary" (click)="handleClickMe()">Click me!</button>
    </div>
  `
})
export class EventThumbnailComponent {
  // @Input indica que esse objeto será recebido de um componente pai
  @Input() event:any

  //@Ouput indica que dados serão transmitidos para o component pai
  @Output() eventClick = new EventEmitter()


  //Variavel publica que pode ser acessada via template variable no component pai,
  //para isso basta declarar uma variable no componente pai com '#'
  someProperty:any = "Some Value"

  handleClickMe() {
    this.eventClick.emit('foo')
  }

  logFoo() {
    console.log('foo');
  }
}