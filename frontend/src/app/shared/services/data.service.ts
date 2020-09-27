import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Conference} from '../models/conference';

@Injectable()
export class DataService {
  // tslint:disable-next-line:variable-name
  private _conference: BehaviorSubject<Conference> = new BehaviorSubject<Conference>(null);
  public conference: Observable<Conference> = this._conference.asObservable();

  constructor() {
  }

  setConference(newConference: Conference) {
    this._conference.next(newConference);
  }

}
