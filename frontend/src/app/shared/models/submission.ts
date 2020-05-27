import {Review} from './review';
import {User} from './user';

export interface Submission {
  id: string;
  title: string;
  abstractPaper: string;
  fullPaper: string;
  topics: string;
  keywords: string;
  finalVerdict: string;
  section: any[];  // Sections it belongs to
  authors: User[];  // Authors it belongs to
  likes: any[];  // PC Members that liked it
  reviews: Review[];
}

