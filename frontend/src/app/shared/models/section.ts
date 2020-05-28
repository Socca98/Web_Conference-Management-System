import {User} from './user';
import {Conference} from './conference';
import {Submission} from './submission';

export interface Section {
  id: number;
  title: string;
  sectionChair: User;
  conference: Conference;
  submissions: Submission[];
  startTime: string;
  endTime: string;
  speakers: User[];
  listeners: User[];
  seats: number;
}



