import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'event-thumbnail',
  template: `
    <div class="well hoverwell thumbnail">
      <h2>{{event?.name}}</h2>
      <div>Date: {{event.date}}</div>
      <div [ngClass]="getStartTimeClass()"  [ngSwitch]="event?.time">
        Time: {{event?.time}} 
        <span *ngSwitchCase="'8:00 am'">(Early Start)</span>
        <span *ngSwitchCase="'9:00 am'">(Late Start)</span>
        <span *ngSwitchDefault>(Normal Start)</span>
      </div>
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
  `,
  styles: [`
    .green {color: #003300 !important;}
    .bold {font-weight: bold;}
  `]
})
export class EventThumbnailComponent {
  // @Input indica que esse objeto será recebido de um componente pai
  @Input() event: any

  //@Ouput indica que dados serão transmitidos para o component pai
  @Output() eventClick = new EventEmitter()


  //Variavel publica que pode ser acessada via template variable no component pai,
  //para isso basta declarar uma variable no componente pai com '#'
  someProperty: any = "Some Value"

  handleClickMe() {
    this.eventClick.emit('foo')
  }

  logFoo() {
    console.log('foo');
  }

  getStartTimeClass() {
    const isEarlyStart = this.event && this.event.time === '8:00 am';

    if (isEarlyStart) {
      return 'green bold';
    }

    return '';
  }
}