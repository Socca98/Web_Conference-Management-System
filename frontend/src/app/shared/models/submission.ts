import {Review} from './review';
import {User} from './user';
import {Verdict} from './verdict';

export interface Submission {
  id: string;
  title: string;
  abstractPaper: string;
  fullPaper: string;
  topics: string;
  keywords: string;
  finalVerdict: Verdict;
  section: any[];  // Sections it belongs to
  authors: User[];  // Authors it belongs to
  likes: any[];  // PC Members that liked it
  reviews: Review[];
}

