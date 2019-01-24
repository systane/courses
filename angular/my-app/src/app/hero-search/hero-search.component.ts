import { Component, OnInit } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { of } from 'rxjs/observable/of';

import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { Hero } from '../hero';
import { HeroService } from '../hero.service';

@Component({
  selector: 'app-hero-search',
  templateUrl: './hero-search.component.html',
  styleUrls: ['./hero-search.component.css']
})
export class HeroSearchComponent implements OnInit {

  /*
    note: the '$' in the end of the variable is a convention
    to marks an Observable. Iterating over an observable using ngFor with 'async'
    pipe subscribe automatically
  */
  heroes$: Observable<Hero[]>;
  private searchTerms = new Subject<string>();

  constructor(private heroService: HeroService) { }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.heroes$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      /**
        With the switchMap operator, every qualifying key event can trigger an HttpClient.get() method call.
        Even with a 300ms pause between requests, you could have multiple HTTP requests in flight and they 
        may not return in the order sent.

        switchMap() preserves the original request order while returning only the observable from the most 
        recent HTTP method call. Results from prior calls are canceled and discarded.

        Note that canceling a previous searchHeroes() Observable doesn't actually abort a pending HTTP 
        request. Unwanted results are simply discarded before they reach your application code

        more information: https://www.learnrxjs.io/operators/transformation/switchmap.html
       */
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.heroService.searchHeroes(term)),
    );
  }
}