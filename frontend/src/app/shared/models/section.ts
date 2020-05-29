import {User} from './user';
import {Conference} from './conference';
import {Submission} from './submission';

export interface Section {
  sectionId: string;
  title: string;
  sectionChair: User;
  conference: Conference;
  submissions: Submission[];
  startTime: number;
  endTime: number;
  speakers: User[];
  listeners: User[];
  seats: number;
}



